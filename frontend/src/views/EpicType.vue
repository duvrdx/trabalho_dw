<script setup>
import { ref } from 'vue'
import { requiredField } from '@/utils/validation'
import api from '@api'

const isValid = ref(null)
const description = ref('')
async function register() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
    }
    const { data } = await api.post('/epicType/', {
	description: description.value
    })
    console.log(data)
}
</script>

<template>
    <v-card>
	<v-form v-model='isValid' @submit.prevent='register'>
	    <v-card-text>
		<v-text-field v-model='description' label='Descricao' :rules=[requiredField] />
	    </v-card-text>
	    <v-card-actions>
		<v-btn :disabled='!isValid' type='submit'>Registrar</v-btn>
	    </v-card-actions>
	</v-form>
    </v-card>
</template>