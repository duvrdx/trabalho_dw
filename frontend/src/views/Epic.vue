<script setup>
import { ref } from 'vue'
import TypeCard from '@/components/epic/TypeCard.vue'
import TypeTable from '@/components/epic/TypeTable.vue'

const refreshTable = ref(1)
function onCreateType() {
    refreshTable.value = 1 - refreshTable.value
}
</script>

<template>
    <type-card mode='create' width='320px' @create='onCreateType' />
    <type-table width='640px' :refresh='refreshTable' />
</template>