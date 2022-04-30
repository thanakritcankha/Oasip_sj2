<script setup>
import { ref } from 'vue'
</script>

<template>
  <div
    class="flex flex-col h-56 w-full bg-zinc-700 rounded-xl shadow-2xl shadow-zinc-900"
  >
    <div class="flex w-full h-12 bg-zinc-800 rounded-t-lg p-2">
      <div class="name">
        <h1>Preeyathorn Chaiyakum</h1>
      </div>

      <div class="pt-2">
        <button class="h-4 w-4 bg-yellow-400 rounded-full mr-2"></button>
        <button class="h-4 w-4 bg-green-500 rounded-full mr-2"></button>
        <button class="h-4 w-4 bg-red-600 rounded-full mr-2"></button>
      </div>
    </div>
    <!-- show data of booking -->
    <div class="p-3">
      <div class="eventsData">
        <!-- <ul>
          <li>Date : 26-04-2022</li>
          <li>Booking name : Preeyathorn Chaiyakum</li>
          <li>Start time : 00:00:00</li>
          <li>Duration : 30 Minutes</li>
          <li>Category : BackEnd Clinic</li>
        </ul> -->
        <ul class="tags">
          <li><button class="tag">Backend Clinic</button></li>
        </ul>

        <h1 class="date">
          <img
            class="icondate"
            src="../../public/date-icon.png"
            alt=""
          />26-04-2022
        </h1>
        <h1 class="duration">30 Minutes</h1>
        <!-- <h1 class="category">Category : BackEnd Clinic</h1> -->
        <h1 class="time">Start : 00:00:00</h1>
      </div>
    </div>
  </div>

  <div class="modal-mask">
    <div class="modal-wrapper">
      <div class="modal-container">
        <div class="modal-body">
          <div class="header">
            <h1>Details</h1>
            <a href="#" class="close-button" @click="close">&#10006;</a>
          </div>
          <table class="details-table">
            <tbody>
              <tr>
                <td>Date</td>
                <td>26-04-2022</td>
              </tr>
              <tr>
                <td>Event Start</td>
                <td>00:00:00</td>
              </tr>
              <tr>
                <td>Duration</td>
                <td>30 Minutes</td>
              </tr>
              <tr>
                <td>Event Category</td>
                <td>Backend Clinic</td>
              </tr>
              <tr>
                <td>Booking By</td>
                <td>Preeyathorn Chaiyakum</td>
              </tr>
              <tr>
                <td>Email</td>
                <td>preeyathorn.c@gmail.com</td>
              </tr>
              <tr>
                <td>EventNotes</td>
                <td>-</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<style>
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
  list-style: none;
  margin: 0;
  overflow: hidden;
  padding: 0;
  width: auto;
  height: auto;
}

.tags li {
  float: left;
  margin-right: 0.5rem;
  margin-bottom: 0.5rem;
}

.tag {
  background: #eee;
  border-radius: 3px 0 0 3px;
  color: black;
  display: inline-block;
  height: 26px;
  line-height: 26px;
  padding: 0 20px 0 23px;
  position: relative;
  margin: 0 10px 10px 0;
  text-decoration: none;
  transition: color 0.2s;
  width: auto;
  height: auto;
}

.tag::before {
  background: rgb(63 63 70);
  border-radius: 10px;
  box-shadow: inset 0 1px rgba(0, 0, 0, 0.25);
  content: '';
  height: 6px;
  left: 10px;
  position: absolute;
  width: 6px;
  top: 10px;
}

.tag::after {
  background: rgb(63 63 70);
  border-bottom: 13px solid transparent;
  border-left: 10px solid #eee;
  border-top: 13px solid transparent;
  content: '';
  position: absolute;
  right: 0;
  top: 0;
}

.tag:hover {
  background-color: #ddd;
  color: #444;
  cursor: pointer;
}

.tag:hover::after {
  border-left-color: #ddd;
}

.time {
  font-size: 1.2rem;
  font-weight: 500;
  color: #fff;
  margin-left: auto;
}

.duration {
  font-size: 1.2rem;
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
}
</style>
