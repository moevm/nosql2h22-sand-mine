import  {createApp} from 'vue'
import Multiselect from 'vue-multiselect'

import App from "./pages/App.vue"
import Temp from "./pages/TempPageForTable.vue"
import TempBackPage from './pages/TempPageForBackground.vue'
import ProductionPage from './pages/ProductionPage.vue'
import ImportExportPage from "./pages/ImportExportPage.vue"
import StaffListPage from "./pages/StaffListPage.vue"
import AccountPage from "./pages/AccountPage.vue"

createApp(AccountPage).mount('#app')
