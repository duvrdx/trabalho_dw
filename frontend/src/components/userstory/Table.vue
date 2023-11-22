<script setup>
import { ref, onBeforeMount, watch } from 'vue'
import { VDataTable } from 'vuetify/labs/VDataTable'
import Card from '@/components/userstory/Card.vue'
import api from '@api'



const props = defineProps({
    refresh: {
	required: false
    },
    selectMode: {
	type: String,
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
	title: 'Tipo',
	sortable: true,
	key: 'userStoryType.description'
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
	title: 'Epico',
	sortable: true,
	key: 'epic.title'
    },
    {
	title: 'Tarefas',
	sortable: true,
	key: 'tasks'
    },
    {
	title: 'Dependencias',
	sortable: true,
	key: 'depends'
    },
    {
	title: 'Acoes',
	key: 'actions'
    },
]
const items = ref([])

async function fetchItems() {
    const { data } = await api.get('/userStory')
    items.value = data
}

async function deleteItem(id) {
    const { data } = await api.delete(`/userStory/${id}/`)
    await fetchItems()
}

const editModal = ref(false)
const editId = ref(undefined)
function editItem(id) {
    editId.value = id
}
const createModal = ref(false)
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
	    <v-card-title>
		{{ props.selectMode ? 'Selecionar de Usuario' : 'Historia de Usuario' }}
	    </v-card-title>
	</v-card-item>
	<v-card-text>
	    <v-data-table :headers='headers' :items='items' density='compact'>
		<template #item.actions="{ item }">
		    <v-btn color='black' icon @click='deleteItem(item.id)'>
			<v-tooltip activator='parent' location='top'>Excluir</v-tooltip>
			<v-icon>mdi-delete</v-icon>
		    </v-btn>
		    <v-btn color='blue' icon @click='editItem(item.id)'>
			<v-tooltip activator='parent' location='top'>Editar</v-tooltip>
			<v-icon>mdi-pen</v-icon>
			<!-- <v-dialog v-model='editModal' activator='parent'>
			    <card mode='edit' :id='editId' @edit='onUpdate' />
			</v-dialog> -->
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
	    <v-btn color='blue'>
		Criar Novo
		<v-dialog v-model='createModal' activator='parent'>
		    <card mode='create' @create='onCreate' />
		</v-dialog>
	    </v-btn>
	    <v-btn v-if='props.selectMode === "multiple"'
		color='blue' @click='selectMultiple' :disabled='props.modelValue.length === 0'>
		Confirmar Selecao
	    </v-btn>
	</v-card-actions>
    </v-card>
</template>