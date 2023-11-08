<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import { requiredField } from '@/utils/validation'
import UserTypeTable from '@/components/userstory/TypeTable.vue'
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
const userStoryType = ref({})



const userStoryTypeModal = ref(false)
function selectUserStoryType() {
    userStoryTypeModal.value = true
}
function onUserStoryTypeSelected(newUserStoryType) {
    userStoryType.value = newUserStoryType
    userStoryTypeModal.value = false
}



async function register() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await api.post('/taskType/', {
	description: description.value,
	userStoryTypeId: userStoryType.value.id
    })
    emit('create', data)
}
async function update() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await api.put(`/taskType/${props.id}/`, {
	description: description.value,
	userStoryTypeId: userStoryType.value.id
    })
    emit('update', data)
}



onBeforeMount(async () => {
    if(!['edit', 'readonly'].includes(props.mode) || !props.id)
	return
    const { data } = await api.get(`/taskType/${props.id}`)
    description.value = data.description
    userStoryType.value = data.userStoryType
})
</script>

<template>
    <v-card>
	<v-card-item>
	    <v-card-title>Tipo de Tarefa</v-card-title>
	</v-card-item>
	<v-form v-model='isValid' @submit.prevent='register'>
	    <v-card-text>
		<v-text-field v-model='description' label='Descricao' :rules=[requiredField] :readonly='readOnly' />
		<v-text-field v-model='userStoryType.description' label='Tipo de Historia de Usuario' readonly clearable @click:clear='userStoryType = {}'>
		    <template #append>
			<v-btn color='primary' icon @click='selectUserStoryType'>
			    <v-icon>
				{{ userStoryType.id ? 'mdi-pen' : 'mdi-plus' }}
			    </v-icon>
			    <v-tooltip activator='parent' position='top'>
				{{ userStoryType.id ? 'Alterar' : 'Adicionar' }}
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
	<v-dialog v-model='userStoryTypeModal'>
	    <user-type-table select-mode @select='onUserStoryTypeSelected' />
	</v-dialog>
    </v-card>
</template>