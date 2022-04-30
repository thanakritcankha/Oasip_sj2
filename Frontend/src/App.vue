<script setup>
import NavMain from './components/NavMain.vue';
import Upcoming from './views/Upcoming.vue';
import ListAll from './components/ListAll.vue';
import { onMounted, ref } from 'vue';
import EventDataService from './services/EventDataService';
const menu = ref([
  { name: "ALL", "path": "/", underline: false },
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




// ex 
const note = ref([])
const list = async () => {
  const res = await EventDataService.retrieveAllEvent();
  note.value = await res.json()
}
onMounted(async () => {
  await list()
  console.log(note.value);
})
</script>

<template>
  <div class="parent md:h-screen md:grid bg-main text-white font-mono font-bold">
    <main class="main ">
      <NavMain />
      <!-- <Upcoming /> -->
      <div class="pl-10  text-2xl">
        EVENTS
      </div>
      <div class="pt-5 pl-14 text-md">
        <router-link :id="n.name" v-for="(n, index) in menu" :to="n.path" class="px-5 mr-10"
          :class="{ 'border-b-2': n.underline }" @click="btn(index)">
          {{
              n.name
          }}
        </router-link>
      </div>
      <div class="p-10">
        <router-view></router-view>
        <ListAll />
      </div>


    </main>
    <!-- <section class="rightbar bg-blacksection/30 md:col-span-2">Sidebar</section> -->
  </div>

  <!-- <div class="parent md:h-screen md:grid md:grid-cols-7 bg-zinc-900 text-white font-mono font-bold">
    <main class="main  md:col-span-5">
      <NavMain />
      <Upcoming />
      <div class="pl-10  text-2xl">
        EVENTS
      </div>
      <div class="pt-5 pl-14 text-md">
        <router-link to="/" class="px-5 mr-10 border-b-2">ALL</router-link>
        <router-link to="/" class="px-5 mr-10 border-b-2">DETAIL</router-link>
        <router-link to="/" class="px-5 mr-10 border-b-2">TODAY</router-link>
        <router-link to="/" class="px-5 mr-10 border-b-2">UPCOMING</router-link>
        <router-link to="/" class="px-5 mr-10 border-b-2">PAST EVENTS</router-link>
      </div>
      <div class="p-10">
        <router-view></router-view>
        <ListAll />
      </div>


    </main>
    <section class="rightbar bg-blacksection/30 md:col-span-2">Sidebar</section>
  </div> -->

</template>

<style >
.bg-main {
  background-color: #1C1C1E;
}
</style>
