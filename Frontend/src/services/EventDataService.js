const API_URL = '/sj2/api'
// const API_URL = '/api'
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
            method: 'PATCH',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(update)
        })
    }
    retreiveCategory(id){
        return fetch(`${API_URL}/events/category/${id}`)
    }
    retreiveAllEventUpcoming(event){
        return fetch(`${API_URL}/events/upcoming` ,{
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(event)
        })
    }
    retreiveAllEventPast(event){
        return fetch(`${API_URL}/events/past` ,{
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(event)
        })
    }
    retreiveAllEventFilter(filter){
        return fetch(`${API_URL}/events/filter` ,{
            method: 'POST',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(filter)
        })
    }
}
export default new EventDataService()