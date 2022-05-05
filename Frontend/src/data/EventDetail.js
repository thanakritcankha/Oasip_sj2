import { reactive } from "vue";

export const detail = reactive({
    // data : {"bookingName": "",
    //         "bookingEmail": "",
    //         "eventStartTime": "",
    //         "eventDuration": "",
    //         "eventNotes": "",
    //         "eventCategory": ""},
        data:{},
    setData(value){
        this.data = value 
    },
    setCategoryName(name){
        this.data.eventCategory = name
    }
})