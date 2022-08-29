<script setup>
import { onBeforeMount, ref } from 'vue';
import { useRoute } from 'vue-router';
import router from '../router';
import UserDataService from '../services/UserDataService';

let { params } = useRoute();

onBeforeMount(async () => {
  await getDetailUser(params.id);
  // setTimeout(() => {
  //   fadein.value = true;
  // });
});

const detail = ref({});
const getDetailUser = async (id) => {
  const res = await UserDataService.retrieveUser(id);
  if (res.status == 200) {
    detail.value = await res.json();
  } else {
    alert('ขออภัยเกิดข้อผิดพลาดกรุณาลองอีกครั้ง');
    router.push({ name: 'Users' });
  }
};

// const getDateM = (date) => {
//   // console.log(date);
//   var dd = String(date.getDate()).padStart(2, '0');
//   var mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
//   var yyyy = date.getFullYear();
//   var myDate = yyyy + '-' + mm + '-' + dd;
//   return myDate;
// };

const updateUser = async () => {
  var newData = {
    id: params.id,
    name: editUserName.value.trim(),
    email: editEmail.value.trim(),
    role: editRole.value,
  };

  if (
    newData.name != detail.value.name ||
    newData.email != detail.value.email ||
    newData.role != detail.value.role
  ) {
    console.log('dsalkda');
    const res = await UserDataService.updateUser(detail.value.id, newData);
    if (res.status == 400) {
      alert('This name or email are already used.');
    } else {
      await getDetailUser(params.id);
    }
  }

  // console.log(newData);
  // console.log(detail.value);

  editMode.value = false;
};

const editMode = ref(false);
const editUserName = ref();
const editEmail = ref();
const editRole = ref();

const editModeOn = (detail) => {
  editMode.value = true;
  editUserName.value = detail.name;
  editEmail.value = detail.email;
  editRole.value = detail.role;
};
const editModeOff = () => {
  editMode.value = false;
};
</script>

<template>
  <div
    class="min-w-full flex justify-center pt-10 transition ease-out duration-1000"
  >
    <div class="px-6 py-6 bg-white rounded-xl w-2/3">
      <div>
        <div class="text-3xl w-full text-center">User Detail</div>
        <div
          class="text-gray-400 text-xl rounded p-2 text-center"
          v-if="!editMode"
        >
          {{ detail.name }}
        </div>
        <div v-else>
          <textarea
            type="text"
            class="text-2xl text-center rounded w-full p-2 bg-slate-200 text-slate-500 border-slate-200 shadow"
            maxlength="100"
            v-model="editUserName"
          />
          <br />
          <!-- <span class="text-red-500 mt-2">{{ errorName }}</span> -->
        </div>
      </div>
      <div class="grid">
        <div class="flex flex-col">
          <div>
            <label class="block text-xl font-medium text-gray-700">Email</label>
            <div
              class="text-gray-400 text-lg rounded w-96 p-2"
              v-if="!editMode"
            >
              {{ detail.email }}
            </div>
            <div v-else>
              <input
                type="text"
                class="text-2xl text-center rounded w-full p-2 bg-slate-200 text-slate-500 border-slate-200 shadow"
                maxlength="50"
                v-model="editEmail"
              />
              <br />
              <!-- <span class="text-red-500 mt-2">{{ errorName }}</span> -->
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700">Role </label>
            <div
              class="text-gray-400 text-lg rounded w-full p-2"
              v-if="!editMode"
            >
              {{ detail.role }}
            </div>
            <div v-else>
              <select
                name="role"
                id="role"
                v-model="editRole"
                class="shadow border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:border-orange-600"
              >
                <option disabled selected value>Select Role</option>
                <option>student</option>
                <option>admin</option>
                <option>lecturer</option>
              </select>
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700"
              >Create On
            </label>
            <div class="text-gray-400 text-lg rounded w-full p-2">
              {{ detail.createOn }}
            </div>
            <div>
              <label class="block text-xl font-medium text-gray-700"
                >Update On
              </label>
              <div class="text-gray-400 text-lg rounded w-full p-2">
                {{ detail.updateOn }}
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="mt-6 flex">
        <button class="flex items-center mr-10">
          <router-link
            :to="{ path: '/listusers', name: 'ListUsers' }"
            class="flex items-center"
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="40"
              height="40"
              fill="currentColor"
              class="bi bi-arrow-left-circle-fill fill-indigo-500 pr-2 animate-pulse"
              viewBox="0 0 16 16"
            >
              <path
                d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zm3.5 7.5a.5.5 0 0 1 0 1H5.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L5.707 7.5H11.5z"
              />
            </svg>
            Back
          </router-link>
        </button>
        <button
          class="flex items-center rounded-xl bg-indigo-500 px-2 text-white"
          v-if="!editMode"
          @click="editModeOn(detail)"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-pen-fill mr-1"
            viewBox="0 0 16 16"
          >
            <path
              d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"
            />
          </svg>
          Edit
        </button>
        <button
          class="flex items-center rounded-xl bg-green-500 px-2 text-white mr-10"
          v-else
          @click="updateUser()"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-pen-fill mr-1"
            viewBox="0 0 16 16"
          >
            <path
              d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"
            />
          </svg>
          Save
        </button>
        <button
          class="flex items-center rounded-xl bg-red-500 px-2 text-white"
          v-if="editMode"
          @click="editModeOff()"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="16"
            height="16"
            fill="currentColor"
            class="bi bi-pen-fill mr-1"
            viewBox="0 0 16 16"
          >
            <path
              d="m13.498.795.149-.149a1.207 1.207 0 1 1 1.707 1.708l-.149.148a1.5 1.5 0 0 1-.059 2.059L4.854 14.854a.5.5 0 0 1-.233.131l-4 1a.5.5 0 0 1-.606-.606l1-4a.5.5 0 0 1 .131-.232l9.642-9.642a.5.5 0 0 0-.642.056L6.854 4.854a.5.5 0 1 1-.708-.708L9.44.854A1.5 1.5 0 0 1 11.5.796a1.5 1.5 0 0 1 1.998-.001z"
            />
          </svg>
          Cancel
        </button>
      </div>
    </div>
  </div>
</template>

<style></style>
