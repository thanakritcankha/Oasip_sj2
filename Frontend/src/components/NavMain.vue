<script setup>
import router from '../router';
const btn = () => {
  const menu = document.querySelector('#menu');
  menu.classList.toggle('hidden');
};
const goHome = () => {
  router.push({ path: '/', name: 'Home' });
};
</script>

<template>
  <nav
    class="top-0 z-100 fixed w-full flex flex-wrap items-center justify-between w-full py-4 md:py-0 px-4 text-lg"
  >
    <div class="ml-28 flex items-center cursor-pointer" @click="goHome()">
      <img src="../assets/xiao-white.png" class="w-20 h-30" alt="" />
      <p class="text-xl text-white -ml-3">จตุรเทพ</p>
    </div>

    <svg
      xmlns="http://www.w3.org/2000/svg"
      id="menu-button"
      class="h-6 w-6 cursor-pointer md:hidden block"
      fill="none"
      viewBox="0 0 24 24"
      stroke="currentColor"
      @click="btn()"
    >
      <path
        stroke-linecap="round"
        stroke-linejoin="round"
        stroke-width="2"
        d="M4 6h16M4 12h16M4 18h16"
      />
    </svg>

    <div class="hidden w-full md:flex md:items-center md:w-auto" id="menu">
      <ul
        class="pt-4 text-base text-gray-700 md:flex md:justify-between md:pt-0"
      >
        <li class="gc3">
          <router-link
            class="md:p-4 py-2 block hover:text-purple-400 text-white"
            :to="{ path: '/listevents', name: 'ListEvent' }"
            >List Event</router-link
          >
        </li>
        <li>
          <router-link
            class="md:p-4 py-2 block hover:text-purple-400 text-white"
            :to="{ path: '/listcategory', name: 'ListCategory' }"
            >List Event Category</router-link
          >
        </li>
        <li>
          <router-link
            class="md:p-4 py-2 block hover:text-purple-400 text-white"
            :to="{ path: '/CreateEvent', name: 'CreateEvent' }"
            >Create Event</router-link
          >
        </li>
        <li>
          <router-link
            class="md:p-4 py-2 block hover:text-purple-400 text-white"
            :to="{ path: '/listusers', name: 'ListUsers' }"
            >Users</router-link
          >
        </li>
        <li>
          <router-link
            class="md:p-4 py-2 block hover:text-purple-400 text-white"
            :to="{ path: '/signUp', name: 'SignUp' }"
            >SignUp</router-link
          >
        </li>
        <li>
          <router-link
            class="md:p-4 py-2 block hover:text-purple-400 text-white"
            :to="{ path: '/signIn', name: 'SignIn' }"
            >SignIn</router-link
          >
        </li>
        <li>
          <router-link
            class="md:p-4 py-2 block hover:text-purple-400 text-white"
            :to="{ path: '/about', name: 'About' }"
            >สมาชิกสภาแห่งเทพ</router-link
          >
        </li>
        <!-- <li>
          <a class="md:p-4 py-2 block hover:text-purple-400 text-white" href="#"
            >Customers</a
          >
        </li>
        <li>
          <a class="md:p-4 py-2 block hover:text-purple-400 text-white" href="#"
            >Blog</a
          >
        </li>
        <li>
          <a class="md:p-4 py-2 block hover:text-purple-400 text-white" href="#"
            >Sign Up</a
          >
        </li> -->
      </ul>
    </div>
  </nav>
</template>

<style scoped>
ul {
  list-style: none;
  display: table;
}
li {
  display: table-cell;
  position: relative;
  padding-bottom: 10px 0;
}
a {
  color: #fff;
  text-transform: uppercase;
  text-decoration: none;

  display: inline-block;
  position: relative;
}
a:after {
  background: none repeat scroll 0 0 transparent;
  bottom: 1;
  content: '';
  display: block;
  height: 3px;
  left: 50%;
  position: absolute;
  background: #fff;
  transition: width 0.3s ease 0s, left 0.3s ease 0s;
  width: 0;
}
a:hover:after {
  width: 100%;
  left: 0%;
}
@media screen and (max-height: 300px) {
  ul {
    margin-top: 40px;
  }
}
</style>
