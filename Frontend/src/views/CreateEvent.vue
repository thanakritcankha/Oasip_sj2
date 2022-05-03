<script setup>
function validateForm() {
  let x = document.forms["bookingForm"]["bname"].value;
  if (x == "") {
    alert("Name must be filled out");
    return false;
  }
}
</script>

<template>
  <div class="w-full max-w-xl">
    <h3>Create New Evet</h3>
    <form
      name="bookingForm"
      action="/action_page.php"
      class="bg-white shadow-md rounded px-5 pt-6 mb-4"
      onsubmit="return validateForm()"
    >
      <div class="mb-4">
        <label for="bname" class="block text-gray-700 text-sm font-bold mb-2"
          >Booking Name :
        </label>
        <input
          required
          type="text"
          id="bname"
          name="bname"
          class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        />
      </div>
      <div class="mb-6">
        <label for="email" class="block text-gray-700 text-sm font-bold mb-2"
          >Booking Email :
        </label>
        <input
          required
          type="email"
          id="email"
          name="email"
          class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        />
      </div>
      <div class="mb-6">
        <label for="duration" class="block text-gray-700 text-sm font-bold mb-2"
          >Booking Duration :
        </label>
        <input
          required
          type="text"
          id="duration"
          name="duration"
          class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        />
      </div>
      <div class="mb-6">
        <label for="notes" class="block text-gray-700 text-sm font-bold mb-2"
          >Booking Notes : </label
        ><br />
        <textarea
          class="form-control block w-full px-3 py-1.5 text-base font-normal text-gray-700 bg-white bg-clip-padding border border-solid border-gray-300 rounded transition ease-in-out m-0 focus:text-gray-700 focus:bg-white focus:border-orange-600 focus:outline-none"
        ></textarea>
      </div>
      <div class="mb-6">
        <label for="category" class="block text-gray-700 text-sm font-bold mb-2"
          >Event Category :
        </label>
        <select
          required
          name="category"
          id="category"
          class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        >
          <option value="1">Project Management Clinic</option>
          <option value="2">Devops/Infra Clinic</option>
          <option value="3">Database Clinic</option>
          <option value="4">Client-side Clinic</option>
          <option value="5">Server-side clinic</option>
        </select>
      </div>
      <div class="mb-6">
        <label
          for="eventTime"
          class="block text-gray-700 text-sm font-bold mb-2"
          >Start Time Event :
        </label>
        <input
          required
          type="datetime-local"
          id="eventTime"
          name="eventTime"
          class="focus:border-orange-600 shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
        />
      </div>
      <button
        class="bg-transparent hover:bg-blue-500 text-blue-700 font-semibold hover:text-white my-5 py-1 px-5 border border-blue-500 hover:border-transparent rounded"
        @click=""
      >
        Submit</button
      ><br />
    </form>
  </div>
</template>

<style></style>
