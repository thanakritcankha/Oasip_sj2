<script setup>
import { onBeforeMount, ref } from 'vue';
import EventCategoryDataService from '../services/EventCategoryDataService';
import EventDataService from '../services/EventDataService';
onBeforeMount(async () => {
  await getAllCategory();
  setTimeout(() => {
    fade.value = true;
  }, 400);
});

const categories = ref();
const getAllCategory = async () => {
  const res = await EventCategoryDataService.retrieveAllCategoryForCreate();
  categories.value = await res.json();
};

const bookingName = ref();
const bookingEmail = ref();
const eventCategoryName = ref();
const eventCategory = ref();
const duration = ref();
const bookingNote = ref();
const eventDate = ref();
const eventTime = ref();
const overlaps = ref();
// ดึง Event Category ทั้งหมด
const listOverlap = async (id) => {
  const res = await EventDataService.retreiveCategory(id);
  const data = await res.json();
  overlaps.value = data;
};

const submitEvent = async () => {
  let text = 'Please check your event data. Press OK for booking.';
  var dateTime = new Date(`${eventDate.value}T${eventTime.value}`);
  if (eventCategory.value != null) {
    await listOverlap(eventCategory.value.id);
  }
  var result = overlaps.value.filter((item) => {
    let oldDateStart = new Date(item.eventStartTime);
    let oldDateEnd = new Date(item.eventStartTime);
    oldDateEnd.setMinutes(oldDateStart.getMinutes() + item.eventDuration);
    let userDateTimeEnd = new Date(`${eventDate.value}T${eventTime.value}`);
    userDateTimeEnd.setMinutes(userDateTimeEnd.getMinutes() + duration.value);
    //เช็คว่าเป็นวันที่เดียวกันไหม
    //และจะต้องเอา newtimend มาเช็คด้วย
    if (
      getDateM(oldDateStart) == getDateM(dateTime) ||
      getDateM(oldDateStart) == getDateM(userDateTimeEnd) ||
      getDateM(oldDateEnd) == getDateM(dateTime) ||
      getDateM(oldDateEnd) == getDateM(userDateTimeEnd)
    )
      if (
        (oldDateStart <= dateTime && dateTime < oldDateEnd) ||
        (oldDateStart < userDateTimeEnd && userDateTimeEnd <= oldDateEnd) ||
        (dateTime < oldDateStart && oldDateEnd < userDateTimeEnd) ||
        (oldDateStart < dateTime && userDateTimeEnd < oldDateEnd)
      ) {
        return true;
      }
    return false;
  });
  if (result.length != 0) {
    alert('This event is overlap.');
    eventTime.value = '';
    return false;
  }
  if (confirm(text) == true) {
    var newEvent = {
      bookingName: bookingName.value,
      bookingEmail: bookingEmail.value,
      eventStartTime: dateTime,
      eventDuration: duration.value,
      eventNotes: bookingNote.value,
      eventCategoryId: eventCategory.value.id,
      eventCategoryEventCategoryName: eventCategory.value.eventCategoryName,
    };
    // console.log(newEvent);
    const res = await EventDataService.createEvent(newEvent).then();
    if (res.status != 200) {
      alert('Fail to create Event');
    }
    reset();
    return false;
  }
};

const getDateM = (date) => {
  var dd = String(date.getDate()).padStart(2, '0');
  var mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
  var yyyy = date.getFullYear();
  var myDate = yyyy + '-' + mm + '-' + dd;
  return myDate;
};

const reset = () => {
  bookingName.value = null;
  bookingEmail.value = null;
  eventCategoryName.value = null;
  duration.value = null;
  bookingNote.value = null;
  eventDate.value = null;
  eventTime.value = null;
};
const durationCategory = () => {
  // console.log(eventCategoryName.value);
  if (eventCategoryName.value != '') {
    var x = categories.value.find(
      (value) => value.eventCategoryName == eventCategoryName.value
    );
    // console.log(x);
    eventCategory.value = x;
    duration.value = x.eventCategoryDuration;
  } else {
    duration.value = '';
  }
};
const checkDate = () => {
  if (eventDate.value != undefined && eventTime.value != undefined) {
    var selectedDate = new Date(`${eventDate.value}T${eventTime.value}`);
    var now = new Date();
    //compare now and เวลาที่เลือก
    if (selectedDate < now) {
      alert('Date must be in the future');
      eventDate.value = '';
      eventTime.value = '';
    }
  }
};
const minDate = () => {
  var today = new Date();
  return getDateM(today);
};
const fade = ref(false);
</script>

