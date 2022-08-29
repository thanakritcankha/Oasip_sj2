const API_URL = `${import.meta.env.VITE_BASE_URL}api`

class EventCategoryDataService{
    retrieveAllCategory(){
        return fetch(`${API_URL}/categories`)
    }
    retrieveAllCategoryForCreate(){ 
        return fetch(`${API_URL}/categories/schedule`)
    }
    retrieveAllCategoryForFilter(){ ////////////////////////
        return fetch(`${API_URL}/categories/period`)
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
}
export default new EventCategoryDataService()