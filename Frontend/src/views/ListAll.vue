<script setup>
import { onBeforeMount, ref } from 'vue';
import EventDataService from '../services/EventDataService';
import Event from '../components/Event.vue';
import Event1 from '../components/Event.vue';

const events = ref([])
const listEvents = async () => {
    const res = await EventDataService.retrieveAllEvent()
    events.value = await res.json()
}
const btnDelete = (id) => {
    // let text = "Press a button!\nEither OK or Cancel.";
    // let text = "เลือกดีๆนะไอ้สัส กูขี้เกียจใส่ข้อมูลใหม่";
    let text = "คุณแน่ที่จะลบหรือไม่";
    // console.log(id)
    if (confirm(text) == true) {
        deleteEvent(id)
    }
}
const deleteEvent = async (id) => {
    const res = await EventDataService.deleteEvent(id);
    res.status === 200
        ? (events.value = events.value.filter((value) => value.id !== id))
        : console.log('Error to delete event')
}
onBeforeMount(async () => {
    await listEvents();
    // console.log(events.value)
})
</script>
    <th class="text-left text-gray-700 capitalize px-4 py-4">Duration</th> -->
    <div class="container mx-auto px-4 sm:px-8">
        <div class="py-">
            <!-- <div>
                <h2 class="text-2xl font-semibold leading-tight">Invoices</h2>
            </div> -->
            <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
                <div class="inline-block min-w-full shadow-md rounded-lg overflow-hidden" v-if="events.length > 0">
                    <table class="min-w-full leading-normal">
                        <thead>
                            <tr>
                                <th
                                    class="px-5 py-3  bg-gray-600 text-md font-semibold text-gray-200 uppercase tracking-wider">
                                    <div class="flex items-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-tags-fill mr-1" viewBox="0 0 16 16">
                                            <path
                                                d="M2 2a1 1 0 0 1 1-1h4.586a1 1 0 0 1 .707.293l7 7a1 1 0 0 1 0 1.414l-4.586 4.586a1 1 0 0 1-1.414 0l-7-7A1 1 0 0 1 2 6.586V2zm3.5 4a1.5 1.5 0 1 0 0-3 1.5 1.5 0 0 0 0 3z" />
                                            <path
                                                d="M1.293 7.793A1 1 0 0 1 1 7.086V2a1 1 0 0 0-1 1v4.586a1 1 0 0 0 .293.707l7 7a1 1 0 0 0 1.414 0l.043-.043-7.457-7.457z" />
                                        </svg>
                                        Category
                                    </div>

                                </th>
                                <th
                                    class="w-1/4 px-5 py-3 bg-gray-600 text-md font-semibold text-gray-200 uppercase tracking-wider">
                                    <div class="flex items-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-alarm-fill mr-1" viewBox="0 0 16 16">
                                            <path
                                                d="M6 .5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1H9v1.07a7.001 7.001 0 0 1 3.274 12.474l.601.602a.5.5 0 0 1-.707.708l-.746-.746A6.97 6.97 0 0 1 8 16a6.97 6.97 0 0 1-3.422-.892l-.746.746a.5.5 0 0 1-.707-.708l.602-.602A7.001 7.001 0 0 1 7 2.07V1h-.5A.5.5 0 0 1 6 .5zm2.5 5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5zM.86 5.387A2.5 2.5 0 1 1 4.387 1.86 8.035 8.035 0 0 0 .86 5.387zM11.613 1.86a2.5 2.5 0 1 1 3.527 3.527 8.035 8.035 0 0 0-3.527-3.527z" />
                                        </svg>
                                        Booking Name
                                    </div>

                                </th>
                                <th
                                    class="px-5 py-3  bg-gray-600 text-md font-semibold text-gray-200 uppercase tracking-wider">
                                    <div class="flex items-center justify-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-calendar-fill mr-1" viewBox="0 0 16 16">
                                            <path
                                                d="M3.5 0a.5.5 0 0 1 .5.5V1h8V.5a.5.5 0 0 1 1 0V1h1a2 2 0 0 1 2 2v11a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V5h16V4H0V3a2 2 0 0 1 2-2h1V.5a.5.5 0 0 1 .5-.5z" />
                                        </svg>
                                        Date
                                    </div>
                                </th>
                                <th
                                    class="px-3 py-3 bg-gray-600 text-md font-semibold text-gray-200 uppercase tracking-wider">
                                    <div class="flex items-center justify-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-alarm-fill mr-1" viewBox="0 0 16 16">
                                            <path
                                                d="M6 .5a.5.5 0 0 1 .5-.5h3a.5.5 0 0 1 0 1H9v1.07a7.001 7.001 0 0 1 3.274 12.474l.601.602a.5.5 0 0 1-.707.708l-.746-.746A6.97 6.97 0 0 1 8 16a6.97 6.97 0 0 1-3.422-.892l-.746.746a.5.5 0 0 1-.707-.708l.602-.602A7.001 7.001 0 0 1 7 2.07V1h-.5A.5.5 0 0 1 6 .5zm2.5 5a.5.5 0 0 0-1 0v3.362l-1.429 2.38a.5.5 0 1 0 .858.515l1.5-2.5A.5.5 0 0 0 8.5 9V5.5zM.86 5.387A2.5 2.5 0 1 1 4.387 1.86 8.035 8.035 0 0 0 .86 5.387zM11.613 1.86a2.5 2.5 0 1 1 3.527 3.527 8.035 8.035 0 0 0-3.527-3.527z" />
                                        </svg>
                                        Time
                                    </div>
                                </th>
                                <th
                                    class="py-3 bg-gray-600 text-md font-semibold text-gray-200 uppercase tracking-wider">
                                    <div class="flex items-center justify-center">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                            fill="currentColor" class="bi bi-calendar-range-fill mr-1"
                                            viewBox="0 0 16 16">
                                            <path
                                                d="M4 .5a.5.5 0 0 0-1 0V1H2a2 2 0 0 0-2 2v1h16V3a2 2 0 0 0-2-2h-1V.5a.5.5 0 0 0-1 0V1H4V.5zM16 7V5H0v5h5a1 1 0 1 1 0 2H0v2a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9h-6a1 1 0 1 1 0-2h6z" />
                                        </svg>
                                        Duration
                                    </div>
                                </th>
                                <th class="px-5 py-3 bg-gray-600"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <Event :mask="event" v-for="event in events" @deleteEvent="btnDelete($event)" />
                        </tbody>
                    </table>
                </div>
                <div v-else>
                    No Scheduled Events
                </div>
            </div>
        </div>
    </div>


</template>
 
<style scoped>
</style>