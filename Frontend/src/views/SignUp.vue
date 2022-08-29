<script setup>
import { onBeforeMount, ref } from 'vue'
import UserDataService from '../services/UserDataService'
onBeforeMount(async () => {
  // await getAllUser();
  // console.log(users.value);
})

// const getAllUser = async () => {
//   const res = await UserDataService.retrieveAllUser();
//   users.value = await res.json();
// };

const userName = ref('')
const userEmail = ref('')
const userPassword = ref('')
const userRole = ref('')

const errorName = ref()
const errorEmail = ref()

// const checkEmail =

const submitUser = async () => {
  if (password.value == confirm_pass.value) {
    var newUser = {
      name: userName.value.trim(),
      email: userEmail.value.trim(),
      password: password.value,
      role: userRole.value,
    }
    console.log(newUser)
    const res = await UserDataService.createUser(newUser)
    if (res.status == 400) {
      alert('This name or email are already used.')
    } else {
      reset()
    }
  }
}

const reset = () => {
  userName.value = ''
  userEmail.value = ''
  userRole.value = ''
  userPassword.value = ''
  password.value = ''
  confirm_pass.value = ''
  error_message.value = false
}

const password = ref('')
const confirm_pass = ref('')
const error_message = ref(false)

const EmailTrim = () => {
  userEmail.value = userEmail.value.trim()
}

const checkPassword = () => {
  if (password.value != '' && confirm_pass.value != '') {
    if (password.value != confirm_pass.value) {
      error_message.value = true
    } else {
      error_message.value = false
    }
  }
}
</script>

<template>
  <div class="bg-grey-lighter mt-24 flex flex-col">
    <form
      class="container max-w-sm mx-auto flex-1 flex flex-col items-center justify-center px-2"
      @submit="submitUser()"
      onsubmit="return false;"
    >
      <div class="bg-white px-6 py-8 rounded shadow-md text-black w-full">
        <h1 class="mb-8 text-3xl text-center">Sign up</h1>
        <label for="nameform">Name</label>
        <input
          required
          id="nameform"
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="name"
          maxlength="100"
          v-model="userName"
          placeholder="E.g. John Washington"
        />
        <span class="text-red-500 mt-2">{{ errorName }}</span>
        <label for="emailform">Email</label>
        <input
          required
          id="emailform"
          type="text"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="email"
          @change="EmailTrim()"
          pattern="[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$"
          maxlength="50"
          v-model="userEmail"
          placeholder="john@example.com"
        />
        <span class="text-red-500 mt-2">{{ errorEmail }}</span>
        <label for="pwdform">Password</label>
        <input
          id="pwdform"
          type="password"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="password"
          pattern=".{8,15}"
          placeholder="password"
          v-model="password"
          @change="checkPassword()"
        />
        <label for="checkpwd">Confirm Password</label>
        <input
          id="checkpwd"
          type="password"
          class="block border border-grey-light w-full p-3 rounded mb-4"
          name="confirm_password"
          placeholder="Confirm Password"
          pattern=".{8,15}"
          v-model="confirm_pass"
          @change="checkPassword()"
        />

        <span v-show="error_message" class="text-red-600"
          >The password DOES NOT match</span
        >
        <span
          v-show="
            (password.length < 8 || password.length > 14) && password != ''
          "
          class="text-red-600"
          >between 8 - 14 charaters</span
        >

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
    </form>
  </div>
</template>

<style></style>
