<template>
  <router-view></router-view>
  <Footer v-if="$route.meta.showComponent"></Footer>
  <UserModal ></UserModal>
  <Header
    v-if="$route.meta.showComponent"
  ></Header>
<BackToTop></BackToTop>
<!-- 文章管理模块 -->

</template>

<script setup>
import { onMounted, ref,  } from 'vue'
import { useStore } from 'vuex'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import UserModal from './components/UserModal'
import BackToTop from './components/BackToTop.vue'
import ArticleModal from './components/ArticleModal'
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
