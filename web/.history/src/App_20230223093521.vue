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
<!-- <button @click="showModal" class="testBtn">显示文章管理模块</button> -->
</template>

<script setup>
import { onMounted, defineAsyncComponent } from 'vue'
import { useStore } from 'vuex'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import UserModal from './components/UserModal'
import BackToTop from './components/BackToTop.vue'
const { state, dispatch } = useStore()
// import ArticleModal from './components/ArticleModal'
// 引入异步组件
const ArticleModal = defineAsyncComponent({
  // 加载函数
  loader: () => import('./components/ArticleModal'),
  delay: 200,
})

const showModal = () => {
  console.log(state.showArticleModal)
  state.showArticleModal = ! state.showArticleModal
}
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

.testBtn{
  width: 200px;
  height: 200px;
  position: absolute;
  top: 100px;
  left: 100px;
  background-color: aqua;
}
</style>
