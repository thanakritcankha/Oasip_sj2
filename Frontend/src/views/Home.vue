<script setup>
import { ref } from 'vue';

const topic = ref(false);
const element = ref(false);
const grids = ref(false);
setTimeout(() => {
  element.value = true;
}, 700);
setTimeout(() => {
  topic.value = true;
}, 1000);
setTimeout(() => {
  grids.value = true;
}, 1000);
</script>

<template>
  <div class="grid grid-cols-2 place-items-center h-max">
    <div class="place-self-center px-20 py-10">
      <h4>INT221 INFORMATION TECHNOLOGY INTEGRATED PROJECT</h4>
      <p>
        What is Lorem Ipsum? Lorem Ipsum is simply dummy text of the printing
        and typesetting industry. Lorem Ipsum has been the industry's standard
        dummy text ever since the 1500s, when an unknown printer took a galley
        of type and scrambled it to make a type specimen book. It has survived
        not only five centuries, but also the leap into electronic typesetting,
        remaining essentially unchanged. It was popularised in the 1960s with
        the release of Letraset sheets containing Lorem Ipsum passages, and more
        recently with desktop publishing software like Aldus PageMaker including
        versions of Lorem Ipsum.
      </p>
      <button
        class="rounded-full bg-indigo-500 px-5 py-2 text-white transition ease-in-out hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 shadow-lg shadow-black/30"
      >
        Button
      </button>
      <button
        class="rounded-full bg-indigo-500 px-5 py-2 text-white transition ease-in-out hover:-translate-y-1 hover:scale-110 hover:bg-indigo-500 duration-300 shadow-lg shadow-black/30"
      >
        Button
      </button>
      <router-link :to="{ path: '/listevents', name: 'ListEvent' }"
        >dsamdklsa</router-link
      >
    </div>
    <div class="place-self-center">
      <div class="flex justify-center">
        <img
          src="../assets/obj1.png"
          class="w-10/12 text-center"
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
