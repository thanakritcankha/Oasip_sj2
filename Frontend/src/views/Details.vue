<script setup>
import {
  onBeforeMount,
  onBeforeUnmount,
  onBeforeUpdate,
  onMounted,
  onUnmounted,
  onUpdated,
  ref,
} from 'vue';
import { detail } from '../data/EventDetail';
import router from '../router';
import EventDataService from '../services/EventDataService';
onBeforeMount(async () => {
  await getDetailEvent(detail.dataId);
  // console.log(event.value)
});
onUpdated(async () => {
  await getDetailEvent(detail.dataId);
});

const bgCategory = [
  'bg-red-500',
  'bg-blue-500',
  'bg-yellow-500',
  'bg-green-500',
  'bg-orange-500',
];

const editMode = ref(false);
const editNote = ref();
const editDate = ref();
const editTime = ref();
const editModeOn = (note, time) => {
  // console.log(note);
  console.log(time);
  editMode.value = true;
  editNote.value = note;
  var date = new Date(time);
  var hours = 0;
  if (date.getHours() < 10) hours = `0${date.getHours()}`;
  else hours = date.getHours();
  var inputTime = `${hours}:${date.getMinutes()}`;
  console.log(inputTime);
  editTime.value = inputTime;
  var month = 0;
  if (date.getMonth() < 10) month = `0${date.getMonth()}`;
  else month = date.getMonth();
  var inputDate = `${date.getFullYear()}-${month}-${date.getDate()}`;
  editDate.value = inputDate;
  // console.log(inputDate);
  // editDate.value =
};

const save = async (id) => {
  let obj = {
    eventStartTime: '2022-05-23T20:30:00.000+07:00',
    eventNotes: editNote.value,
  };
  const res = await EventDataService.updateEvent(id, obj);
  console.log(res.text());
};

const editModeOff = async (id) => {
  editMode.value = false;
  await save(id);
};

const event = ref({});
const getDetailEvent = async (id) => {
  const res = await EventDataService.retrieveEvent(id);
  if (res.status == 200) event.value = await res.json();
  else {
    alert('ขออภัยเกิดข้อผิดพลาดกรุณาลองอีกครั้ง');
    router.push({ name: 'ListEvent' });
  }
};
const formatTime = (datetime) => {
  var date = new Date(datetime).toLocaleString();
  console.log(date);
  return date.slice(-10, -6) + ' ' + date.slice(-2);
};
const formatDate = (datetime) => {
  var date = new Date(datetime);
  return (
    date.getDate() +
    ' | ' +
    date.toLocaleString('en-US', { month: 'short' }) +
    ' | ' +
    date.getFullYear()
  );
};
const eventNoteReturn = (notes) => {
  // console.log(notes == undefined);
  if (notes != undefined) return notes;
  else return 'No message';
};
</script>

<template>
  <div class="min-w-full flex justify-center pt-10">
    <div class="px-6 py-6 bg-white rounded-xl">
      <div>
        <div class="text-3xl w-full text-center">My Detail</div>
        <div class="text-gray-400 text-2xl rounded w-full p-2 mt-2 text-center">
          {{ event.bookingName }}
        </div>
      </div>
      <div class="grid grid-cols-2">
        <div class="flex flex-col">
          <div>
            <label class="block text-xl font-medium text-gray-700"
              >Booking Email</label
            >
            <div class="text-gray-400 text-lg rounded w-full p-2">
              {{ event.bookingEmail }}
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700"
              >Cetagory</label
            >
            <div class="text-gray-400 text-lg rounded w-full p-2">
              {{ event.eventCategory?.eventCategoryName }} Minutes
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700"
              >Duration</label
            >
            <div class="text-gray-400 text-lg rounded w-full p-2">
              {{ event.eventDuration }} Minutes
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700">Date</label>
            <div
              class="text-gray-400 text-lg rounded w-full p-2"
              v-if="!editMode"
            >
              {{ formatDate(event.eventStartTime) }}
            </div>
            <div v-else>
              <input
                required
                type="date"
                id="eventTime"
                name="eventTime"
                v-model="editDate"
                class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700">Time</label>
            <div
              class="text-gray-400 text-lg rounded w-full p-2"
              v-if="!editMode"
            >
              {{ formatTime(event.eventStartTime) }}
            </div>
            <div v-else>
              <input
                required
                type="time"
                id="eventTime"
                name="eventTime"
                v-model="editTime"
                class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
          </div>
        </div>
      </div>
      <div>
        <label class="block text-xl font-medium text-gray-700"
          >EventNotes</label
        >
        <div class="text-gray-400 text-lg rounded w-full p-2" v-if="!editMode">
          {{ eventNoteReturn(event.eventNotes) }}
        </div>
        <textarea
          v-else
          v-model="editNote"
          maxlength="500"
          class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-orange-600 focus:outline-none"
        ></textarea>
      </div>
      <div class="mt-6 flex">
        <button class="flex items-center mr-10">
          <router-link
            :to="{ path: '/listevents', name: 'ListEvent' }"
            class="flex items-center"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="40"
              height="40"
              fill="currentColor"
              class="bi bi-arrow-left-circle-fill fill-indigo-500 pr-2 animate-pulse"
              viewBox="0 0 16 16"
            >
              <path
                d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"
              />
            </svg>
            Back
          </router-link>
        </button>
        <button
          class="flex items-center rounded-xl bg-indigo-500 px-2 text-white"
          v-if="!editMode"
          @click="editModeOn(event.eventNotes, event.eventStartTime)"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-pen-fill mr-1"
            viewBox="0 0 16 16"
          >
            <path
              d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"
            />
          </svg>
          Edit
        </button>
        <button
          class="flex items-center rounded-xl bg-green-500 px-2 text-white mr-10"
          v-else
          @click="editModeOff(event.id)"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-pen-fill mr-1"
            viewBox="0 0 16 16"
          >
            <path
              d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"
            />
          </svg>
          Save
        </button>
        <button
          class="flex items-center rounded-xl bg-red-500 px-2 text-white"
          v-if="editMode"
          @click="editModeOff()"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-pen-fill mr-1"
            viewBox="0 0 16 16"
          >
            <path
              d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"
            />
          </svg>
          Cancel
        </button>
      </div>
    </div>
  </div>
</template>

<style></style>
