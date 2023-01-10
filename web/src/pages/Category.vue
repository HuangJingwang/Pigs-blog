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
    let data = reactive([
      {
        category: 'HTML',
        data: [
          { id: '001', title: 'BFC的作用' },
          { id: '002', title: 'html5新特性' },
          { id: '002', title: 'html5新特性' },
          { id: '002', title: 'html5新特性' },
        ],
      },
      {
        category: 'javascript',
        data: [
          { id: '001', title: 'BFC的作用' },
          { id: '002', title: 'html5新特性' },
          { id: '002', title: 'html5新特性' },
          { id: '002', title: 'html5新特性' },
        ],
      },
      {
        category: 'Java',
        data: [
          { id: '001', title: 'BFC的作用' },
          { id: '002', title: 'html5新特性' },
          { id: '002', title: 'html5新特性' },
          { id: '002', title: 'html5新特性' },
        ],
      },
    ])
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
