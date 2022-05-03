<script setup>
import router from '../router';
// const btn = () => {
//     const menu = document.querySelector(".mobile-menu");
//     menu.classList.toggle("hidden");



// }
const goHome = () => {
    router.push({ path: '/', name: 'ListEvent' })
}
</script>
 
<template>
    <!-- <div class="grid grid-cols-2 flex items-center p-4 text-white">
        <div class="flex items-center text-2xl pl-10">
            <img src="../assets/xiao-white.png" alt="Logo" width="100" class="pt-1">
            <p>จตุรเทพ</p>
        </div>
        <div class="text-md flex justify-evenly">
            <router-link to="/" class="border-b-2">HOME</router-link>
            <router-link to="/about">DEV TEAM</router-link>
        </div>
    </div> -->
    <div class="flex items-center w-full bg-zinc-800 rounded-b-2xl">
        <div class="flex items-center text-2xl pl-10 text-white cursor-pointer" @click="goHome()">
            <img src="../assets/xiao-white.png" alt="Logo" width="80" class="pt-1">
            <p>จตุรเทพ</p>
        </div>
        <router-link to="/about" class="pl-10 text-white">about</router-link>
    </div>
</template>
 
<style>
</style>