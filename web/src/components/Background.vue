<template>
  <div class="background" :style="{ backgroundImage: `url(${imgUrl})` }">
    <div class="title">{{ props.title }}</div>
  </div>
</template>

<script setup>
let imgUrl = require("@/assets/img/bgi1.jpg")
const props = defineProps(["title"])
console.log(props.title)
</script>

<style scoped>
.background {
  position: relative;
  width: 100%;
  height: 400px;
  background-position: center center;
  background-size: cover;
}
.background::after {
  content: "";
  width: 100%;
  height: 100%;
  z-index: 0;
  background-color: rgba(0, 0, 0, 0.5);
  position: absolute;
  top: 0;
  left: 0;
}
.title {
  position: absolute;
  z-index: 1;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
  font-size: 50px;
  color: #fff;
  pointer-events: none;
  font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
}
</style>
