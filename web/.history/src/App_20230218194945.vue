<template>
  <!-- 路由组件 -->
  <router-view></router-view>
  <!-- 页脚 -->
  <Footer v-if="$route.meta.showComponent"></Footer>
  <!-- 登录模块 -->
  <UserModal ></UserModal>
  <!-- 头部导航 -->
  <Header
    v-if="$route.meta.showComponent"
  ></Header>
  <!-- 回到顶部 -->
<BackToTop></BackToTop>
<!-- 文章管理模块 -->
<ArticleModal></ArticleModal>
</template>

<script setup>
import { onMounted, ref, defineAsyncComponent } from 'vue'
import { useStore } from 'vuex'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import UserModal from './components/UserModal'
import BackToTop from './components/BackToTop.vue'
// import ArticleModal from './components/ArticleModal'
const AsyncComp = defineAsyncComponent({
  // 加载函数
  loader: () => import('./Foo.vue'),
  // 加载异步组件时使用的组件
  // 展示加载组件前的延迟时间，默认为 200ms
  // 加载失败后展示的组件
  // 如果提供了一个 timeout 时间限制，并超时了
  // 也会显示这里配置的报错组件，默认值是：Infinity
  timeout: 3000
})
const { state, dispatch } = useStore()

let result = ref(null)
// 请求分类列表数据
onMounted(async() => {
  dispatch('reqGroupList')
})
</script>
<style>
.basic-box {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.15), 0 8px 26px 0 rgba(0, 0, 0, 0.15);
}
.box-shadow {
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.15), 0 8px 26px 0 rgba(0, 0, 0, 0.15);
}
</style>
