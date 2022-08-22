<script setup>
import { onBeforeMount, onBeforeUpdate, onUpdated, ref } from 'vue';
import { useRoute } from 'vue-router';
import { detail } from '../data/EventDetail';
import router from '../router';
import EventDataService from '../services/EventDataService';
let { params } = useRoute();
//Lifecycle Hooks
onBeforeMount(async () => {
  await getDetailEvent(params.id);
  setTimeout(() => {
    fadein.value = true;
  });
});

//Fetch API
const event = ref({});
const getDetailEvent = async (id) => {
  const res = await EventDataService.retrieveEvent(id);
  if (res.status == 200) event.value = await res.json();
  else {
    alert('ขออภัยเกิดข้อผิดพลาดกรุณาลองอีกครั้ง');
    router.push({ name: 'ListEvent' });
  }
};

const editMode = ref(false);
const editNote = ref();
const editDate = ref();
const editTime = ref();

const editModeOn = (note, time) => {
  editMode.value = true;
  editNote.value = note;
  var inputDate = time.slice(0, 10);
  var inputTime = time.slice(-18, -13);
  editTime.value = inputTime;
  editDate.value = inputDate;
};

const overlaps = ref();

// ดึง Event Category ทั้งหมด
const listOverlap = async (id) => {
  // console.log(id);
  const res = await EventDataService.retreiveCategory(id);
  const data = await res.json();
  overlaps.value = data;
};

const getDateM = (date) => {
  var dd = String(date.getDate()).padStart(2, '0');
  var mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
  var yyyy = date.getFullYear();
  var myDate = yyyy + '-' + mm + '-' + dd;
  return myDate;
};

const save = async (id) => {
  var dateTime = new Date(`${editDate.value}T${editTime.value}`);
  if (event.value.eventCategoryId != null) {
    await listOverlap(event.value.eventCategoryId);
    overlaps.value = overlaps.value.filter((item) => {
      return item.id != params.id;
    });
    // console.log(overlaps.value);
  }
  var result = overlaps.value.filter((item) => {
    let oldDateStart = new Date(item.eventStartTime);
    let oldDateEnd = new Date(item.eventStartTime);
    oldDateEnd.setMinutes(oldDateStart.getMinutes() + item.eventDuration);
    let userDateTimeEnd = new Date(`${editDate.value}T${editTime.value}`);
    userDateTimeEnd.setMinutes(
      userDateTimeEnd.getMinutes() + event.value.eventDuration
    );
    // console.log(dateTime);
    // console.log(event.value.eventDuration);
    //เช็คว่าเป็นวันที่เดียวกันไหม
    if (item.id != event.value.id)
      if (
        getDateM(oldDateStart) == getDateM(dateTime) ||
        getDateM(oldDateStart) == getDateM(userDateTimeEnd) ||
        getDateM(oldDateEnd) == getDateM(dateTime) ||
        getDateM(oldDateEnd) == getDateM(userDateTimeEnd)
      )
        if (
          (oldDateStart < dateTime && dateTime < oldDateEnd) ||
          (oldDateStart < userDateTimeEnd && userDateTimeEnd < oldDateEnd)
        ) {
          return true;
        }
    return false;
  });
  // console.log(result);
  if (result.length != 0) {
    // console.log(result);
    alert('This event is overlap.');
    eventTime.value = '';
    editModeOff();
    editModeOn(event.value.eventNotes, event.value.eventStartTime);

    return false;
  }
  editModeOff();
  let obj = {
    eventStartTime: dateTime,
    eventNotes: editNote.value,
  };
  // console.log(obj);
  const res = await EventDataService.updateEvent(id, obj);
  await getDetailEvent(params.id);
};

const editModeOff = () => {
  editMode.value = false;
};

const eventNoteReturn = (notes) => {
  if (notes != '') return notes;
  else return 'No message';
};

