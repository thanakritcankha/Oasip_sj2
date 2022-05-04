import { createApp } from 'vue'
import App from './App.vue'
import './index.css'
import router from './router'
import Event from './components/Event.vue'
import ListAll from './views/ListAll.vue'

const app = createApp(App)
app.use(router)
app.mount('#app')
