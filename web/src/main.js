import { createApp } from 'vue'
import App from './App.vue'
import { router } from './router/index.js'
// 引入全局组件
import Background from './components/Background.vue'
// 引入pinia倉庫
import { createPinia } from 'pinia'
// 引入持久化工具
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
// 引入仓库
// 引入element-plus
import ElementPlus from 'element-plus'
// 引入element-plus样式
import 'element-plus/dist/index.css'
const pinia = createPinia()
pinia.use(piniaPluginPersistedstate)
const app = createApp(App)
app
  .component('Background', Background)

  .use(ElementPlus)
  .use(pinia)
  .use(router)
  .mount('#app')
// script 引入
