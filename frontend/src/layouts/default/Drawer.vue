<template>
    <v-navigation-drawer
	:modelValue='props.modelValue'
	@update:modelValue='updateModelValue'
    >
	<v-list-item title="Projetos" :to='{ name: "project" }' />
	<v-list-item title="Épicos" :to='{ name: "epic" }' />
	<v-list-item title="Histórias de Usuário" :to='{ name: "userstory" }' />
	<v-list-item title="Tarefas" :to='{ name: "task" }' />
    </v-navigation-drawer>
</template>

<script setup>
const props = defineProps({
    modelValue: {
	type: Boolean,
	required: true
    }
})
const emit = defineEmits(['update:modelValue'])
function updateModelValue(newValue) {
    emit('update:modelValue', newValue)
}
</script>