import {createRouter, createWebHistory} from 'vue-router'
import App from '../pages/App.vue'
import SubordinatePage from '../pages/SubordinateAccountPage.vue'
import ImportExportPage from "../pages/ImportExportPage.vue";
import ProductionPage from "../pages/ProductionPage.vue";
import TempPageForBackground from "../pages/TempPageForBackground.vue";

const routes = [
    {path:'/', name:"App", component: App},
    {path:'/subordinate/:id',name:"SubordinatePage", component: SubordinatePage},
    {path:'/import-export',name:"ImportExportPage",component: ImportExportPage},
    {path:'/production',name:"ProductionPage",component: ProductionPage},
    {path:'/temp',name:'temp',component:TempPageForBackground}
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router

