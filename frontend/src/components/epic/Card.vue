<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import { requiredField } from '@/utils/validation'
import TypeTable from '@/components/epic/TypeTable.vue'
import ProjectTable from '@/components/project/Table.vue'
import ETable from '@/components/epic/Table.vue'
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

const isValid = ref(null)

const title = ref('')
const description = ref('')
const relevance = ref(0)

const category = ref('')
const categories = ref([])
async function getCategories() {
    const { data } = await api.get('/categories/epic')
    categories.value = data
}

const dependencies = ref([])
const selectDepsModal = ref(false)
function selectDeps() {
    selectDepsModal.value = true
}
function setDeps(newDeps) {
    dependencies.value = newDeps
}
const depsText = computed(() =>
    !dependencies.value || dependencies.value.length === 0 ?
	'Nenhuma Dependencia selecionada'
    : dependencies.value.length === 1 ?
	'1 Dependencia selecionada'
    : `${dependencies.value.length} Dependencias selecionadas`
)

function reset() {
    title.value = ''
    description.value = ''
    relevance.value = undefined
    type.value = {}
    project.value = {}
    category.value = ''
    dependencies.value = []
}

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
	dependencies: dependencies.value
    })
    emit('create', data)
    // reset()
}
async function update() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await api.put(`/epic/${props.id}/`, {
	title: title.value,
	description: description.value,
	relevance: Number(relevance.value),
	epicTypeId: type.value.id,
	projectId: project.value.id,
	category: category.value,
	dependencies: dependencies.value
    })
    emit('update', data)
}

onBeforeMount(async () => {
    await getCategories()

    if(!['edit', 'readonly'].includes(props.mode) || !props.id)
	return
	
    const { data } = await api.get(`/epic/${props.id}`)
    if(!data)
	throw new Error(`nao foi possivel encontrar epico com id ${props.id}`)
    title.value = data.title
    description.value = data.description
    relevance.value = data.relevance
    category.value = data.category
    type.value = data.epicType
    project.value = data.project
    dependencies.value = data.depends.map((dep) => dep.id)
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
		<v-autocomplete v-model='category' label='Categoria' :items='categories'/>
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
		<v-text-field :modelValue='depsText' label='Dependencias' readonly>
		    <template #append>
			<v-btn color='primary' icon @click='selectDeps'>
			    <v-icon>
				{{ dependencies && dependencies.length ? 'mdi-pen' : 'mdi-plus' }}
			    </v-icon>
			    <v-tooltip activator='parent' position='top'>
				{{ dependencies && dependencies.length ? 'Alterar' : 'Adicionar' }}
			    </v-tooltip>
			</v-btn>
		    </template>
		</v-text-field>
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
	<v-dialog v-model='selectDepsModal'>
	    <e-table v-model='dependencies' select-mode='multiple' @select-multiple='selectDepsModal = false' />
	</v-dialog>
    </v-card>
</template>