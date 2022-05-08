const API_URL = '/api'
class EventCategoryDataService{
    retrieveAllCategory(){
        return fetch(`/sj2${API_URL}/categories`)
    }
    retrieveCategory(id){
        return fetch(`/sj2${API_URL}/categories/${id}`)
    }
    deleteCategory(id){
        return fetch(`/sj2${API_URL}/categories/${id}`,{
            method: 'DELETE'
        })
    }
}
export default new EventCategoryDataService