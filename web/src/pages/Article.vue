<template>
  <div class="cover_box" ></div>
  <div class="wrapper">
  <div class="preview">
    <ArticlePreview/>
  </div>
  </div>
</template>

<script setup>
import ArticlePreview from '@/components/ArticlePreview.vue'
</script>

<style scoped>
.cover_box {
  width: 100%;
  /* height: 450px; */
  background-color: pink;
}

.wrapper {
  margin: auto;
  width: 1200px;
  position: relative;
  /* top: 100px; */
  min-height: 600px;
}
</style>
