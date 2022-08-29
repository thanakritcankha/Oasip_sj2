<script setup>
import { ref } from 'vue';
import LoginService from '../services/AuthenticationService';
const email = ref('');
const password = ref('');

const submitLogin = async () => {
  var requiredUser = {
    email: email.value.trim(),
    password: password.value,
  };
  console.log(requiredUser);
  const res = await LoginService.logInUser(requiredUser);
  if (res.status == 404) {
    alert('A user with the specified email DOES NOT exist');
  } else if (res.status == 401) {
    alert('Password NOT Matched');
  } else {
    alert('Password Matched');
  }
};

const EmailTrim = () => {
  email.value = email.value.trim();
};
</script>

<template>
  <div class="bg-grey-lighter min-h-screen flex flex-col">
    <form
      class="container max-w-sm mx-auto flex-1 flex flex-col items-center justify-center px-2"
      @submit="submitLogin()"
      onsubmit="return false;"
    >
      <div class="bg-white px-6 py-8 rounded shadow-md text-black w-full">
        <h1 class="mb-8 text-3xl text-center">Sign In</h1>
        <input
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="email"
          placeholder="Email"
          @change="EmailTrim()"
          pattern="[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$"
          v-model="email"
        />

        <input
          type="password"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="password"
          placeholder="Password"
          v-model="password"
        />

        <button
          type="submit"
          class="w-full text-center py-3 rounded bg-green text-black hover:bg-green-dark focus:outline-none my-1"
        >
          Log In
        </button>
      </div>

      <div class="text-grey-dark mt-6">
        Don't have an account?
        <router-link
          class="no-underline border-b border-blue text-blue"
          :to="{ path: '/signUp', name: 'SignUp' }"
        >
          Create yours now. </router-link
        >.
      </div>
    </form>
  </div>
</template>

<style></style>
