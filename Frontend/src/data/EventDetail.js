import { reactive } from "vue";

export const detail = reactive({
    // data : {"bookingName": "",
    //         "bookingEmail": "",
    //         "eventStartTime": "",
    //         "eventDuration": "",
    //         "eventNotes": "",
    //         "eventCategory": ""},
        dataId:{},
    setDataId(id){
        this.dataId = id
    },
})