<template>
  <Header v-if="$route.meta.showComponent"></Header>
  <router-view></router-view>
  <Footer v-if="$route.meta.showComponent"></Footer>
</template>

<script>
import { toRef, computed } from 'vue'
// import { useRoute, useRouter } from 'vue-router'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
export default {
  name: 'App',
  components: {
    Header,
    Footer,
  },
}
</script>

<style>
.basic-box {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.15), 0 8px 26px 0 rgba(0, 0, 0, 0.15);
}
</style>
