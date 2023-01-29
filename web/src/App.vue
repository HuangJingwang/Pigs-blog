<template>

  <router-view></router-view>
  <Footer v-if="$route.meta.showComponent"></Footer>
  <UserModal v-show="showUserModal"></UserModal>
  <Header
    v-if="$route.meta.showComponent"
    :showUserModal="showUserModal"
  ></Header>
</template>

<script setup>
import { toRef, computed, onMounted, ref, watch } from 'vue'
import { useStore } from 'vuex'
// import { useRoute, useRouter } from 'vue-router'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import UserModal from './components/UserModal.vue'

const { state, dispatch } = useStore()
let innerHeight = ref(0)
innerHeight.value = window.innerHeight

const showUserModal = ref(true)
// onMounted(() => {
//   dispatch('reqGroupList')
// })
onMounted(() => {
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