<template>
  <div
    class="min-w-full flex justify-center pt-10 transition ease-in duration-700"
    :class="{ 'opacity-0': !fade, 'opacity-100': fade }"
  >
    <form
      class="w-full max-w-5xl bg-white grid grid-cols-2 divide-x rounded-xl"
      @submit="submitEvent()"
      onsubmit="return false;"
    >
      <div
        class="place-self-center w-full h-full rounded-l-xl bgimg bg-contain bg-repeat-x bg-center bg-blue-400"
      ></div>
      <div>
        <div name="bookingForm" class="px-5 pt-6 mb-4">
          <div class="mb-4">
            <label
              for="bname"
              class="block text-gray-700 text-lg font-bold mb-2"
              >Booking Name :
            </label>
            <input
              required
              type="text"
              id="bname"
              name="bname"
              v-model="bookingName"
              maxlength="100"
              placeholder="Enter your booking name . . ."
              class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-orange-600"
            />
          </div>
          <div class="mb-6">
            <label
              for="email"
              class="block text-gray-700 text-lg font-bold mb-2"
              >Booking Email :
            </label>
            <input
              required
              type="email"
              id="email"
              name="email"
              maxlength="100"
              pattern="[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$"
              v-model="bookingEmail"
              placeholder="username@example.com"
              class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-orange-600"
            />
          </div>
          <div class="mb-6">
            <label
              for="category"
              class="block text-gray-700 text-lg font-bold mb-2"
              >Event Category :
            </label>
            <select
              required
              name="category"
              id="category"
              @change="durationCategory()"
              v-model="eventCategoryName"
              class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-orange-600"
            >
              <option disabled selected value></option>
              <option v-for="(category, index) in categories">
                {{ category.eventCategoryName }}
              </option>
            </select>
          </div>
          <div class="mb-6">
            <label
              for="duration"
              class="block text-gray-700 text-lg font-bold mb-2"
              >Booking Duration :
            </label>
            <input
              required
              type="text"
              id="duration"
              name="duration"
              v-model="duration"
              placeholder="Your event duration"
              class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight cursor-not-allowed disabled:bg-slate-200 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none"
              disabled
            />
          </div>
          <div class="mb-6">
            <label
              for="notes"
              class="block text-gray-700 text-lg font-bold mb-2"
              >Booking Notes :
            </label>
            <textarea
              v-model="bookingNote"
              maxlength="500"
              placeholder="Enter your description . . ."
              class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-gray-300 rounded transition ease-in-out focus:text-gray-700 focus:bg-white focus:border-orange-600 focus:outline-none"
            ></textarea>
          </div>
          <div class="mb-3">
            <label
              for="eventTime"
              class="block text-gray-700 text-lg font-bold mb-2"
              >Start Time Event :
            </label>
            <div class="flex px-10">
              <input
                required
                @change="checkDate()"
                type="date"
                id="eventDate"
                :min="minDate()"
                v-model="eventDate"
                class="focus:border-orange-600 shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline mr-8"
              />
              <input
                required
                @change="checkDate()"
                type="time"
                id="eventTime"
                name="eventTime"
                v-model="eventTime"
                class="focus:border-orange-600 shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
              />
            </div>
          </div>
          <button
            class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white my-5 py-1 px-5 border border-blue-500 hover:border-transparent rounded mr-5"
            @click=""
          >
            Submit
          </button>
          <router-link :to="{ name: 'ListEvent' }"
            ><button
              class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white my-5 py-1 px-5 border border-blue-500 hover:border-transparent rounded"
              @click=""
            >
              Cancel
            </button></router-link
          >
          <br />
        </div>
      </div>
    </form>
  </div>
</template>

<style scoped>
input:checked + label {
  border-color: black;
  box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1),
    0 4px 6px -2px rgba(0, 0, 0, 0.05);
}
.bgimg {
  background-image: url('../assets/bgCreate.png');
}
</style>
