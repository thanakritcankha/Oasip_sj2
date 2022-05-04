<script setup>
defineEmits(['deleteEvent'])
const prop = defineProps({
  mask: Object,
})
const formatTime = (datetime) => {
  var date = new Date(datetime).toUTCString()
  return date.slice(-12, -7)
}
const formatDate = (datetime) => {
  var date = new Date(datetime)
  // var text = date.getDate() + " " + date.toLocaleString('en-US', { month: "short", timeZone: "UTC" }) + " " + date.getFullYear();
  return date
}
</script>

<template>
  <tr class="text-gray-800 bg-gray-500  hover:bg-gray-400 cursor-pointer">
    <td class="px-5 py-5 border-b border-gray-600 text-md">
      {{ prop.mask.eventCategory.eventCategoryName }}
    </td>
    <td class="py-5 border-b border-gray-600 text-md">
      {{ prop.mask.bookingName }}
    </td>
    <td class="px-2 py-5 border-b border-gray-600 text-sm text-center">
      <div class="grid grid-cols-3 divide-x divide-slate-800/100 h-full">
        <div>{{ formatDate(prop.mask.eventStartTime).getDate() }}</div>
        <div>{{ formatDate(prop.mask.eventStartTime).toLocaleString('en-US', { month: "short", timeZone: "UTC" }) }}
        </div>
        <div>{{ formatDate(prop.mask.eventStartTime).getFullYear() }}</div>
      </div>
    </td>
    <td class="px-2 py-5 border-b border-gray-600 text-sm">
      <div class="flex items-center justify-center">
        <span class="relative inline-block px-3 py-1 font-semibold text-green-900 leading-tight">
          <span aria-hidden class="absolute inset-0 bg-green-200 opacity-50 rounded-full"></span>
          <span class="relative">{{ formatTime(prop.mask.eventStartTime) }}</span>
        </span>
      </div>

    </td>
    <td class="px-1 py-5 border-b border-gray-600 text-sm">
      <div class="flex items-center justify-center">
        <span class="relative inline-block px-3 py-1 font-semibold text-red-900 leading-tight">
          <span aria-hidden class="absolute inset-0 bg-red-200 opacity-50 rounded-full"></span>
          <span class="relative">{{ prop.mask.eventDuration }} Minute</span>
        </span>
      </div>
    </td>
    <td class="px-1 py-1 border-b border-gray-600 text-sm">
      <div class="flex justify-around">
        <button type="button" class="flex items-center justify-center inline-block text-gray-900 hover:text-gray-100">
          Details
        </button>
        <button type="button"
          class="flex items-center justify-center px-2 py-2 rounded-full bg-gray-600 inline-block text-red-500 hover:text-gray-100"
          @click="$emit('deleteEvent', prop.mask.id)">
          <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-trash-fill"
            viewBox="0 0 16 16">
            <path
              d="M2.5 1a1 1 0 0 0-1 1v1a1 1 0 0 0 1 1H3v9a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V4h.5a1 1 0 0 0 1-1V2a1 1 0 0 0-1-1H10a1 1 0 0 0-1-1H7a1 1 0 0 0-1 1H2.5zm3 4a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 .5-.5zM8 5a.5.5 0 0 1 .5.5v7a.5.5 0 0 1-1 0v-7A.5.5 0 0 1 8 5zm3 .5v7a.5.5 0 0 1-1 0v-7a.5.5 0 0 1 1 0z" />
          </svg>
        </button>
      </div>


    </td>

  </tr>

</template>

<style scoped>
</style>