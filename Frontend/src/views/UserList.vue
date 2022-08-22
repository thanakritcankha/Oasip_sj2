<script setup>
import { onBeforeMount, ref } from 'vue';
import router from '../router';
import UserDataService from '../services/UserDataService';
import User from '../components/User.vue';

onBeforeMount(async () => {
  await listUsers();
  // setTimeout(() => {
  //   fade.value = true
  // }, 400)
});

const users = ref([]);

const listUsers = async () => {
  const res = await UserDataService.retrieveAllUser();
  users.value = await res.json();
  console.log(users.value);
};

const deleteUser = async (id) => {
  const res = await UserDataService.deleteUser(id);
  res.status === 200
    ? (users.value = users.value.filter((value) => value.id !== id))
    : alert('Error to delete this user');
};

const confirmDelete = (id) => {
  console.log(id);
  let text = 'Do you want to delete the user?';
  if (confirm(text) == true) {
    deleteUser(id);
  }
};
</script>

<template>
  <div class="container mx-auto px-4 sm:px-8">
    <div class="py-8">
      <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
        <div
          class="inline-block min-w-full shadow-md rounded-lg overflow-hidden"
        >
          <table class="min-w-full leading-normal">
            <thead>
              <tr>
                <th
                  class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-700 uppercase tracking-wider"
                >
                  Name
                </th>
                <th
                  class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-700 uppercase tracking-wider"
                >
                  Email
                </th>
                <th
                  class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-700 uppercase tracking-wider"
                >
                  Role
                </th>
                <th
                  class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-left text-xs font-semibold text-gray-700 uppercase tracking-wider"
                ></th>
              </tr>
            </thead>
            <tbody>
              <tr
                class="rounded overflow-hidden shadow-lg bg-white"
                v-for="user in users"
                v-if="users.length > 0"
              >
                <User :mask="user" @deleteUser="confirmDelete($event)" />
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <!-- <div
    class="p-20 grid grid-cols-1 sm:grid-cols-1 md:grid-cols-3 lg:grid-cols-3 xl:grid-cols-3 gap-5"
  >
    <div
      class="rounded overflow-hidden shadow-lg bg-white"
      v-for="user in users"
      v-if="users.length > 0"
    >
      <User :mask="user" @deleteUser="confirmDelete($event)" />
    </div>
    <div v-else class="grid place-content-center h-full text-white text-3xl">
      No Users
    </div>
  </div> -->
</template>

<style></style>