const checkDate = () => {
  if (editDate.value != undefined && editTime.value != undefined) {
    var selectedDate = new Date(`${editDate.value}T${editTime.value}`);
    var now = new Date();
    //compare now and เวลาที่เลือก
    if (selectedDate < now) {
      alert('Date must be in the future');
      editDate.value = '';
      editTime.value = '';
    }
  }
};
const minDate = () => {
  var today = new Date();
  var dd = String(today.getDate()).padStart(2, '0');
  var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
  var yyyy = today.getFullYear();

  today = yyyy + '-' + mm + '-' + dd;
  return today;
};

const fadein = ref(false);
</script>

<template>
  <div
    class="min-w-full flex justify-center pt-10 transition ease-out duration-1000"
    :class="{ 'translate-x-0': fadein, 'translate-x-full': !fadein }"
  >
    <div class="px-6 py-6 bg-white rounded-xl">
      <div>
        <div class="text-3xl w-full text-center">My Detail</div>
        <div
          class="text-gray-400 text-2xl rounded w-full p-2 text-center"
          v-if="!editMode"
        >
          {{ event.bookingName }}
        </div>
        <input
          v-else
          type="text"
          id="bname"
          name="bname"
          v-model="event.bookingName"
          maxlength="100"
          class="shadow atext-gray-400 text-2xl rounded w-full p-2 text-center cursor-not-allowed disabled:bg-slate-200 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none"
          disabled
        />
      </div>
      <div class="grid grid-cols-2">
        <div class="flex flex-col">
          <div>
            <label class="block text-xl font-medium text-gray-700"
              >Booking Email</label
            >
            <div
              class="text-gray-400 text-lg rounded w-full p-2"
              v-if="!editMode"
            >
              {{ event.bookingEmail }}
            </div>
            <div
              v-else
              class="text-lg rounded w-full p-2 bg-slate-200 text-slate-500 border-slate-200 shadow cursor-not-allowed"
            >
              {{ event.bookingEmail }}
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700"
              >Category</label
            >
            <div
              class="text-gray-400 text-lg rounded w-full p-2"
              v-if="!editMode"
            >
              {{ event.eventCategoryEventCategoryName }}
            </div>
            <div
              v-else
              class="text-lg rounded w-full p-2 bg-slate-200 text-slate-500 border-slate-200 shadow cursor-not-allowed"
            >
              {{ event.eventCategoryEventCategoryName }}
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700"
              >Duration</label
            >
            <div
              class="text-gray-400 text-lg rounded w-full p-2"
              v-if="!editMode"
            >
              {{ event.eventDuration }} Minutes
            </div>
            <div
              v-else
              class="text-lg rounded w-full p-2 bg-slate-200 text-slate-500 border-slate-200 shadow cursor-not-allowed"
            >
              {{ event.eventDuration }} Minutes
            </div>
            <!-- <input
              v-else
              type="text"
              id="duration"
              name="duration"
              v-model="event.eventDuration"
              class="shadow appearance-none border rounded w-full text-lg py-2 px-3 text-gray-700 leading-tight cursor-not-allowed disabled:bg-slate-200 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none"
              disabled
            /> -->
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700">Date</label>
            <div
              class="text-gray-400 text-lg rounded w-full p-2"
              v-if="!editMode"
            >
              {{ event.eventStartTime?.slice(0, 10) }}
            </div>
            <div v-else>
              <input
                required
                type="date"
                id="eventDate"
                @change="checkDate()"
                :min="minDate()"
                name="eventDate"
                v-model="editDate"
                class="focus:border-orange-600 text-lg shadow appearance-none border rounded w-full p-2.5 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700">Time</label>
            <div
              class="text-gray-400 text-lg rounded w-full p-2"
              v-if="!editMode"
            >
              {{ event.eventStartTime?.slice(11, 16) }}
            </div>
            <div v-else>
              <input
                required
                type="time"
                id="eventTime"
                @change="checkDate()"
                name="eventTime"
                v-model="editTime"
                class="focus:border-orange-600 text-lg shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
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
          rows="1"
          class="form-control block w-full text-lg px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-orange-600 focus:outline-none"
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
          @click="save(event.id)"
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
