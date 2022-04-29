<script setup>
const btn = () => {
    const menu = document.querySelector(".mobile-menu");
    menu.classList.toggle("hidden");
}
</script>
 
<template>
    <div class="grid grid-cols-2 flex items-center p-4 text-white">
        <div class="flex items-center text-2xl pl-10">
            <img src="/xiao-white.png" alt="Logo" width="100" class="pt-1">
            <p>จตุรเทพ</p>
        </div>
        <div class="text-md flex justify-evenly">
            <router-link to="/" class="border-b-2">HOME</router-link>
            <router-link to="/about">DEV TEAM</router-link>
        </div>
    </div>
</template>
 
<style>
</style>