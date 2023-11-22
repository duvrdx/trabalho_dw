<script setup>
import { ref, onBeforeMount, watch } from 'vue'
import { VDataTable } from 'vuetify/labs/VDataTable'
import Card from '@/components/project/Card.vue'
import api from '@api'



const props = defineProps({
    refresh: {
	required: false
    },
    selectMode: {
	type: Boolean
    }
})
const emit = defineEmits(['select'])

const headers = [
    {
	title: 'Nome',
	sortable: true,
	key: 'name'
    },
    {
	title: 'Acoes',
	sortable: false,
	key: 'actions'
    },
]
const items = ref([])

async function fetchItems() {
    const { data } = await api.get('/project')
    items.value = data
}

async function deleteItem(id) {
    const { data } = await api.delete(`/project/${id}/`)
    await fetchItems()
}
function selectItem(project) {
    emit('select', project)
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
	    <v-card-title>{{ props.selectMode ? 'Selecionar Projeto' : 'Projetos' }}</v-card-title>
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
	    <card :id='editId' mode='edit' @update='onUpdate' />
	</v-dialog>
	<v-dialog v-model='createModal'>
	    <card mode='create' @create='onCreate' />
	</v-dialog>
    </v-card>
</template>