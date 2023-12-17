<template>
  <v-app-bar flat color='primary'>
    <template #prepend>
	<v-app-bar-nav-icon @click='updateModelValue' />
    </template>
    <v-app-bar-title>
      <v-icon icon="mdi-bird" />
      Origami
    </v-app-bar-title>
  </v-app-bar>
</template>

<script setup>
const props = defineProps({
    modelValue: {
	type: Boolean,
	required: true
    }
})
const emit = defineEmits(['update:modelValue'])
function updateModelValue() {
    emit('update:modelValue', !props.modelValue)
}
</script>
