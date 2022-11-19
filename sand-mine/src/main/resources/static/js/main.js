import  {createApp} from 'vue'
import Multiselect from 'vue-multiselect'

import App from "./pages/App.vue"
import Temp from "./pages/TempPageForTable.vue"
import TempBackPage from './pages/TempPageForBackground.vue'
import ProductionPage from './pages/ProductionPage.vue'

createApp(ProductionPage).mount('#app')
// createApp(TempBackPage).mount('#app')
