<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import { requiredField } from '@/utils/validation'
import TypeTable from '@/components/task/TypeTable.vue'
import UserStoryTable from '@/components/userstory/Table.vue'
import taskController from '@/controllers/task'



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


const types = ref([])
const type = ref({})
const selectTypeModal = ref(false)
function onTypeSelected(newType) {
    type.value = newType
    selectTypeModal.value = false
}
function clearType() {
    type.value = {}
}

const userStory = ref({})
const selectUserStoryModal = ref(false)
function onUserStorySelected(newUserStory) {
    userStory.value = newUserStory
    selectUserStoryModal.value = false
}


const isValid = ref(null)

const title = ref('')
const description = ref('')

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
    type.value = {}
    userStory.value = {}
    dependencies.value = []
}

async function register() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await taskController.register({
	title: title.value,
	description: description.value,
	taskTypeId: type.value.id,
	userStoryId: userStory.value.id,
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
    const { data } = await projectController.update(props.id, {
	title: title.value,
	description: description.value,
	taskTypeId: type.value.id,
	userStoryId: userStory.value.id,
	dependencies: dependencies.value
    })
    emit('update', data)
}

onBeforeMount(async () => {
    if(!['edit', 'readonly'].includes(props.mode) || !props.id)
	return
	
    const { data } = await taskController.getItem(props.id)
    if(!data)
	throw new Error(`nao foi possivel encontrar tarefa com id ${props.id}`)
    title.value = data.title
    description.value = data.description
    type.value = data.taskType
    userStory.value = data.userStory
    dependencies.value = data.depends.map((dep) => dep.id)
})
</script>

<template>
    <v-card>
	<v-card-item>
	    <v-card-title>Tarefa</v-card-title>
	</v-card-item>
	<v-form v-model='isValid' @submit.prevent='register'>
	    <v-card-text>
		<v-text-field v-model='title' label='Titulo' :rules=[requiredField] :readonly='readOnly' />
		<v-text-field v-model='description' label='Descricao' :rules=[requiredField] :readonly='readOnly' />
		<v-text-field :modelValue='type.description' label='Tipo' :rules=[requiredField] readonly>
		    <template #append>
			<v-btn color='primary' icon>
			    <v-icon>
				{{ type.id ? 'mdi-pen' : 'mdi-plus' }}
			    </v-icon>
			    <v-tooltip activator='parent' position='top'>
				{{ type.id ? 'Alterar' : 'Adicionar' }}
			    </v-tooltip>
			    <v-dialog v-model='selectTypeModal' activator='parent'>
				<type-table v-model='types' select-mode @select='onTypeSelected' />
			    </v-dialog>
			</v-btn>
		    </template>
		</v-text-field>
		<v-text-field :modelValue='userStory.description' label='Historia de Usuario' :rules=[requiredField] readonly>
		    <template #append>
			<v-btn color='primary' icon>
			    <v-icon>
				{{ userStory.id ? 'mdi-pen' : 'mdi-plus' }}
			    </v-icon>
			    <v-tooltip activator='parent' position='top'>
				{{ userStory.id ? 'Alterar' : 'Adicionar' }}
			    </v-tooltip>
			    <v-dialog v-model='selectUserStoryModal' activator='parent'>
				<user-story-table select-mode='single' @select='onUserStorySelected' />
			    </v-dialog>
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
    </v-card>
</template>