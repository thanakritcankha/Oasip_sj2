import { createRouter, createWebHistory} from "vue-router";
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import ListAll from '../views/ListAll.vue'
const history = createWebHistory()
const routes = [
    {   path: '/',  name: 'Home',   component: Home },
    {   path: '/about',  name: 'About',   component: About },
    {   path: '/list',  name: 'ListEvent',   component: ListAll },
]
const router = createRouter({history , routes})
export default router