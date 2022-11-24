import  {createApp} from 'vue'
import App from "./pages/App.vue"
import Temp from "./pages/TempPageForTable.vue"
import Authorization from "./pages/Authorization.vue"
import CheckPass from "./pages/CheckPass.vue"
import AdministratorPage from "./pages/AdministratorPage.vue"
import StaffShifts from "./pages/StaffShifts.vue"
import AddEditStaff from "./pages/AddEditStaff.vue"

// createApp(App).mount('#app')
// createApp(Temp).mount('#app')
// createApp(Authorization).mount('#app')
// createApp(CheckPass).mount('#app')
createApp(AddEditStaff).mount('#app')
