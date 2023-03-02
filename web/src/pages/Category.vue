<template>
  <Background></Background>
  <div class="container">
    <div class="Categories" v-for="(item, index) in data" :key="index">
      <div class="articleTitle">
      </div>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'

export default {
  name: 'Category',
  setup() {
    let data = reactive()
    return { data }
  },
}
</script>

<style scoped>
.container {
  width: 300px;
  height: 300px;
  background-color: #fff;
}
</style>
