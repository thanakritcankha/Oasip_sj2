<script setup>
import { onBeforeMount, ref } from 'vue';
import EventDataService from '../services/EventDataService';
import Event from '../components/Event.vue';

//Lifecycle Hooks
onBeforeMount(async () => {
  await listEvents();
  setTimeout(() => {
    fade.value = true;
  }, 400);
});

//Fetch API
const events = ref([]);

const listEvents = async () => {
  const res = await EventDataService.retrieveAllEvent();
  events.value = await res.json();
}; //listAllEvent

const deleteEvent = async (id) => {
  const res = await EventDataService.deleteEvent(id);
  res.status === 200
    ? (events.value = events.value.filter((value) => value.id !== id))
    : console.log('Error to delete event');
}; //DeleteEvent

//Function
const confirmDelete = (id) => {
  let text = 'Do you want to delete the event?';
  if (confirm(text) == true) {
    deleteEvent(id);
  }
};
// :class="{ 'translate-x-0': scalefade, 'translate-x-full': !scalefade }"
const fade = ref(false);
</script>
<template>
  <div
    class="w-full flex justify-center transition ease-in-out duration-700"
    :class="{ 'opacity-0': !fade, 'opacity-100': fade }"
  >
    <div class="mt-2">
      <div
        class="text-3xl text-white px-4 pt-4 pb-14 bg-slate-700 rounded-lg border-double border-2 border-white"
      >
        My EventBoard
      </div>
      <!-- card container -->
      <div class="-mt-16">
        <div v-for="event in events" v-if="events.length > 0" class>
          <Event :mask="event" @deleteEvent="confirmDelete($event)" />
        </div>
        <div v-else>No Scheduled Events</div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
