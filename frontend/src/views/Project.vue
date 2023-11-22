<script setup>
import { ref } from 'vue'
import Card from '@/components/project/Card.vue'
import PTable from '@/components/project/Table.vue'

const refreshTable = ref(1)
function onCreate() {
    refreshTable.value = 1 - refreshTable.value
}
</script>

<template>
    <p-table />
</template>