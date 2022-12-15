import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index.js'
// 引入mock文件
import '@/mock/mockSever'
// 引入markdown 编辑器 样式
import 'md-editor-v3/lib/style.css'
const app = createApp(App)
app.use(router).mount('#app')
// script 引入
