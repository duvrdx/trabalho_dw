<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import { requiredField } from '@/utils/validation'
import TypeTable from '@/components/epic/TypeTable.vue'
import ProjectTable from '@/components/project/Table.vue'
import categories from '@/utils/categories'
import api from '@api'



const props = defineProps({
    mode: {
	type: String,
	required: true,
	validator(value) {
	    return ['create', 'readonly', 'edit'].includes(value)
	}
    },
    id: {
	type: String,
	required: false
    }
})
const emit = defineEmits(['create', 'update'])


const readOnly = computed(() => props.mode === 'readonly')


const type = ref({})
const selectTypeModal = ref(false)
function selectType() {
    selectTypeModal.value = true
}
function onTypeSelected(newType) {
    type.value = newType
    selectTypeModal.value = false
}
function clearType() {
    type.value = {}
}


const project = ref({})
const selectProjectModal = ref(false)
function selectProject() {
    selectProjectModal.value = true
}
function onProjectSelected(newProject) {
    project.value = newProject
    selectProjectModal.value = false
}
function clearProject() {
    project.value = {}
}

const category = ref('')


const isValid = ref(null)
const title = ref('')
const description = ref('')
const relevance = ref(0)
async function register() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await api.post('/epic/', {
	title: title.value,
	description: description.value,
	relevance: Number(relevance.value),
	epicTypeId: type.value.id,
	projectId: project.value.id,
	category: category.value,
	// n ta funfando
	// dependencies: null
    })
    emit('create', data)
}
async function update() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await api.put(`/epicType/${props.id}/`, {
	description: description.value
    })
    emit('update', data)
}

onBeforeMount(async () => {
    if(!['edit', 'readonly'].includes(props.mode) || !props.id)
	return
    const { data } = await api.get(`/epicType/${props.id}`)
    description.value = data.description
})
</script>

<template>
    <v-card>
	<v-card-item>
	    <v-card-title>Tipo de Epico</v-card-title>
	</v-card-item>
	<v-form v-model='isValid' @submit.prevent='register'>
	    <v-card-text>
		<v-text-field v-model='title' label='Titulo' :rules=[requiredField] :readonly='readOnly' />
		<v-text-field v-model='description' label='Descricao' :readonly='readOnly' />
		<v-text-field v-model='relevance' label='Relevancia' :readonly='readOnly' type='number' />
		<v-text-field v-model='type.description' label='Tipo' readonly clearable @click:clear='clearType'>
		    <template #append>
			<v-btn color='primary' icon @click='selectType'>
			    <v-icon>
				{{ type.id ? 'mdi-pen' : 'mdi-plus' }}
			    </v-icon>
			    <v-tooltip activator='parent' position='top'>
				{{ type.id ? 'Alterar' : 'Adicionar' }}
			    </v-tooltip>
			</v-btn>
		    </template>
		</v-text-field>
		<v-text-field v-model='project.name' label='Projeto' readonly clearable @click:clear='clearProject'>
		    <template #append>
			<v-btn color='primary' icon @click='selectProject'>
			    <v-icon>
				{{ project.id ? 'mdi-pen' : 'mdi-plus' }}
			    </v-icon>
			    <v-tooltip activator='parent' position='top'>
				{{ project.id ? 'Alterar' : 'Adicionar' }}
			    </v-tooltip>
			</v-btn>
		    </template>
		</v-text-field>
		<v-select v-model='category' label='Categoria' :items='categories' clearable />
	    </v-card-text>
	    <v-card-actions>
		<v-spacer />
		<v-btn v-if='props.mode === "create"' :disabled='!isValid' color='success' @click='register'>Registrar</v-btn>
		<v-btn v-else-if='props.mode === "edit"' :disabled='!isValid' color='primary' @click='update'>Atualizar</v-btn>
	    </v-card-actions>
	</v-form>
	<v-dialog v-model='selectTypeModal'>
	    <type-table select-mode @select='onTypeSelected' />
	</v-dialog>
	<v-dialog v-model='selectProjectModal'>
	    <project-table select-mode @select='onProjectSelected' />
	</v-dialog>
    </v-card>
</template>