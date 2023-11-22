<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import { requiredField } from '@/utils/validation'
import EpicTable from '@/components/epic/Table.vue'
import TypeTable from '@/components/userstory/TypeTable.vue'
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

const isValid = ref(null)
const description = ref('')
const title = ref('')
const type = ref({})
const relevance = ref(0)
const epic = ref({})



const selectTypeModal = ref(false)
function selectType(newType) {
    type.value = newType
    selectTypeModal.value = false
}

const selectEpicModal = ref(false)
function selectEpic(newEpic) {
    epic.value = newEpic
    selectEpicModal.value = false
}



async function register() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await api.post('/userStory/', {
	description: description.value,
	title: title.value,
	relevance: relevance.value,
	epicId: epic.value.id,
	userStoryTypeId: type.value.id
    })
    emit('create', data)
}
async function update() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await api.put(`/userStory/${props.id}/`, {
	id: props.id,
	description: description.value,
	title: title.value,
	relevance: relevance.value,
	epicId: epic.value.id,
	userStoryTypeId: type.value.id
    })
    emit('update', data)
}



onBeforeMount(async () => {
    if(!['edit', 'readonly'].includes(props.mode) || !props.id)
	return
    const { data } = await api.get(`/userStory/${props.id}`)
    title.value = data.title
    description.value = data.description
    relevance.value = data.relevance
    epic.value = data.epic
    type.value = data.userStoryType
})
</script>

<template>
    <v-card>
	<v-card-item>
	    <v-card-title>Historia de Usuario</v-card-title>
	</v-card-item>
	<v-form v-model='isValid' @submit.prevent='register'>
	    <v-card-text>
		<v-text-field v-model='title' label='Titulo' :rules=[requiredField] :readonly='readOnly' />
		<v-text-field v-model='description' label='Descricao' :rules=[requiredField] :readonly='readOnly' />
		<v-text-field v-model='type.description' label='Tipo' readonly clearable @click:clear='type = {}'>
		    <template #append>
			<v-btn color='primary' icon>
			    <v-icon>
				{{ type.id ? 'mdi-pen' : 'mdi-plus' }}
			    </v-icon>
			    <v-tooltip activator='parent' position='top'>
				{{ type.id ? 'Alterar' : 'Adicionar' }}
			    </v-tooltip>
			    <v-dialog v-model='selectTypeModal' activator='parent'>
				<type-table select-mode @select='selectType'/>
			    </v-dialog>
			</v-btn>
		    </template>
		</v-text-field>
		<v-text-field v-model='relevance' label='Relevancia' type='number' :rules='[requiredField]' :readonly='readOnly' />
		<v-text-field v-model='epic.title' label='Epico' readonly clearable @click:clear='epic = {}'>
		    <template #append>
			<v-btn color='primary' icon>
			    <v-icon>
				{{ epic.id ? 'mdi-pen' : 'mdi-plus' }}
			    </v-icon>
			    <v-tooltip activator='parent' position='top'>
				{{ epic.id ? 'Alterar' : 'Adicionar' }}
			    </v-tooltip>
			    <v-dialog v-model='selectEpicModal' activator='parent'>
				<epic-table select-mode='single' @select='selectEpic'/>
			    </v-dialog>
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