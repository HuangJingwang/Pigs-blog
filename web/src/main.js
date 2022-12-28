import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'
// 引入全局组件
import Background from './components/Background.vue'
import store from './store'
// 引入mock文件
import '@/mock/mockSever'
// 引入markdown 编辑器 样式
const app = createApp(App)
app.component('Background', Background).use(router).use(store).mount('#app')
// script 引入
