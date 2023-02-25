<template>
  <div class="cover">
    <div class="articleInfo">
      <div class="title">
        标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字字标题很多字标题很多字
      </div>
      <div class="breads">
        <div class="author"></div>
        <div class="date"></div>
        <div class="category"></div>
      </div>
      <div class="view"></div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps(["articleData"])
console.log("props传参", props.articleData)
</script>

<style scoped>
.cover {
  width: 100%;
  height: 400px;
  background-color: aqua;
  position: relative;
}
.cover .articleInfo {
  width: 700px;
  height: 200px;
  background-color: pink;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate( -30%,-50%);
}
.articleInfo .title {
  width: 100%;
  font-size: 24px;
  line-height: 40px;
}
</style>
