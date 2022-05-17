const API_URL = '/sj2/api'
// const API_URL = '/api'
class EventCategoryDataService{
    retrieveAllCategory(){
        return fetch(`${API_URL}/categories`)
    }
    retrieveCategory(id){
        return fetch(`${API_URL}/categories/${id}`)
    }
    deleteCategory(id){
        return fetch(`${API_URL}/categories/${id}`,{
            method: 'DELETE'
        })
    }
}
export default new EventCategoryDataService