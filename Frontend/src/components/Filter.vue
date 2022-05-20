<script setup>
import { onBeforeMount, ref } from 'vue';
import EventCategoryDataService from '../services/EventCategoryDataService';
defineEmits(['category']);
onBeforeMount(async () => {
  await listCategory();
});

const categories = ref();
const listCategory = async () => {
  const res = await EventCategoryDataService.retrieveAllCategoryForFilter();
  categories.value = await res.json();
}; //listAllEvent

const selectCategory = ref();
</script>

<template>
  <div class="w-full flex justify-center">
    <div class="max-w-4xl px-4 py-4 bg-white rounded-2xl">
      <div class="flex">
        <div>
          <label
            for="category"
            class="block text-gray-700 text-lg font-bold mb-2"
            >Event Category :
          </label>
          <select
            required
            name="category"
            id="category"
            @change="$emit('category', selectCategory)"
            v-model="selectCategory"
            class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-orange-600"
          >
            <option selected="selected" value="0">On Filter</option>
            <option
              v-for="(category, index) in categories"
              :value="category.id"
            >
              {{ category.eventCategoryName }}
            </option>
          </select>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
