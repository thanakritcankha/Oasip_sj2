<script setup>
import { onBeforeMount, ref } from 'vue';
import { detail } from '../data/EventDetail'
import router from '../router';
import EventDataService from '../services/EventDataService';
onBeforeMount(async () => {
  await getDetailEvent(detail.dataId)
  categoryName()
  // console.log(event.value)
})
const event = ref({})
const getDetailEvent = async (id) => {
  const res = await EventDataService.retrieveEvent(id);
  if (res.status == 200)
    event.value = await res.json()
  else {
    alert("ขออภัยเกิดข้อผิดพลาดกรุณาลองอีกครั้ง")
    router.push({ name: 'ListEvent' })
  }

}
const cateName = ref()
const categoryName = () => {
  cateName.value = event.eventCategory
}
const formatTime = (datetime) => {
  var date = new Date(datetime).toUTCString()
  return date.slice(-12, -7)
}
const formatDate = (datetime) => {
  var date = new Date(datetime)
  date.setHours(date.getHours() - 7)
  return (
    date.getDate() +
    ' | ' +
    date.toLocaleString('en-US', { month: 'short'}) +
    ' | ' +
    date.getFullYear()
  )
}
</script>

<template>
  <div class="modal-wrapper">
    <div class="details">
      <div class="modal-container">
        <div class="modal-body">
          <div class="header flex justify-center">
            <h1>Details</h1>
          </div>
          <table class="details-table">
            <tbody>
              <tr>
                <td>Date</td>
                <td>{{ formatDate(event.eventStartTime) }}</td>
              </tr>
              <tr>
                <td>Event Start</td>
                <td>{{ formatTime(event.eventStartTime) }}</td>
              </tr>
              <tr>
                <td>Duration</td>
                <td>{{ event.eventDuration }} Minutes</td>
              </tr>
              <tr>
                <td>Event Category</td>
                <td>{{ event.eventCategory?.eventCategoryName }}</td>
              </tr>
              <tr>
                <td>Booking By</td>
                <td>{{ event.bookingName }}</td>
              </tr>
              <tr>
                <td>Email</td>
                <td>{{ event.bookingEmail }}</td>
              </tr>
              <tr>
                <td>EventNotes</td>
                <td>{{ event.eventNotes }}</td>
              </tr>
            </tbody>
            <!-- <tbody v-else>
              <tr>
                <h2>
                  เกิดข้อผิดพลาด
                </h2>
              </tr>
            </tbody> -->
          </table>
        </div>
      </div>
      <div class="btnBack flex justify-center pt-5">
        <router-link :to="{ name: 'ListEvent' }"><button class="back">Back</button></router-link>
      </div>
    </div>
  </div>
</template>

<style>
.modal-wrapper {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.details {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding-top: 55px;

}

.modal-container {
  width: 700px;
  margin: 0px auto;
  padding: 20px 30px;
  background-color: rgb(55 65 81);
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.33);
  transition: all 0.3s ease;
  height: auto;
}

.header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.header h1 {
  font-size: 1.5rem;
  font-weight: 600;
  color: #fff;
}

.close-button {
  background: transparent;
  border: 0;
  color: #fff;
  font-size: 1.5rem;
  font-weight: 600;
  line-height: 1;
  padding: 0;
}

.details-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 1rem;
  font-size: 1.2rem;
  font-weight: 500;
  color: #fff;
}

.details-table td {
  padding: 0.5rem;
  border-bottom: 1px solid #fff;
}

.btnBack {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.back {
  background-color: rgb(55 65 81);
  color: #fff;
  border: none;
  border-radius: 5px;
  padding: 10px;
  font-size: 1.2rem;
  cursor: pointer;
}
</style>