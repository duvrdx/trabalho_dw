<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import { requiredField } from '@/utils/validation'
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
const name = ref('')
async function register() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await api.post('/project/', {
	name: name.value
    })
    emit('create', data)
}
async function update() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await api.put(`/project/${props.id}/`, {
	name: name.value
    })
    emit('update', data)
}

onBeforeMount(async () => {
    if(!['edit', 'readonly'].includes(props.mode) || !props.id)
	return
    const { data } = await api.get(`/project/${props.id}`)
    name.value = data.name
})
</script>

<template>
    <v-card>
	<v-card-item>
	    <v-card-title>Projeto</v-card-title>
	</v-card-item>
	<v-form v-model='isValid' @submit.prevent='register'>
	    <v-card-text>
		<v-text-field v-model='name' label='Nome' :rules=[requiredField] :readonly='readOnly' />
	    </v-card-text>
	    <v-card-actions>
		<v-spacer />
		<v-btn v-if='props.mode === "create"' :disabled='!isValid' color='success' @click='register'>Registrar</v-btn>
		<v-btn v-else-if='props.mode === "edit"' :disabled='!isValid' color='primary' @click='update'>Atualizar</v-btn>
	    </v-card-actions>
	</v-form>
    </v-card>
</template>