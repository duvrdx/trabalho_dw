<template>
  <v-app>
    <default-bar v-model='drawer' />

    <default-drawer v-model='drawer' />

    <default-view />
  </v-app>
</template>

<script setup>
import { ref } from 'vue'
import DefaultBar from './AppBar.vue'
import DefaultDrawer from './Drawer.vue'
import DefaultView from './View.vue'

const drawer = ref(false)
</script>
