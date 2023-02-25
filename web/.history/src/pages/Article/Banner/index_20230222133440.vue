<template>
  <div class="cover">
    <div class="articleInfo">
      <div class="title">
       {{articleData.title}}
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
  width:750px;
  height: 200px;
  background-color: pink;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate( -50%,-40%);
}
.articleInfo .title {
  width: 100%;
  font-size: 32px;
  line-height: 40px;
  text-align: center;
    align-items: center;
}
.articleInfo .breads{
    width: 100%;
    height: 100px;
    background-color: hotpink;
}
</style>
