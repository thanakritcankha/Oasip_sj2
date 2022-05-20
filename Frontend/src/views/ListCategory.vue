<script setup>
import { onBeforeMount, ref } from 'vue';
import EventCategoryDataService from '../services/EventCategoryDataService';
import Categoires from '../components/Categories.vue';
onBeforeMount(async () => {
  await listCategory();
  console.log(categories.value);
});
//Fetch
const categories = ref([]);
// List All Category
const listCategory = async () => {
  const res = await EventCategoryDataService.retrieveAllCategory();
  categories.value = await res.json();
};
const fade = ref(false);
</script>

<template>
  <div class="w-full flex justify-center transition ease-in-out duration-700">
    <div class="mt-2">
      <div
        class="text-3xl text-white px-4 pt-4 pb-14 bg-slate-700 rounded-lg border-double border-2 border-white"
      >
        Categories Board
      </div>
      <!-- card container -->
      <div class="-mt-16">
        <div v-for="category in categories" v-if="categories.length > 0" class>
          <Categoires :mask="category" />
        </div>
        <div v-else>No Scheduled Events</div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
