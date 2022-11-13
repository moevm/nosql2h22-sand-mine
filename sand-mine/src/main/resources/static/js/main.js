import  {createApp} from 'vue'
import App from "./pages/App.vue"
import Temp from "./pages/TempPageForTable.vue"
import TempBackPage from './pages/TempPageForBackground.vue'

// createApp(App).mount('#app')
createApp(TempBackPage).mount('#app')
