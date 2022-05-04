<script setup>
import NavMain from './components/NavMain.vue';
// import Upcoming from './views/Upcoming.vue';
import { onMounted, ref } from 'vue';
import EventDataService from './services/EventDataService';
const menu = ref([
  { name: "ALL", "path": "/list", underline: false },
  { name: "DETAIL", "path": "/about", underline: false },
  { name: "TODAY", "path": "/", underline: false },
  { name: "UPCOMING", "path": "/", underline: false },
  { name: "PAST EVENTS", "path": "/", underline: false },
])
const btn = (index) => {
  for (let i = 0; i < menu.value.length; i++) {
    menu.value[i].underline = false
  }
  menu.value[index].underline = true
}
</script>

<template>
  <div class="h-screen w-screen bg-main">
    <NavMain />

    <div class="p-2">
      <router-view></router-view>
    </div>
  </div>

</template>

<style >
.bg-main {
  background-color: #1C1C1E;
}

body {
  font-family: 'Mochiy Pop P One', sans-serif;
}
</style>