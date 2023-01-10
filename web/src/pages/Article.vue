<template>
  <div class="header-box"></div>
  <div class="wrapper">
    <ArticlePreview />
  </div>
</template>

<script>
import ArticlePreview from '@/components/ArticlePreview.vue'
export default {
  components: { ArticlePreview },
  setup() {
    return {}
  },
}
</script>

<style scoped>
.header-box {
  width: 100%;
  height: 200px;
  background-color: #eee;
}

.wrapper {
  margin: auto;
  width: 1200px;
  position: relative;
  top: 100px;
  min-height: 600px;
}
</style>
