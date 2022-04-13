import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

//引入element plus
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import './assets/css/global.css'
import zhCn from 'element-plus/es/locale/lang/zh-cn'

import {
    Location,
    Setting,
    Search,
    ArrowDown,
} from '@element-plus/icons'

createApp(App)
    .component("Location", Location)
    .component("Search", Search)
    .component("setting", Setting)
    .component("arrowDown", ArrowDown)
    .use(store).use(router)
    .use(ElementPlus, {
        locale: zhCn, size:"small"
    }).mount('#app')
