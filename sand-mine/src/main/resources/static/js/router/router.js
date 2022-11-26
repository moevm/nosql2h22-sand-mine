import {createRouter, createWebHistory} from 'vue-router'
import App from '../pages/App.vue'
import TempBackPage from '../pages/TempPageForBackground.vue'
import ProductionPage from '../pages/ProductionPage.vue'
import ImportExportPage from "../pages/ImportExportPage.vue"
import StaffListPage from "../pages/StaffListPage.vue"
import FindStaffPage from "../pages/FindStaffPage.vue"
import StaffPage from "../pages/StaffPage.vue"
import ShiftsPage from "../pages/ShiftsListPage.vue"
import ShiftsListPage from "../pages/ShiftsListPage.vue";
import SubordinatePage from "../pages/SubordinateAccountPage.vue"
import WardenAccPage from "../pages/WardenAccountPage.vue"
import UserPassPage from "../pages/UserPassInformationPage.vue"
import Authorization from "../pages/Authorization.vue"
import CheckPass from "../pages/CheckPass.vue"
import AdministratorPage from "../pages/AdministratorPage.vue"
import StaffShifts from "../pages/StaffShifts.vue"
import AddEditStaff from "../pages/AddEditStaff.vue"

const AUTHORIZATION_PAGE = '/'
const PRODUCTION_PAGE = '/production'
const IMPORT_EXPORT_PAGE = '/import_export'
const STAFF_LIST_PAGE = '/staff_list'
const FIND_STAFF_PAGE = '/find_staff'
const WORKER_ACC_PAGE = '/worker_acc'
const SHIFTS_PAGE = '/shifts'
const SHIFTS_LIST_PAGE = '/shifts_list'
const SUBORDINATE_PAGE = '/subordinate'
const WARDEN_ACC_PAGE = '/warden_acc'
const USER_PASS_PAGE = '/user_pass'
const CHECK_PASS = '/check_pass'
const ADMINISTRATOR_PAGE = '/administrator_acc'
const STAFF_SHIFTS_PAGE = '/staff_shifts'
const EDIT_STAFF_PAGE = '/edit_staff_page'

function authorize(type, id) {
    let relative_path = ""
    switch (type) {
        case "worker": relative_path += WORKER_ACC_PAGE; break;
        case "admin": relative_path += ADMINISTRATOR_PAGE; break;
        case "warden": relative_path += ADMINISTRATOR_PAGE; break;
    }
    router.push(relative_path + "/id")
}

const routes = [
    {path: AUTHORIZATION_PAGE, name:"Authorization", component: Authorization},
    {path: PRODUCTION_PAGE, name: "ProductionPage", component: Production},
    {path: IMPORT_EXPORT_PAGE, name: "ImportExportPage", component: ImportExportPage}
    {path: STAFF_LIST_PAGE, name: "StaffListPage", component: StaffListPage},
    {path: FIND_STAFF_PAGE, name: "FindStaffPage", component: FindStaffPage},
    {path: WORKER_ACC_PAGE,name: "WorkerAccPage",component: WorkerAccPage},
    {path: SHIFTS_PAGE, name: 'ShiftsPage',component: ShiftsPage},
    {path: SHIFTS_LIST_PAGE, name: 'ShiftsListPage', component: ShiftsListPage}
    {path: SUBORDINATE_PAGE, name: 'SubordinatePage', component: SubordinatePage}
    {path: WARDEN_ACC_PAGE, name: 'WardenAccPage', component: WardenAccPage}
    {path: USER_PASS_PAGE, name: 'UserPassPage', component: UserPassPage}
    {path: CHECK_PASS, name: 'CheckPassPage', component: CheckPassPage}
    {path: ADMINISTRATOR_PAGE, name: 'AdministratorPage', component: AdministratorPage}
    {path: STAFF_SHIFTS_PAGE, name: 'StaffShiftsPage', component: StaffShiftsPage}
    {path: EDIT_STAFF_PAGE, name: 'EditStaffPage', component: EditStaffPage}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router

