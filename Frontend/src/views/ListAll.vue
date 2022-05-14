<script setup>
import { onBeforeMount, ref } from 'vue';
import EventDataService from '../services/EventDataService';
import Event from '../components/Event.vue';

const events = ref([]);
const listEvents = async () => {
  const res = await EventDataService.retrieveAllEvent();
  events.value = await res.json();
};
const btnDelete = (id) => {
  // let text = "Press a button!\nEither OK or Cancel.";
  // let text = "เลือกดีๆนะไอ้สัส กูขี้เกียจใส่ข้อมูลใหม่";
  let text = 'Do you want to delete the event?';
  // console.log(id)
  if (confirm(text) == true) {
    deleteEvent(id);
  }
};
const deleteEvent = async (id) => {
  const res = await EventDataService.deleteEvent(id);
  res.status === 200
    ? (events.value = events.value.filter((value) => value.id !== id))
    : console.log('Error to delete event');
};
onBeforeMount(async () => {
  await listEvents();
  // console.log(events.value)
});
</script>
<template>
  <div class="w-full flex justify-center">
    <div class="mt-2">
      <div
        class="text-3xl text-white px-4 pt-4 pb-14 bg-slate-700 rounded-lg border-double border-2 border-white"
      >
        My EventBoard
      </div>
      <!-- card container -->
      <div class="-mt-16">
        <div v-for="event in events" v-if="events.length > 0" class>
          <Event :mask="event" @deleteEvent="btnDelete($event)" />
        </div>
        <div v-else>No Scheduled Events</div>
      </div>
    </div>
  </div>
</template>

<style scoped></style>
