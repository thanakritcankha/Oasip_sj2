<script setup>
</script>

<template>
  <div
    class="flex flex-col h-56 w-full bg-zinc-700 rounded-xl shadow-2xl shadow-zinc-900"
  >
    <div class="flex w-full h-12 bg-zinc-800 rounded-t-lg p-2">
      <div class="name">
        <h1>Preeyathorn Chaiyakum</h1>
      </div>

      <div class="button">
        <button class="h-4 w-4 bg-yellow-400 rounded-full mr-2"></button>
        <button class="h-4 w-4 bg-green-500 rounded-full mr-2"></button>
        <button class="h-4 w-4 bg-red-600 rounded-full mr-2"></button>
      </div>
    </div>
    <!-- show data of booking -->
    <div class="p-3">
      <div class="eventsData">
        <ul>
          <li>Date : 26-04-2022</li>
          <!-- <li>Booking name : Preeyathorn Chaiyakum</li> -->
          <li>Start time : 00:00:00</li>
          <li>Duration : 30 Minutes</li>
          <li>Category : BackEnd Clinic</li>
        </ul>
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
</style>
