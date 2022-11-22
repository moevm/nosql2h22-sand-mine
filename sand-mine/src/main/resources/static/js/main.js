import  {createApp} from 'vue'
import Multiselect from 'vue-multiselect'

import App from "./pages/App.vue"
import Temp from "./pages/TempPageForTable.vue"
import TempBackPage from './pages/TempPageForBackground.vue'
import ProductionPage from './pages/ProductionPage.vue'
import ImportExportPage from "./pages/ImportExportPage.vue"
import StaffListPage from "./pages/StaffListPage.vue"
import SubordinatePage from "./pages/SubordinateAccountPage.vue"
import WardenAccPage from "./pages/WardenAccountPage.vue"
import UserPassPage from "./pages/UserPassInformationPage.vue"
import router from 'router/router'


const app = createApp(App).use(router)

// app.component('App',App)
// app.component('SubordinatePage',SubordinatePage)

app.mount('#app')
