const API_URL = '/api'
class EventDataService{
    retrieveAllEvent(){
        return fetch(`${API_URL}/events`)
    }
    retrieveEvent(id){
        return fetch(`${API_URL}/events/${id}`)
    }
    deleteEvent(id){
        return fetch(`${API_URL}/events/${id}`,{
            method: 'DELETE'
        })
    }
}
export default new EventDataService()