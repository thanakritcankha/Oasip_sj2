 <script setup>
import { onBeforeMount, ref } from 'vue';
import EventDataService from '../services/EventDataService';
import Event from '../components/Event.vue';

const showWindow = ref(false)

// ex 
const events = ref([])
const listEvents = async () => {
    const res = await EventDataService.retrieveAllEvent()
    events.value = await res.json()
}
onBeforeMount(async () => {
    await listEvents();
    console.log(events.value)
})
</script>
 
<template>
    <!-- <div class="grid grid-cols-3 gap-2 flex" v-if="events.length > 0">
        <div v-for="(event, index) in events" :id="event.id">
            <Event :mask="event" />
        </div>
    </div> -->
    <div class="relative flex flex-col justify-center py-2 sm:py-4 " v-if="events.length > 0">
        <div
            class="columns-2 2xl:columns-3 gap-6 [column-fill:_balance] box-border mx-auto before:box-inherit after:box-inherit">
            <div class="break-inside-avoid p-2 mb-6 bg-gray-100 rounded-lg" v-for="(event, index) in events"
                :id="event.id">
                <Event :mask="event" />
            </div>
        </div>
    </div>
    <div v-else>
        No Scheduled Events
    </div>

</template>
 
<style scoped>
</style>