<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import { requiredField } from '@/utils/validation'
import projController from '@/controllers/project'



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
async function registerProj() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await projController.register(name.value)
    emit('create', data)
}
async function updateProj() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await projController.update(props.id, name.value)
    emit('update', data)
}

onBeforeMount(async () => {
    if(!['edit', 'readonly'].includes(props.mode) || !props.id)
	return
    // const { data } = projController.getItem(id)
    // name.value = data.name
})
</script>

<template>
    <v-card>
	<v-card-item>
	    <v-card-title>Projeto</v-card-title>
	</v-card-item>
	<v-form v-model='isValid' @submit.prevent='registerProj'>
	    <v-card-text>
		<v-text-field v-model='name' label='Nome' :rules=[requiredField] :readonly='readOnly' />
	    </v-card-text>
	    <v-card-actions>
		<v-spacer />
		<v-btn v-if='props.mode === "create"' :disabled='!isValid' color='success' type='submit'>Registrar</v-btn>
		<v-btn v-else-if='props.mode === "edit"' :disabled='!isValid' color='primary' @click='updateProj'>
		       Atualizar
		</v-btn>
	    </v-card-actions>
	</v-form>
    </v-card>
</template>