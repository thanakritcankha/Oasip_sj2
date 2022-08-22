<script setup>
import router from '../router';

defineEmits(['deleteUser']);

const prop = defineProps({
  mask: Object,
});

const setDetail = () => {
  setTimeout(() => {
    router.push({ name: 'UserDetail', params: { id: prop.mask.id } });
  }, 500);
};
</script>

<template>
  <td class="px-5 py-5 border-b border-gray-200 bg-white text-lg">
    <div class="flex">
      <div class="ml-3 w-96">
        <h2 class="text-gray-900 whitespace-no-wrap">{{ prop.mask.name }}</h2>
      </div>
    </div>
  </td>
  <td class="px-5 py-5 border-b border-gray-200 bg-white text-lg">
    <p class="text-gray-900 whitespace-no-wrap">{{ prop.mask.email }}</p>
  </td>
  <td class="px-5 py-5 border-b border-gray-200 bg-white text-lg">
    <span
      class="relative inline-block px-3 py-1 font-semibold text-green-900 leading-tight"
    >
      <span
        aria-hidden
        class="absolute inset-0 bg-green-200 opacity-50 rounded-full"
      ></span>
      <span class="relative">{{ prop.mask.role }}</span>
    </span>
  </td>
  <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
    <span
      class="relative inline-block px-3 py-1 font-semibold text-green-900 leading-tight"
    >
      <button
        @click.stop.prevent="setDetail(prop.mask.id)"
        class="w-8 h-8 flex"
      >
        <img src="../../public/icons/information.png" />
      </button>
      <button
        class="w-8 h-8 mt-1 flex"
        @click.stop.prevent="$emit('deleteUser', prop.mask.id)"
      >
        <img src="../../public/icons/delete.png" />
      </button>
    </span>
  </td>

  <!-- <div class="px-6 py-4">
    <div>
      <div class="font-bold text-xl mb-2">{{ prop.mask.name }}</div>
      <p class="text-gray-700 text-base">Email : {{ prop.mask.email }}</p>
      <p class="text-gray-700 text-base">Role : {{ prop.mask.role }}</p>
    </div>
    <div>
      <button
        @click.stop.prevent="setDetail(prop.mask.id)"
        class="justify-items-end w-8 h-8 ml-96 flex"
      >
        <img src="../../public/icons/information.png" />
      </button>
      <button
        class="justify-items-end w-8 h-8 ml-96 pt-2 flex"
        @click.stop.prevent="$emit('deleteUser', prop.mask.id)"
      >
        <img src="../../public/icons/delete.png" />
      </button>
    </div>
  </div> -->
</template>

<style></style>
