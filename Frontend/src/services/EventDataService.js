const API_URL = '/api'
class EventDataService {
    retrieveAllEvent(){
        return fetch(`${API_URL}/events`)
    }
}   
export default new EventDataService();