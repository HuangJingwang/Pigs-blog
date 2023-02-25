import { createRouter, createWebHashHistory } from 'vue-router'
const routes = [
  {
    path: '/',
    redirect: '/index',
  },
  {
    path: '/index',
    name: 'index',
    meta: {
      showComponent: true,
    },
    component: () => import('@/pages/Hone'),
  },
  {
    path: '/article',
    name: 'article',
    meta: {
      showComponent: true,
    },
    component: () => import('@/pages/Article'),
  },
  {
    path: '/category',
    name: 'category',
    meta: {
      showComponent: true,
    },
    component: () => import('@/pages/Category'),
  },
  {
    path: '/archives',
    name: 'archives',
    meta: {
      showComponent: true,
    },
    component: () => import('@/pages/Archives'),
  },
  {
    path: '/write',
    name: 'write',
    meta: {
      showComponent: false,
    },
    component: () => import('@/pages/Write'),
  },
  {
    path: '/about',
    name: 'about',
    meta: {
      showComponent: true,
    },
    component: () => import('@/pages/About'),
  },
  {
    path: '/manage',
    name: 'manage',
    meta: {
      showComponent: false,
    },
    component: () => import('@/pages/Manage'),
    children: [
      {
        path: 'overview',
        component: () => import('@/pages/Manage/OverView'),
      },
      {
        path: 'draft',
        component: () => import('@/pages/Manage/Draft'),
      },
      // 路由重定向，设置默认展示页面
      {
        path: '/manage',
        redirect: '/manage/overview',
      },
    ],
  },
]


const router = createRouter({
  history: createWebHashHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    // 始终滚动到顶部
    return { top: 0 }
  },
})
export default router
