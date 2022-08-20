<script setup>
import NavMain from './components/NavMain.vue';
</script>

<template>
  <div class="h-full w-full relative page">
    <NavMain />
    <div class="mt-16">
      <router-view></router-view>
    </div>
  </div>

  <!-- <div class="h-screen w-screen">
    <NavMain />
    <div class="p-2">
      <router-view></router-view>
    </div>
  </div> -->
</template>

<style>
body {
  /* font-family: 'Mochiy Pop P One', sans-serif; */
  font-family: 'Mitr', sans-serif;
  height: 100%;
  margin: 0;
  background-repeat: no-repeat;
  background-attachment: fixed;
  /* background-image: linear-gradient(
    to right top,
    #051937,
    #004d7a,
    #008793,
    #00bf72,
    #a8eb12
  ); */

  background: #1488cc; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to right,
    #2b32b2,
    #1488cc
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to right,
    #2b32b2,
    #1488cc
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.color2 {
  background: #00d2ff; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to right,
    #3a7bd5,
    #00d2ff
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to right,
    #3a7bd5,
    #00d2ff
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}
.color1 {
  background: #6441a5; /* fallback for old browsers */
  background: -webkit-linear-gradient(
    to right,
    #2a0845,
    #6441a5
  ); /* Chrome 10-25, Safari 5.1-6 */
  background: linear-gradient(
    to right,
    #2a0845,
    #6441a5
  ); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
}

.page {
  height: 100vh;
}
.page::before {
  content: '';
  position: fixed;
  width: 300%;
  height: 100%;
  background-color: #ffffff;
  left: 50%;
  top: 50%;
  opacity: 10%;
  transform: translateX(-50%) skew(0, -10deg);
  /* border-radius: 50%; */
  border-radius: 42% 58% 70% 30% / 45% 45% 55% 55%;
  z-index: -1;
  animation: waving 5s ease-in-out infinite alternate;
}
@keyframes waving {
  from {
    transform: translateX(-50%) skew(0, -5deg);
  }
  to {
    transform: translateX(-30%) skew(10deg, 0);
  }
}
</style>
