<script setup>
import { ref } from 'vue'

const prop = defineProps({
  mask: Object,
})

const showDetail = ref(false);

const btnShowDetail = () => {
  showDetail.value = true;
}
const btnCloseDetail = () => {
  showDetail.value = false;
}


const formatTime = (datetime) => {
  return datetime.slice(-8)
}

const formatDate = (datetime) => {
  var date = new Date(datetime)
  return date
}

</script>

<template>
  <!-- card container -->
  <div class="max-w-lg shadow-lg rounded overflow-hidden m-4 sm:flex bg-zinc-700">
    <div
      class="h-60 sm:h-auto sm:w-38 md:w-40 flex-none bg-cover bg-center rounded rounded-t sm:rounded sm:rounded-l flex text-center items-center overflow-hidden"
      style="background-image: url('https://unsplash.it/804/800')">
      <div class=" m-auto flex shadow-2xl">
        <div class="calendar sm:w-30">
          <p id="monthName">
            {{
                formatDate(prop.mask.eventStartTime).toLocaleString('th-TH', {
                  month: "long"
                })
            }}</p>
          <p id="dayName">
            {{ formatDate(prop.mask.eventStartTime).toLocaleString('th-TH',
                { weekday: "long" })
            }}</p>
          <p id="dayNumber">{{ formatDate(prop.mask.eventStartTime).getDate() }}</p>
          <p id="year">{{ formatDate(prop.mask.eventStartTime).getFullYear() }}</p>
<<<<<<< HEAD
        </div>
      </div>
    </div>
    <!-- card-content -->
    <div class="px-2 py-3 w-full relative">
      <div class="flex justify-end pb-3">
        <button class="h-4 w-4 bg-yellow-400 rounded-full mr-2"></button>
        <button class="h-4 w-4 bg-green-500 rounded-full mr-2" @click="btnShowDetail()"></button>
        <button class="h-4 w-4 bg-red-600 rounded-full mr-2"></button>
      </div>
      <div class="px-5">
        <h2 class="mb-2 font-black text-xl "> {{ prop.mask.bookingName }} </h2>
        <!-- <p class="mb-4 text-grey-dark text-sm">
          Learning Tailwind is incredibly easy. The team has done a wonderful job with the documentation. This
          is
          pretty amazing, I must say.
        </p> -->

        <ul class="tags flex items-center">
          <img src="public/icons/subfolder.png" alt="subfolder" class="w-8 pr-2">
          <li><button class="tag font-Mochiy ">{{ prop.mask.eventCategory.eventCategoryName }}</button></li>
        </ul>
        <div class="time font-bold text-lg flex items-center">
          <img src="public/icons/chronometer.png" alt="chronometer" class="w-8">
          <span class="pl-3 text-xl">Start : {{
              formatTime(prop.mask.eventStartTime)
          }}</span>
        </div>

      </div>
      <h1 class="duration text-white mt-2 flex justify-end font-Mochiy">
        <div class="px-4 py-1 bg-orange-600 rounded-full">{{
            prop.mask.eventDuration
        }} Minutes</div>
      </h1>
    </div>
  </div>


  <!-- showDetail -->
  <div class="modal-mask" v-show="showDetail">
    <div class="modal-wrapper">
      <div class="modal-container">
        <div class="modal-body">
          <div class="header">
            <h1>Details</h1>
            <button class="close-btn" @click="btnCloseDetail">&#10006;</button>
          </div>
          <table class="details-table">
            <tbody>
              <tr>
                <td>Date</td>
                <td>26-04-2022</td>
              </tr>
              <tr>
                <td>Event Start</td>
                <td>{{ formatTime(prop.mask.eventStartTime) }}</td>
              </tr>
              <tr>
                <td>Duration</td>
                <td>{{ prop.mask.eventDuration }} Minutes</td>
              </tr>
              <tr>
                <td>Event Category</td>
                <td>{{ prop.mask.eventCategory.eventCategoryName }}</td>
              </tr>
              <tr>
                <td>Booking By</td>
                <td>{{ prop.mask.bookingName }}</td>
              </tr>
              <tr>
                <td>Email</td>
                <td>{{ prop.mask.bookingEmail }}</td>
              </tr>
              <tr>
                <td>EventNotes</td>
                <td>{{ prop.mask.eventNotes }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
=======
        </div>
      </div>
    </div>
    <!-- card-content -->
    <div class="px-2 py-3 w-full relative">
      <div class="flex justify-end pb-3">
        <button class="h-4 w-4 bg-yellow-400 rounded-full mr-2"></button>
        <button class="h-4 w-4 bg-green-500 rounded-full mr-2" @click="btnShowDetail()"></button>
        <button class="h-4 w-4 bg-red-600 rounded-full mr-2"></button>
      </div>
      <div class="px-5">
        <h2 class="pb-2 font-black text-xl border-b-2"> {{ prop.mask.bookingName }} </h2>
        <!-- <p class="mb-4 text-grey-dark text-sm">
          Learning Tailwind is incredibly easy. The team has done a wonderful job with the documentation. This
          is
          pretty amazing, I must say.
        </p> -->
        <ul class="tags grid justify-items-center  items-center text-md">
          <li class="text-xl w-max mt-1">Category :</li>
          <li><button class="tag font-Mochiy flex break-words"> {{ prop.mask.eventCategory.eventCategoryName }}</button>
          </li>
        </ul>
        <div class="time font-bold text-lg flex items-center">
          <img src="../assets/icons/chronometer.png" alt="chronometer" class="w-8">
          <span class="pl-3 text-xl">Start : {{
              formatTime(prop.mask.eventStartTime)
          }}</span>
        </div>

      </div>
      <h1 class="duration text-white mt-2 flex justify-end font-Mochiy">
        <div class="px-4 py-1 bg-orange-600 rounded-full">{{
            prop.mask.eventDuration
        }} Minutes</div>
      </h1>
    </div>
  </div>


  <!-- showDetail -->
  <div class="modal-mask" v-show="showDetail">
    <div class="modal-wrapper">
      <div class="modal-container">
        <div class="modal-body">
          <div class="header">
            <h1>Details</h1>
            <button class="close-btn" @click="btnCloseDetail">&#10006;</button>
          </div>
          <table class="details-table">
            <tbody>
              <tr>
                <td>Date</td>
                <td>26-04-2022</td>
              </tr>
              <tr>
                <td>Event Start</td>
                <td>{{ formatTime(prop.mask.eventStartTime) }}</td>
              </tr>
              <tr>
                <td>Duration</td>
                <td>{{ prop.mask.eventDuration }} Minutes</td>
              </tr>
              <tr>
                <td>Event Category</td>
                <td>{{ prop.mask.eventCategory.eventCategoryName }}</td>
              </tr>
              <tr>
                <td>Booking By</td>
                <td>{{ prop.mask.bookingName }}</td>
              </tr>
              <tr>
                <td>Email</td>
                <td>{{ prop.mask.bookingEmail }}</td>
              </tr>
              <tr>
                <td>EventNotes</td>
                <td>{{ prop.mask.eventNotes }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
>>>>>>> main
  </div>
</template>

<style scoped>
.calendar {
  position: relative;
  width: 120px;
  background: #fff;
  text-align: center;
  border-radius: 0.5em;
  overflow: hidden;
  /* -webkit-box-reflect: below 0px linear-gradient(transparent, transparent, rgba(0, 0, 0, 0.4)); */
}

#monthName {
  position: relative;
  padding: 3px 8px;
  background: #ff6331;
  color: #fff;
  font-size: 20px;
  font-weight: 700;
}

#dayName {
  margin-top: 10px;
  font-size: 18px;
  font-weight: 300;
  color: #999;
}


