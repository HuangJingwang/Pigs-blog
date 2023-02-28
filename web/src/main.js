import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'
// 引入全局组件
import Background from './components/Background.vue'
// 引入pinia倉庫
import { createPinia } from 'pinia'
// 引入仓库
import store from './store/vuex'
// 引入element-plus
import ElementPlus from 'element-plus'
// 引入element-plus样式
import 'element-plus/dist/index.css'
const app = createApp(App)
app
  .component('Background', Background)
  .use(ElementPlus)
  .use(createPinia())
  .use(router)
  .use(store)
  .mount('#app')
// script 引入
