<script setup>
import { ref, onBeforeMount, watch } from 'vue'
import { VDataTable } from 'vuetify/labs/VDataTable'
import TypeCard from '@/components/userstory/TypeCard.vue'
import TaskTypeTable from '@/components/task/TypeTable.vue'
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
	title: 'Descricao',
	sortable: true,
	key: 'description'
    },
    {
	title: 'Tipo de Epico',
	sortable: true,
	key: 'epicType.description'
    },
    {
	title: 'Tipos de Tarefa',
	sortable: false,
	align: 'center',
	key: 'tasktypes'
    },
    {
	title: 'Historias de Usuario',
	sortable: false,
	align: 'center',
	key: 'userstories'
    },
    {
	title: 'Acoes',
	key: 'actions'
    },
]
const items = ref([
{
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "description": "tipo de historia de usuario",
  "epicType": {
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "description": "tipo de epico",
    "userStoryTypeList": [
      "string"
    ],
    "epics": [
      {
        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "description": "string",
        "title": "string",
        "relevance": 0,
        "category": "CATEGORY",
        "userStories": [
          "string"
        ],
        "epicType": "string",
        "project": {
          "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
          "name": "string",
          "epics": [
            "string"
          ]
        },
        "depends": [
          "string"
        ]
      }
    ]
  },
  "taskTypeList": [
    {
      "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "description": "string",
      "userStoryType": "string"
    }
  ],
  "userStories": [
    {
      "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "title": "string",
      "description": "string",
      "relevance": 0,
      "category": "CATEGORY",
      "tasks": [
        {
          "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
          "description": "string",
          "title": "string",
          "userStory": "string",
          "taskType": {
            "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
            "description": "string",
            "userStoryType": "string"
          },
          "depends": [
            "string"
          ]
        }
      ],
      "epic": {
        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "description": "string",
        "title": "string",
        "relevance": 0,
        "category": "CATEGORY",
        "userStories": [
          "string"
        ],
        "epicType": "string",
        "project": {
          "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
          "name": "string",
          "epics": [
            "string"
          ]
        },
        "depends": [
          "string"
        ]
      },
      "userStoryType": "string",
      "depends": [
        "string"
      ]
    }
  ]
}
])

async function fetchItems() {
    const { data } = await api.get('/userStoryType')
    items.value = data
}

async function deleteItem(id) {
    const { data } = await api.delete(`/userStoryType/${id}/`)
    console.log(data)
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



const taskTypesModal = ref(false)
const taskTypes = ref([])
function showTaskTypes(newTaskTypes) {
    taskTypes.value = newTaskTypes
    taskTypesModal.value = true
}
function hideTaskTypes() {
    taskTypesModal.value = false
    taskTypes.value = []
}


const userStoriesModal = ref(false)
const userStories = ref([])
function showUserStories(newUsrStories) {
    userStories.value = newUsrStories
    userStoriesModal.value = true
}
function hideUserStories() {
    userStories.value = []
    userStoriesModal.value = false
}



watch(() => props.refresh, fetchItems)

// onBeforeMount(fetchItems)
</script>

<template>
    <v-card>
	<v-card-item>
	    <v-card-title>
		{{ props.selectMode ? 'Selecionar Tipo de Historia de Usuario' : 'Tipos de Historia de Usuario' }}
	    </v-card-title>
	</v-card-item>
	<v-card-text>
	    <v-data-table :headers='headers' :items='items' density='compact'>
		<template #item.tasktypes="{ item }">
		    <v-btn @click='showTaskTypes(item.taskTypeList)'>Ver</v-btn>
		</template>
		<template #item.userstories="{ item }">
		    <v-btn @click='showUserStories(item.userStories)'>Ver</v-btn>
		</template>
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
	<v-dialog v-model='taskTypesModal'>
	    <task-type-table :modelValue='taskTypes' />
	</v-dialog>
    </v-card>
</template>