<script setup>
import NavMain from './components/NavMain.vue';
import Upcoming from './views/Upcoming.vue';
import ListAll from './components/ListAll.vue';
</script>

<template>
  <div class="parent md:h-screen md:grid md:grid-cols-7 bg-blackmain text-white">
    <main class="main  md:col-span-5">
      <NavMain />
      <Upcoming />
      <div class="pl-10 pt-10 text-2xl">
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
  </div>


</template>

<style scoped>
</style>
