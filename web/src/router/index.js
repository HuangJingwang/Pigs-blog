import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
  {
    path: '/',
    redirect: '/index',
  },
  {
    path: '/index',
    name: 'index',
    component: () => import('@/pages/Index'),
  },
  {
    path: '/article',
    name: 'article',
    component: () => import('@/pages/Article'),
  },
  {
    path: '/category',
    name: 'category',
    component: () => import('@/pages/Category'),
  },
]

const router = createRouter({
  history: createWebHashHistory(),
  routes,
})
export default router
