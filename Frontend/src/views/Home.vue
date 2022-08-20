<script setup>
// import { ref } from 'vue';

import { ref } from 'vue';
import router from '../router';

// const topic = ref(false);
// const element = ref(false);
// const grids = ref(false);
// setTimeout(() => {
//   element.value = true;
// }, 700);
// setTimeout(() => {
//   topic.value = true;
// }, 1000);
// setTimeout(() => {
//   grids.value = true;
// }, 1000);
const fadeout = ref(false);
const goToListEvent = () => {
  fadeout.value = true;
  setTimeout(() => {
    router.push({ path: '/listevents', name: 'ListEvent' });
  }, 700);
};
const goToCreateEvent = () => {
  fadeout.value = true;
  setTimeout(() => {
    router.push({ path: '/CreateEvent', name: 'CreateEvent' });
  }, 700);
};
</script>

<template>
  <div
    class="grid grid-cols-2 place-items-center h-max text-white mt-40 transition ease-out duration-1000"
    :class="{ 'opacity-0': fadeout, 'opacity-100': !fadeout }"
  >
    <div class="place-self-center px-10 py-10">
      <p class="lg:text-2xl xl:text-4xl mb-4 text-center">
        INT221 INFORMATION TECHNOLOGY INTEGRATED PROJECT
      </p>
      <p class="text-center">OASIP เป็น Platform ในการนัดคุยปัญหาของนักศึกษา</p>
      <div class="flex justify-evenly mt-10">
        <button
          class="rounded-full bg-indigo-500 px-5 py-2text-white transition ease-in-out hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 shadow-lg shadow-black/30"
          @click="goToListEvent()"
        >
          ดูตารางนัด
        </button>
        <button
          class="rounded-full bg-indigo-500 px-5 py-2 text-white transition ease-in-out hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 shadow-lg shadow-black/30"
          @click="goToCreateEvent()"
        >
          สร้างตารางนัด
        </button>
      </div>
    </div>
    <div class="place-self-center">
      <div class="flex justify-center">
        <img
          src="../assets/obj1.png"
          class="w-9/12 text-center"
          alt="object1"
        />
      </div>
    </div>
  </div>
  <!-- <div class="h-full w-full">
    <div class="relative w-full flex justify-center">
      <h1
        class="absolute text-8xl text-white transform transition-all duration-700 ease-out scale-0 top-10 left-50"
        :class="{ 'scale-0': topic, 'scale-100': topic }"
      >
        OASIP - จตุรเทพ
      </h1>
      <h1
        class="text-5xl text-white block transform transition-all duration-700 ease-out mt-10"
        :class="{ 'mt-36': element }"
      >
        What is Oasip?
      </h1>
      <div class="flex justify-center">
        <div>
          <img src="../assets/obj1.png" class="h-96 w-96" alt="object1" />
          <div class="w-full flex justify-center">
            <button
              class="rounded-full bg-indigo-500 px-5 py-2 text-white transition ease-in-out hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 shadow-lg shadow-black/30"
            >
              Button
            </button>
          </div>
        </div>
      </div>
    </div>
  </div> -->
</template>

<style scoped></style>
