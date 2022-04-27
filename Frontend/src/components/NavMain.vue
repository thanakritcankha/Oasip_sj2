<script setup>
const btn = () => {
    const menu = document.querySelector(".mobile-menu");
    menu.classList.toggle("hidden");
}
</script>
 
<template>
    <div class="grid grid-cols-2 flex items-center p-10 text-white">
        <div class="text-2xl pl-10">จตุรเทพ</div>
        <div class="text-md flex justify-evenly">
            <router-link to="/" class="border-b-2">HOME</router-link>
            <router-link to="/about">DEV TEAM</router-link>
        </div>
    </div>
</template>
 
<style>
</style>