<script setup>
import { onBeforeMount, ref } from 'vue';
import EventCategoryDataService from '../services/EventCategoryDataService';
import EventDataService from '../services/EventDataService';

const bookingName = ref()
const bookingEmail = ref()
const eventCategoryName = ref()
const eventCategory = ref()
const duration = ref()
const bookingNote = ref()
const eventDate = ref()
const eventTime = ref()

const submitEvent = async () => {
  // console.log(eventCategoryName.value)
  // console.log(eventDate.value)
  // console.log(eventTime.value)
  let text = "กรุณาตรวจสอบให้ครบถ้วน กดตกลงเพื่อยืนยัน";
  if (confirm(text) == true) {
    var dateTime = `${eventDate.value}T${eventTime.value}:00z`;
    // consol.log(dateTime)
    var newEvent = {
      "bookingName": bookingName.value,
      "bookingEmail": bookingEmail.value,
      "eventStartTime": dateTime,
      "eventDuration": duration.value,
      "eventNotes": bookingNote.value,
      "eventCategory": eventCategory.value
    }
    console.log(newEvent)
    const res = await EventDataService.createEvent(newEvent)
    reset()
    return false;
  }

}

function checkDate() {
  if (eventDate.value != undefined) {
    if (eventTime.value != undefined) {
      var selectedText = `${eventDate.value}T${eventTime.value}:00z`;
      var selectedDate = new Date(selectedText);
      var now = new Date();
      // console.log("now" + now)
      // console.log(selectedDate)
      selectedDate.setHours(selectedDate.getHours() - 7)
      // console.log(selectedDate)
      if (selectedDate < now) {
        alert("Date must be in the future");
        eventDate.value = ""
        eventTime.value = ""
      }
    }
  }
  // console.log(eventDate.value)
  // console.log(eventTime.value)
  // var selectedText = document.getElementById('eventTime').value;
  // var selectedDate = new Date(selectedText);
}

const reset = () => {
  bookingName.value = null
  bookingEmail.value = null
  eventCategory.value = null
  duration.value = null
  bookingNote.value = null
  eventDate.value = null
  eventTime.value = null
}

const categories = ref()
const getAllCategory = async () => {
  const res = await EventCategoryDataService.retrieveAllCategory()
  categories.value = await res.json()
}

const durationCategory = () => {
  console.log(eventCategoryName.value)
  if (eventCategoryName.value != "") {
    var x = categories.value.find((value) => value.eventCategoryName == eventCategoryName.value)
    eventCategory.value = x
    console.log(x)
    duration.value = x.eventCategoryDuration
  } else {
    duration.value = ""
  }
}



onBeforeMount(async () => {
  await getAllCategory()
})
</script>

<template>
  <div class="min-w-full flex justify-center pt-10">
    <div class="w-full max-w-xl">
      <form name="bookingForm" class="bg-white shadow-md rounded px-5 pt-6 mb-4" @submit="submitEvent();"
        onsubmit="return false;">
        <div class="mb-4">
          <label for="bname" class="block text-gray-700 text-sm font-bold mb-2">Booking Name :
          </label>
          <input required type="text" id="bname" name="bname" v-model="bookingName"
            class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
        </div>
        <div class="mb-6">
          <label for="email" class="block text-gray-700 text-sm font-bold mb-2">Booking Email :
          </label>
          <input required type="email" id="email" name="email" v-model="bookingEmail"
            class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
        </div>
        <div class="mb-6">
          <label for="category" class="block text-gray-700 text-sm font-bold mb-2">Event Category :
          </label>
          <select required name="category" id="category" @change="durationCategory()" v-model="eventCategoryName"
            class="focus:border-orange-600 shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            <option disabled selected value></option>
            <option v-for="(category, index) in categories">{{ category.eventCategoryName }}</option>
          </select>
        </div>
        <div class="mb-6">
          <label for="duration" class="block text-gray-700 text-sm font-bold mb-2">Booking Duration :
          </label>
          <input required type="text" id="duration" name="duration" v-model="duration"
            class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight cursor-not-allowed disabled:bg-slate-200 disabled:text-slate-500 disabled:border-slate-200 disabled:shadow-none"
            disabled />
        </div>
        <div class="mb-6">
          <label for="notes" class="block text-gray-700 text-sm font-bold mb-2">Booking Notes : </label><br />
          <textarea v-model="bookingNote"
            class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-orange-600 focus:outline-none"></textarea>
        </div>
        <div class="mb-6">
          <label for="eventTime" class="block text-gray-700 text-sm font-bold mb-2">Start Time Event :
          </label>
          <div class="flex px-10">
            <input required @change="checkDate()" type="date" id="eventTime" name="eventTime" v-model="eventDate"
              class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
            <input required @change="checkDate()" type="time" id="eventTime" name="eventTime" v-model="eventTime"
              class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" />
          </div>
        </div>
        <button
          class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white my-5 py-1 px-5 border border-blue-500 hover:border-transparent rounded"
          @click="">
          Submit</button><br />
      </form>
    </div>
  </div>

</template>

<style>
</style>
