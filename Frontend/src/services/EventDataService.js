// const API_URL = '/sj2/api'
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
    createEvent(newEvent){
        return fetch(`${API_URL}/events`,{
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(newEvent)
        })
    }
    updateEvent(id , update){
        return fetch(`${API_URL}/events/${id}`,{
            method: 'PUT',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(update)
        })
    }
}
export default new EventDataService()