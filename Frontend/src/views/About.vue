<script setup>
import router from '../router'

const goBack = () => {
  router.push({ name: 'ListCategory' })
}
</script>

<template>
  <div
    class="flex-wrap flex justify-center"
    :class="{ 'opacity-0': fadeout, 'opacity-100': !fadeout }"
  >
    <div class="rounded-xl flex py-32 mt-28">
      <div class="grid-rows-4 gap-3">
        <img
          src="../assets/images/paul.jpeg"
          alt="ThatsPaul"
          class="w-48 h-48 rounded-full mx-12 border-4 border-white object-cover"
        />
        <h3 class="text-center text-white pt-5" id="shadow">
          ทรรศภูมิ คงนิธิเฉลิม
        </h3>
        <h2 class="text-center text-white" id="shadow">63130500044</h2>
      </div>
      <div class="grid-rows-4 gap-4">
        <img
          src="../assets/images/wave.jpeg"
          alt="Wave"
          class="w-48 h-48 rounded-full mx-12 border-4 border-white object-cover"
        />
        <h3 class="text-center text-white pt-5" id="shadow">ธนกฤต แคนขา</h3>
        <h2 class="text-center text-white" id="shadow">63130500045</h2>
      </div>
      <div class="grid-rows-4 gap-4">
        <img
          src="../assets/images/prin.jpeg"
          alt="prin"
          class="w-48 h-48 rounded-full mx-12 border-4 border-white object-cover"
        />
        <h3 class="text-center text-white pt-5" id="shadow">ปรียาทร ไชยคำ</h3>
        <h2 class="text-center text-white" id="shadow">63130500079</h2>
      </div>
    </div>
  </div>
  <div class="w-full flex justify-center">
    <button
      class="flex items-center text-2xl mr-10 bg-white px-2 py-2 rounded-3xl"
      @click="goBack()"
    >
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="45"
        height="45"
        fill="currentColor"
        class="bi bi-arrow-left-circle-fill fill-indigo-500 pr-2 animate-pulse"
        viewBox="0 0 16 16"
      >
        <path
          d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"
        />
      </svg>
      Back
    </button>
  </div>
</template>

<style>
#shadow {
  text-shadow: 1px 1px rgb(0, 119, 119);
}
</style>
