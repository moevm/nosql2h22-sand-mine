import  {createApp} from 'vue'
import App from "./pages/App.vue"
import TempBackPage from './pages/TempPageForBackground.vue'
import ProductionPage from './pages/ProductionPage.vue'
import ImportExportPage from "./pages/ImportExportPage.vue"
import StaffListPage from "./pages/StaffListPage.vue"
import FindStaffPage from "./pages/FindStaffPage.vue"
import StaffPage from "./pages/StaffPage.vue"
import ShiftsPage from "./pages/ShiftsListPage.vue"
import ShiftsListPage from "./pages/ShiftsListPage.vue";
import SubordinatePage from "./pages/SubordinateAccountPage.vue"
import WardenAccPage from "./pages/WardenAccountPage.vue"
import UserPassPage from "./pages/UserPassInformationPage.vue"
import Authorization from "./pages/Authorization.vue"
import CheckPass from "./pages/CheckPass.vue"
import AdministratorPage from "./pages/AdministratorPage.vue"
import StaffShifts from "./pages/StaffShifts.vue"
import AddEditStaff from "./pages/AddEditStaff.vue"
import router from 'router/router'



const app = createApp(WardenAccPage).use(router)
app.mount('#app')
