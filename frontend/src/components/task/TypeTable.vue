<script setup>
import { ref, onBeforeMount, watch } from 'vue'
import TypeCard from '@/components/task/TypeCard.vue'
import { VDataTable } from 'vuetify/labs/VDataTable'
import taskController from '@/controllers/task'



const props = defineProps({
    modelValue: {
	type: Array
    },
    refresh: {
	required: false
    },
    selectMode: {
	type: Boolean
    }
})
const emit = defineEmits(['select', 'update:modelValue'])

const headers = [
    {
	title: 'Descricao',
	sortable: true,
	key: 'description'
    },
    {
	title: 'Acoes',
	key: 'actions'
    },
]

function updateModelValue(newVal) {
    emit('update:modelValue', newVal)
}

async function fetchItems() {
    const { data } = await taskController.listTypes()
    updateModelValue(data)
}

async function deleteItem(id) {
    const { data } = await taskController.deleteType(id)
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
	    <v-card-title>
		Tipos de Tarefa
	    </v-card-title>
	</v-card-item>
	<v-card-text>
	    <v-data-table :headers='headers' :items='props.modelValue' density='compact'>
		<template #item.actions="{ item }">
		    <v-btn color='black' icon @click='deleteItem(item.id)'>
			<v-tooltip activator='parent' location='top'>Excluir</v-tooltip>
			<v-icon>mdi-delete</v-icon>
		    </v-btn>
		    <v-btn color='blue' icon @click='editItem(item.id)'>
			<v-tooltip activator='parent' location='top'>Editar</v-tooltip>
			<v-icon>mdi-pen</v-icon>
		    </v-btn>
		    <v-btn v-if='props.selectMode' color='green' icon @click='selectItem(item)'>
			<v-tooltip activator='parent' location='top'>Selecionar</v-tooltip>
			<v-icon>mdi-check</v-icon>
		    </v-btn>
		</template>
	    </v-data-table>
	</v-card-text>
	<v-card-actions>
	    <v-spacer />
	    <v-btn color='blue' @click='create'>Criar Novo</v-btn>
	</v-card-actions>
	<v-dialog v-model='editModal'>
	    <type-card :id='editId' mode='edit' @update='onUpdate' />
	</v-dialog>
	<v-dialog v-model='createModal'>
	    <type-card mode='create' @create='onCreate' />
	</v-dialog>
    </v-card>
</template>
