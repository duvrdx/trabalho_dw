<script setup>
import { ref, computed, onBeforeMount } from 'vue'
import { requiredField } from '@/utils/validation'
import EpicTypeTable from '@/components/epic/TypeTable.vue'
import userstoryController from '@/controllers/userstory'



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
const epicType = ref({})



const epicTypeModal = ref(false)
function selectEpicType() {
    epicTypeModal.value = true
}
function onEpicTypeSelected(newEpicType) {
    epicType.value = newEpicType
    epicTypeModal.value = false
}



async function register() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await userstoryController.registerType({
	description: description.value,
	epicTypeId: epicType.value.id
    })
    emit('create', data)
}
async function update() {
    if(!isValid.value) {
	console.error('dados invalidos do formulario')
	return
    }
    const { data } = await userstoryController.updateType(id, {
	description: description.value,
	epicTypeId: epicType.value.id
    })
    emit('update', data)
}



onBeforeMount(async () => {
    if(!['edit', 'readonly'].includes(props.mode) || !props.id)
	return
    const { data } = await userstoryController.getType(id)
    description.value = data.description
    epicType.value = data.epicType
})
</script>

<template>
    <v-card>
	<v-card-item>
	    <v-card-title>Tipo de Historia de Usuario</v-card-title>
	</v-card-item>
	<v-form v-model='isValid' @submit.prevent='register'>
	    <v-card-text>
		<v-text-field v-model='description' label='Descricao' :rules=[requiredField] :readonly='readOnly' />
		<v-text-field v-model='epicType.description' label='Tipo de Epico' readonly clearable @click:clear='epicType = {}'>
		    <template #append>
			<v-btn color='primary' icon @click='selectEpicType'>
			    <v-icon>
				{{ epicType.id ? 'mdi-pen' : 'mdi-plus' }}
			    </v-icon>
			    <v-tooltip activator='parent' position='top'>
				{{ epicType.id ? 'Alterar' : 'Adicionar' }}
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
	<v-dialog v-model='epicTypeModal'>
	    <epic-type-table select-mode @select='onEpicTypeSelected' />
	</v-dialog>
    </v-card>
</template>