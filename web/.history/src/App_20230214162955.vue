<template>
  <router-view></router-view>
  <Footer v-if="$route.meta.showComponent"></Footer>
  <UserModal ></UserModal>
  <Header
    v-if="$route.meta.showComponent"
  ></Header>
<BackToTop></BackToTop>

<div>{{ result }}</div>
</template>

<script setup>
import { toRef, computed, onMounted, ref, watch } from 'vue'
import axios from 'axios'
import { useStore } from 'vuex'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import UserModal from './components/UserModal.vue'
import BackToTop from './components/BackToTop.vue'
const { state, dispatch } = useStore()
// let innerHeight = ref(0)
// innerHeight.value = window.innerHeight

let result = ref(null)

// onMounted(async() => {
//   result = await axios.get('http://www.starrysummer.com:6533/pigs-blog/articles-tags/list')
//  console.log(result)
  dispatch('reqGroupList')
// })
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
