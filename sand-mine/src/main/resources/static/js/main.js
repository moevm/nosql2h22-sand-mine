import  {createApp} from 'vue'
import Multiselect from 'vue-multiselect'
import App from "./pages/App.vue"
import Temp from "./pages/TempPageForTable.vue"
import TempBackPage from './pages/TempPageForBackground.vue'
import ProductionPage from './pages/ProductionPage.vue'
import ImportExportPage from "./pages/ImportExportPage.vue"
import StaffListPage from "./pages/StaffListPage.vue"
import FindStaffPage from "./pages/FindStaffPage.vue"
import StaffPage from "./pages/StaffPage.vue"
import ShiftsPage from "./pages/ShiftsListPage.vue"
import ShiftsListPage from "./pages/ShiftsListPage.vue";


// createApp(App).mount('#app')
createApp(ShiftsListPage).mount('#app')

