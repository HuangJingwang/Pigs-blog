<template>
  <Header/>
  <div class="wrapper">
    <Aside/>
    <div class="content">
      <div class="mainstay">
        <Navigation />
        <router-view/>
      </div>
      <div class="footer"></div>
    </div>
  </div>
</template>

<script setup>
import Aside from '@/pages/Manage/components/Aside.vue'
import Header from '@/pages/Manage/components/Header.vue'
import Navigation from './components/Navigation.vue'
</script>

<style scoped>
.wrapper {
  display: flex;
  height: calc(100% - 50px);
  width: 100%;
}
.content {
  flex: 1;
  height: 100%;
  background-color: pink;
}
.mainstay {
  /* padding: 15px; */
  /* padding: 0 15px  15px  15px ; */
}
</style>
