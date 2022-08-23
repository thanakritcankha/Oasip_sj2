<script setup>
import { onBeforeMount, ref } from 'vue';
import UserDataService from '../services/UserDataService';
onBeforeMount(async () => {
  // await getAllUser();
  // console.log(users.value);
});

const users = ref();
// const getAllUser = async () => {
//   const res = await UserDataService.retrieveAllUser();
//   users.value = await res.json();
// };

const userName = ref('');
const userEmail = ref('');
const userRole = ref('');

const errorName = ref();
const errorEmail = ref();

const submitUser = async () => {
  var newUser = {
    name: userName.value.trim(),
    email: userEmail.value.trim(),
    role: userRole.value,
  };
  console.log(newUser);
  const res = await UserDataService.createUser(newUser);
  if (res.status == 400) {
    alert('This name or email are already used.');
  } else {
    reset();
  }
};

const EmailTrim = () => {
  userEmail.value = userEmail.value.trim();
};

const reset = () => {
  userName.value = '';
  userEmail.value = '';
  userRole.value = '';
};
</script>

<template>
  <div class="bg-grey-lighter mt-52 flex flex-col">
    <form
      class="container max-w-sm mx-auto flex-1 flex flex-col items-center justify-center px-2"
      @submit="submitUser()"
      onsubmit="return false;"
    >
      <div class="bg-white px-6 py-8 rounded shadow-md text-black w-full">
        <h1 class="mb-8 text-3xl text-center">Sign up</h1>
        <input
          required
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="name"
          maxlength="100"
          v-model="userName"
          placeholder="Username"
        />
        <span class="text-red-500 mt-2">{{ errorName }}</span>
        <input
          required
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="email"
          @change="EmailTrim()"
          pattern="[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$"
          maxlength="50"
          v-model="userEmail"
          placeholder="Email"
        />
        <span class="text-red-500 mt-2">{{ errorEmail }}</span>
        <!-- <input
          type="password"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="password"
          placeholder="Password"
        /> -->
        <!-- <input
          type="password"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="confirm_password"
          placeholder="Confirm Password"
        /> -->
        <div class="mb-6">
          <label
            for="category"
            class="block text-gray-700 text-lg font-bold mb-2"
            >Role :
          </label>
          <select
            name="category"
            id="category"
            v-model="userRole"
            class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-orange-600"
          >
            <option disabled selected value>Select Role</option>
            <option>student</option>
            <option>admin</option>
            <option>lecturer</option>
          </select>
        </div>
        <button
          type="submit"
          class="w-full text-center py-3 rounded bg-green text-black hover:bg-green-dark focus:outline-none my-1"
        >
          Create Account
        </button>
        <router-link :to="{ name: 'ListUsers' }">
          <button
            class="w-full text-center py-3 rounded bg-green text-black hover:bg-green-dark focus:outline-none my-1"
            @click=""
          >
            Cancel
          </button>
        </router-link>
      </div>

      <!-- <div class="text-grey-dark mt-6">
        Already have an account?        
        <router-link
            class="no-underline border-b border-blue text-blue"
            :to="{ path: '/signIn', name: 'SignIn' }"
            > Log in </router-link
          >.
      </div> -->
    </form>
  </div>
</template>

<style></style>
