<script setup>
import { onBeforeMount, ref } from 'vue';
import EventCategoryDataService from '../services/EventCategoryDataService';
defineEmits(['category', 'filter']);
onBeforeMount(async () => {
  await listCategory();
});

const categories = ref();
const listCategory = async () => {
  const res = await EventCategoryDataService.retrieveAllCategoryForFilter();
  categories.value = await res.json();
}; //listAllEvent

const selectCategory = ref(0);
const option = ref(0);
const dateTime = ref('');
const listOption = ref({
  eventCategoryId: 0,
  option: 0,
  eventStartTime: '',
});
const changeOption = () => {
  listOption.value = {
    eventCategoryId: selectCategory.value,
    option: parseInt(option.value),
    eventStartTime: dateTime.value,
  };
  // console.log(listOption.value);
  if (parseInt(option.value) == 3 && dateTime.value == '') {
    return false;
  } else if (parseInt(option.value) != 3) {
    dateTime.value = '';
    return listOption.value;
  } else {
    return listOption.value;
  }
};
const reset = () => {
  selectCategory.value = 0;
  option.value = 0;
  dateTime.value = '';

  listOption.value = {
    eventCategoryId: selectCategory.value,
    option: parseInt(option.value),
    eventStartTime: dateTime.value,
  };
  return listOption.value;
};
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
            @change="$emit('filter', changeOption())"
            v-model="selectCategory"
            class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-orange-600"
          >
            <option selected="selected" value="0">All Category</option>
            <option
              v-for="(category, index) in categories"
              :value="category.id"
            >
              {{ category.eventCategoryName }}
            </option>
          </select>
        </div>
        <div class="ml-4">
          <label
            for="category"
            class="block text-gray-700 text-lg font-bold mb-2"
            >Event Option :
          </label>
          <select
            required
            name="category"
            id="category"
            @change="$emit('filter', changeOption())"
            v-model="option"
            class="shadow border rounded w-full py-2 px-3 w- text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-orange-600"
          >
            <option selected="selected" value="0">None</option>
            <option :value="1">Upcoming Events</option>
            <option :value="2">Past Events</option>
            <option :value="3">Select Day Events</option>
          </select>
        </div>
        <div class="ml-4">
          <label
            for="category"
            class="block text-gray-700 text-lg font-bold mb-2"
            >Select Day Event :
          </label>
          <input
            @change="$emit('filter', changeOption())"
            type="date"
            id="eventDate"
            v-model="dateTime"
            class="shadow border rounded w-full py-2.5 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-orange-600 disabled:opacity-75 disabled:bg-current/80 disabled:cursor-not-allowed"
            :disabled="option != 3"
          />
        </div>
        <div>
          <button
            class="flex items-center rounded-full text-md bg-red-500 px-2 py-2 text-white mt-9 ml-4"
            @click="$emit('filter', reset())"
          >
            Reset
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
input:checked + label {
  border-color: black;
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1),
    0 4px 6px -2px rgba(0, 0, 0, 0.05);
}
</style>
