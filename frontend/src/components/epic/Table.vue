<script setup>
import { ref, onBeforeMount, watch } from 'vue'
import { VDataTable } from 'vuetify/labs/VDataTable'
import Card from '@/components/epic/Card.vue'
import epicController from '@/controllers/epic'



const props = defineProps({
    refresh: {
	required: false
    },
    selectMode: {
	type: String,
	required: false,
	validator(value) {
	    return ['single', 'multiple'].includes(value) || !value
	}
    },
    modelValue: {
	type: Array,
	required: false
    }
})
const emit = defineEmits(['select', 'selectMultiple', 'update:modelValue'])

function updateModelValue(newValue) {
    console.log(newValue)
    emit('update:modelValue', newValue)
}

const headers = [
    {
	title: 'Titulo',
	sortable: true,
	key: 'title'
    },
    {
	title: 'Descricao',
	sortable: true,
	key: 'description'
    },
    {
	title: 'Relevancia',
	sortable: true,
	key: 'relevance'
    },
    {
	title: 'Categoria',
	sortable: true,
	key: 'category'
    },
    {
	title: 'Tipo',
	sortable: true,
	key: 'epicType.description'
    },
    {
	title: 'Projeto',
	sortable: true,
	key: 'project.name'
    },
    {
	title: 'Acoes',
	key: 'actions'
    },
]

const items = ref([])
async function fetchItems() {
    const { data } = await epicController.list()
    items.value = data
}
async function deleteItem(id) {
    const { data } = await epicController.delete(id)
    await fetchItems()
}

const editModal = ref(false)
const editId = ref(undefined)
function editItem(id) {
    editId.value = id
    editModal.value = true
}
const createModal = ref(false)
function create() {
    createModal.value = true
}
async function onCreate() {
    createModal.value = false
    await fetchItems()
}
function selectItem(item) {
    emit('select', item)
}
function selectMultiple() {
    if(props.modelValue && props.modelValue.length)
	emit('selectMultiple')
}
async function onUpdate() {
    editModal.value = false
    await fetchItems()
}

watch(() => props.refresh, fetchItems)

onBeforeMount(fetchItems)
</script>

<template>
    <v-card>
	<v-card-item>
	    <v-card-title>{{ props.selectMode ? 'Selecionar Epico' : 'Epicos' }}</v-card-title>
	</v-card-item>
	<v-card-text>
	    <v-data-table :modelValue='props.modelValue' @update:modelValue='updateModelValue'
		:show-select='props.selectMode === "multiple"' :headers='headers'
		:items='items' item-value='id' density='compact'>
		<template #item.actions="{ item }">
		    <v-btn color='black' icon @click='deleteItem(item.id)'>
			<v-tooltip activator='parent' location='top'>Excluir</v-tooltip>
			<v-icon>mdi-delete</v-icon>
		    </v-btn>
		    <v-btn color='blue' icon @click='editItem(item.id)'>
			<v-tooltip activator='parent' location='top'>Editar</v-tooltip>
			<v-icon>mdi-pen</v-icon>
		    </v-btn>
		    <v-btn v-if='props.selectMode === "single"' color='green' icon @click='selectItem(item)'>
			<v-tooltip activator='parent' location='top'>Selecionar</v-tooltip>
			<v-icon>mdi-check</v-icon>
		    </v-btn>
		</template>
	    </v-data-table>
	</v-card-text>
	<v-card-actions>
	    <v-spacer />
	    <v-btn color='blue' @click='create'>Criar Novo</v-btn>
	    <v-btn v-if='props.selectMode === "multiple"'
		color='blue' @click='selectMultiple' :disabled='props.modelValue.length === 0'>
		Confirmar Selecao
	    </v-btn>
	</v-card-actions>
	<v-dialog v-model='editModal'>
	    <card :id='editId' mode='edit' @update='onUpdate' />
	</v-dialog>
	<v-dialog v-model='createModal'>
	    <card mode='create' @create='onCreate' />
	</v-dialog>
    </v-card>
</template>