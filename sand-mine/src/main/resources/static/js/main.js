import  {createApp} from 'vue'
import App from "./pages/App.vue"
import router from 'router/router'
import axios from 'axios'



const app = createApp(App).use(router)
app.mount('#app')


axios.get("/api/zone/all")
    .then(response => {
        app.config.globalProperties.$zones_mapping = response.data
    })

