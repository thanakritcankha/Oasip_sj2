 <script setup>
import { ref } from 'vue';
import Event from './Event.vue';

const showWindow = ref(false)
</script>
 
<template>
    <div class="columns-3">
        <Event />
        <Event />
        <Event />
    </div>
        <div>

        </div>
    
</template>
 
<style scoped>

</style>