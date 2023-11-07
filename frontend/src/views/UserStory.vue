<script setup>
import TypeCard from '@/components/userstory/TypeCard.vue'
import TypeTable from '@/components/userstory/TypeTable.vue'
</script>

<template>
    <type-card mode='create' @create='console.log' />
    <type-table />
</template>