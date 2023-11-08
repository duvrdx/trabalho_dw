<script setup>
import { ref } from 'vue'
import TypeTable from '@/components/task/TypeTable.vue'
const tipos = ref([])
</script>

<template>
    <type-table v-model='tipos'/>
</template>