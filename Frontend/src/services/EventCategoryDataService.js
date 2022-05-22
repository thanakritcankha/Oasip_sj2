// const API_URL = '/sj2/api'
const API_URL = '/api'
class EventCategoryDataService{
    retrieveAllCategory(){
        return fetch(`${API_URL}/categories`)
    }
    retrieveAllCategoryForCreate(){
        return fetch(`${API_URL}/categories/create`)
    }
    retrieveAllCategoryForFilter(){
        return fetch(`${API_URL}/categories/filter`)
    }
    updateEvent(id , update){
        return fetch(`${API_URL}/categories/${id}`,{
            method: 'PATCH',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify(update)
        })
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