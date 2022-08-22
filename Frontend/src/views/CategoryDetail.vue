<script setup>
import { onBeforeMount, ref } from 'vue';
import { useRoute } from 'vue-router';
import router from '../router';
import EventCategoryDataService from '../services/EventCategoryDataService';

let { params } = useRoute();

onBeforeMount(async () => {
  await getDetailCategory(params.id);
  await listCategory();
  // console.log(categories.value);

  setTimeout(() => {
    fadein.value = true;
  });
});

const fadein = ref();

const editMode = ref(false);
const editCategoryName = ref();
const editCategoryDescription = ref();
const editDuration = ref();
const editModeOn = () => {
  //   console.log(category.value);
  editMode.value = true;
  editCategoryName.value = category.value.eventCategoryName;
  editCategoryDescription.value = category.value.eventCategoryDescription;
  editDuration.value = category.value.eventCategoryDuration;
  //   console.log(editCategoryName.value);
};
const editModeOff = () => {
  errorName.value = '';
  errorduration.value = '';
  checkName.value = true;
  editMode.value = false;
};

const category = ref({});
const getDetailCategory = async (id) => {
  const res = await EventCategoryDataService.retrieveCategory(id);
  if (res.status == 200) category.value = await res.json();
  else {
    alert('ขออภัยเกิดข้อผิดพลาดกรุณาลองอีกครั้ง');
    router.push({ name: 'ListCategory' });
  }
};
const errorduration = ref();
const checkduration = () => {
  if (editDuration.value > 0 && editDuration.value <= 480)
    errorduration.value = '';
  else errorduration.value = 'duration must between 1 and 480.';
};
const save = async (categpryid) => {
  if (
    editDuration.value > 0 &&
    editDuration.value <= 480 &&
    editCategoryName.value != '' &&
    checkName.value
  ) {
    const categorys = {
      id: categpryid,
      eventCategoryName: editCategoryName.value,
      eventCategoryDescription: editCategoryDescription.value,
      eventCategoryDuration: editDuration.value,
    };
    if (
      categorys.eventCategoryName != category.value.eventCategoryName ||
      categorys.eventCategoryDescription !=
        category.value.eventCategoryDescription ||
      categorys.eventCategoryDuration != category.value.eventCategoryDuration
    ) {
      const res = await EventCategoryDataService.updateEvent(
        categpryid,
        categorys
      );
      if (res.status != 400) {
        editModeOff();
        await getDetailCategory(params.id);
      } else {
        // console.log('error update');
        alert('error update' + res.statusText);
      }
    } else {
      editModeOff();
    }
  }
};

const errorName = ref();
const categories = ref();
const checkName = ref(true);
const listCategory = async () => {
  const res = await EventCategoryDataService.retrieveAllCategoryForFilter();
  categories.value = await res.json();
  categories.value = categories.value.filter((value) => {
    return !(value.eventCategoryName == category.value.eventCategoryName);
  });
  // console.log(categories.value);
}; //listAllEvent
const checkCategoryName = async () => {
  if (editCategoryName.value != '') {
    editCategoryName.value = editCategoryName.value.replace(/^\s+|\s+$/gm, '');
    // console.log(editCategoryName.value);
    // console.log(categories.value);
    var result = categories.value.filter((value) => {
      // console.log(value.eventCategoryName.toLowerCase());
      return (
        value.eventCategoryName.toLowerCase() ==
        editCategoryName.value.toLowerCase()
      );
    });
    if (result.length > 0) {
      errorName.value = 'The eventCategoryName is NOT unique.';
      checkName.value = false;
    } else {
      errorName.value = '';
      checkName.value = true;
    }
  } else {
    errorName.value = 'The eventCategoryName is not null';
    checkName.value = false;
  }
};
</script>

<template>
  <div
    class="min-w-full flex justify-center pt-10 transition ease-out duration-1000"
    :class="{ 'translate-x-0': fadein, 'translate-x-full': !fadein }"
  >
    <div class="px-6 py-6 bg-white rounded-xl">
      <div>
        <div class="text-3xl w-full text-center">Category Detail</div>
        <div
          class="text-gray-400 text-2xl rounded w-full p-2 text-center"
          v-if="!editMode"
        >
          {{ category.eventCategoryName }}
        </div>
        <div v-else>
          <input
            type="text"
            class="text-2xl text-center rounded w-full p-2 bg-slate-200 text-slate-500 border-slate-200 shadow"
            maxlength="100"
            @keyup="checkCategoryName()"
            v-model="editCategoryName"
          />
          <br />
          <span class="text-red-500 mt-2">{{ errorName }}</span>
        </div>
      </div>
      <div class="grid">
        <div class="flex flex-col">
          <div>
            <label class="block text-xl font-medium text-gray-700"
              >Category Description</label
            >
            <div
              class="text-gray-400 text-lg rounded w-96 p-2"
              v-if="!editMode"
            >
              {{ category.eventCategoryDescription }}
            </div>
            <div class="text-gray-400 text-lg rounded w-96 p-2" v-else>
              <textarea
                rows="5"
                class="text-lg rounded w-full p-2 bg-slate-200 text-slate-500 border-slate-200 shadow"
                maxlength="500"
                v-model="editCategoryDescription"
              ></textarea>
            </div>
          </div>
          <div>
            <label class="block text-xl font-medium text-gray-700"
              >Duration
              <span class="text-sm text-red-500">{{
                errorduration
              }}</span></label
            >
            <div
              class="text-gray-400 text-lg rounded w-full p-2"
              v-if="!editMode"
            >
              {{ category.eventCategoryDuration }} Minutes
            </div>
            <div v-else>
              <input
                type="number"
                class="text-lg rounded w-full p-2 bg-slate-200 text-slate-500 border-slate-200 shadow"
                @keyup="checkduration()"
                v-model="editDuration"
              />
            </div>
          </div>
        </div>
      </div>
      <div class="mt-6 flex">
        <button class="flex items-center mr-10">
          <router-link
            :to="{ path: '/listcategory', name: 'ListCategory' }"
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
          @click="editModeOn()"
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
          @click="save(category.id)"
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

<style scoped></style>
