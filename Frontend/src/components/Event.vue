<script setup>
import { onBeforeMount, ref } from 'vue';
import router from '../router';
const slide = ref(false);
onBeforeMount(() => {
  setTimeout(() => {
    slide.value = true;
  }, 200);
});
defineEmits(['deleteEvent']);
const prop = defineProps({
  mask: Object,
});

const borderCategory = [
  'border-red-500',
  'border-blue-500',
  'border-yellow-500',
  'border-green-500',
  'border-orange-500',
];

const bgCategory = [
  'bg-red-500',
  'bg-blue-500',
  'bg-yellow-500',
  'bg-green-500',
  'bg-orange-500',
];
const getBorder = (id) => {
  // console.log(id);
  // console.log(borderCategory[id - 1]);
  return borderCategory[id - 1];
};
const getBgColor = (id) => {
  // console.log(id);
  return bgCategory[id - 1];
};

const formatTime = (datetime) => {
  var date = new Date(datetime).toLocaleString('th-TH');
  // console.log(date);
  // console.log(Intl.DateTimeFormat().resolvedOptions().timeZone);
  return date.slice(-8, -3);
};
const formatDate = (datetime) => {
  var date = new Date(datetime);
  return date;
};
const setDetail = () => {
  setTimeout(() => {
    router.push({ name: 'Detail', params: { id: prop.mask.id } });
  }, 500);
};
</script>

<template>
  <div
    class="max-w-6xl h-60 md:h-20 shadow-lg rounded overflow-hidden m-4 sm:flex bg-white rounded-xl border-l-8 border-r-8 shadow-lg shadow-black/50 cursor-pointer scale-100 hover:scale-105 transition duration-700"
    :class="getBorder(prop.mask.eventCategoryId)"
    v-bind:class="{
      'translate-x-0': slide,
      'translate-x-full': !slide,
    }"
    @click="setDetail()"
  >
    <div
      class="flex flex-col w-32 justify-center items-center border-r-2 border-gray-200 px-4"
    >
      <p class="font-extrabold text-xl text-gray-900">
        {{ formatTime(prop.mask.eventStartTime) }}
      </p>
      <p class="text-sm text-gray-600">{{ prop.mask.eventDuration }} Minute</p>
    </div>
    <!-- image -->
    <div
      class="flex flex-col justify-center items-center border-r-2 border-gray-200 px-4"
    >
      <div>{{ formatDate(prop.mask.eventStartTime).getDate() }}</div>
      <div>
        {{
          formatDate(prop.mask.eventStartTime).toLocaleString('en-US', {
            month: 'short',
          })
        }}
      </div>
      <div>{{ formatDate(prop.mask.eventStartTime).getFullYear() }}</div>
    </div>
    <!-- category -->
    <div
      class="flex flex-col basis-1/3 w-80 justify-center items-center border-r-2 border-gray-200 px-4"
    >
      <p class="font-extrabold text-xl text-gray-900">
        {{ prop.mask.bookingName }}
      </p>
      <!-- <p class="text-sm text-gray-600">klatsch.duration</p> -->
    </div>
    <!-- name -->
    <div
      class="flex flex-col basis-1/3 justify-center items-center border-r-2 border-gray-200 px-4"
    >
      <p
        class="font-extrabold text-xl text-gray-900 tag text-center"
        :class="getBgColor(prop.mask.eventCategoryId)"
      >
        {{ prop.mask.eventCategoryEventCategoryName }}
      </p>
      <!-- <p class="text-sm text-gray-600">klatsch.duration</p> -->
    </div>
    <!-- button -->
    <div class="flex flex-col justify-center items-center px-4">
      <div class="flex">
        <button
          type="button"
          class="flex items-center justify-center px-2 py-2 rounded-full inline-block text-black hover:text-red-700"
          @click.stop.prevent="setDetail()"
        >
          Detail
        </button>
        <button
          type="button"
          class="flex items-center justify-center px-3 py-3 rounded-full bg-slate-200 inline-block text-red-500 hover:text-red-700"
          @click.stop.prevent="$emit('deleteEvent', prop.mask.id)"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="20"
            height="20"
            fill="currentColor"
            class="bi bi-trash-fill"
            viewBox="0 0 16 16"
          >
            <path
              d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z"
            />
          </svg>
        </button>
      </div>
    </div>
  </div>
</template>

<style scoped>
.tags {
  /* display: flex;
  flex-direction: row;
  flex-wrap: wrap; */
  /* justify-content: flex-start; */
  align-items: center;
  margin-bottom: 1rem;
}
.tag {
  color: #fff;
  padding: 0.5rem 1rem;
  border-radius: 1rem;
}
</style>