#dayNumber {
  margin-top: 0px;
  line-height: 1em;
  font-size: 60px;
  font-weight: 700;
  color: #333;
}

#year {
  margin-bottom: 10px;
  font-size: 18px;
  font-weight: 500;
  color: #999;
}

.name {
  flex: auto;
}

.name h1 {
  font-size: 1.5rem;
  font-weight: 600;
  color: #fff;
}

.eventsData {
  display: flex;
  flex-direction: column;
}

.eventsData ul {
  list-style: none;
  padding: 0;
  margin: 0;
}

.eventsData ul li {
  font-size: 1.2rem;
  font-weight: 500;
  color: #fff;
  margin-bottom: 0.5rem;
}

.icondate {
  width: 30px;
  height: 30px;
  margin-right: 0.5rem;
}

.date {
  font-size: 1.5rem;
  font-weight: 600;
  color: #fff;
  border-bottom: 1px solid #fff;
  padding-bottom: 0.5rem;
  display: flex;
}

.tags {
<<<<<<< HEAD
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: center;
  margin-bottom: 1rem;
  font-size: small;
=======
  /* display: flex;
  flex-direction: row;
  flex-wrap: wrap; */
  /* justify-content: flex-start; */
  align-items: center;
  margin-bottom: 1rem;
>>>>>>> main
}

.tag {
  background: #ff6331;
  color: #fff;
  padding: 0.5rem 1rem;
  margin-right: 0.5rem;
  border-radius: 1rem;
<<<<<<< HEAD

=======
>>>>>>> main
}

.time {
  margin-left: auto;
}


.duration {
  font-size: 0.8rem;
  font-weight: 500;
  color: #fff;
  margin-left: auto;
  padding-bottom: 0.5rem;
}

.modal-mask {
  position: fixed;
  z-index: 9998;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: table;
  transition: opacity 0.3s ease;
}

.modal-wrapper {
  display: table-cell;
  vertical-align: middle;
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
</style>
