<script setup>
import router from '../router';
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
  'bg-red-400',
  'bg-blue-400',
  'bg-yellow-400',
  'bg-green-400',
  'bg-orange-400',
];
const getBorder = (id) => {
  console.log(id);
  // console.log(id);
  // console.log(borderCategory[id - 1]);
  return borderCategory[id - 1];
};
const getBgColor = (id) => {
  // console.log(id);
  return bgCategory[id - 1];
};
const setDetail = () => {
  setTimeout(() => {
    router.push({ name: 'CategoryDetail', params: { id: prop.mask.id } });
  }, 500);
};
</script>

<template>
  <div
    class="max-w-2xl px-8 py-4 mx-auto bg-white rounded-lg shadow-md transition hover:bg-gray-200 cursor-pointer border-8"
    @click="setDetail()"
    :class="getBorder(prop.mask.id)"
  >
    <div class="mt-2">
      <h1
        href="https://stackdiary.com/"
        class="text-2xl font-bold text-gray-700 text-black/80"
      >
        {{ prop.mask.eventCategoryName }}
      </h1>
      <p
        class="mt-2 text-black/40"
        v-if="prop.mask.eventCategoryDescription.length > 0"
      >
        {{ prop.mask.eventCategoryDescription }}
      </p>
      <p v-else class="mt-2 text-gray-900 dark:text-gray-300">
        No Descriptions
      </p>
    </div>
    <div class="flex items-center justify-between mt-4">
      <button
        class="text-blue-600 dark:text-blue-400 hover:underline"
        @click.stop.prevent="setDetail()"
      >
        Read more ⟶
      </button>
      <div class="flex items-center">
        <span
          class="inline-block rounded-full px-3 py-1 text-sm font-semibold text-gray-700 mr-2 mb-2 text-white"
          :class="getBgColor(prop.mask.id)"
          >{{ prop.mask.eventCategoryDuration }} Minutes</span
        >
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
