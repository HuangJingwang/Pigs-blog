<template>
  <Header v-if="$route.meta.showComponent"></Header>
  <router-view></router-view>
  <Footer v-if="$route.meta.showComponent"></Footer>
</template>

<script setup>
import { toRef, computed,onMounted ,ref,watch} from 'vue'
import { useStore } from 'vuex';
// import { useRoute, useRouter } from 'vue-router'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
const {state}  = useStore()
let innerHeight = ref(0)
innerHeight.value  = window.innerHeight
// watch(innerHeight, (newVal, oldVal) => {
//       console.log({ newVal, oldVal })
//     })

window.onresize(() => {
  console.log(123)
})
</script>

<style>
.basic-box {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.15), 0 8px 26px 0 rgba(0, 0, 0, 0.15);
}
</style>
