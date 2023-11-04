<script setup>
import { ref } from 'vue'
import Card from '@/components/epic/Card.vue'
import ETable from '@/components/epic/Table.vue'
import TypeCard from '@/components/epic/TypeCard.vue'
import TypeTable from '@/components/epic/TypeTable.vue'

const refreshTable = ref(1)
function onCreateType() {
    refreshTable.value = 1 - refreshTable.value
}

function criouProjeto(projeto) {
    console.log('projeto:', projeto)
}
</script>

<template>
    <e-table />
</template>