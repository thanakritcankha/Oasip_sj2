<script setup>
import { onBeforeMount, ref } from 'vue';
import EventCategoryDataService from '../services/EventCategoryDataService';
import Categoires from '../components/Categories.vue';
onBeforeMount(async () => {
  await listCategory();
  // console.log(categories.value);
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
      <!-- card container -->
      <div class="mt-16">
        <div v-for="category in categories" v-if="categories.length > 0" class>
          <Categoires :mask="category" />
        </div>
        <div v-else>No Scheduled Events</div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.container {
  column-count: 3;
  column-gap: 20px;
  column-fill: balance;
  margin: 20px auto 0;
  padding: 2rem;
}
.container .item {
  display: inline-block;
  margin: 0 0 20px;
  -webkit-column-break-inside: avoid;
  page-break-inside: avoid;
  break-inside: avoid;
  width: 100%;
}
.container .item {
  width: 100%;
  height: auto;
}
</style>
