<template>
  <div class="cover">
    <div class="coverImg" :style="{ backgroundImage: `url(${imgUrl})` }"></div>
    <div class="headline">
      <div class="mainTitle" @click="test">
        <div class="logo"></div>
        <p class="title">Pigs-blog</p>
      </div>
      <TypeWriter></TypeWriter>
    </div>
    <div class="scrollToMain" @click="scrollToMain">
      <span class="iconfont arrow_one icon-jiantouxia"></span>
      <span class="iconfont arrow_two icon-jiantouxia"></span>
      <span class="iconfont arrow_three icon-jiantouxia"></span>
    </div>
  </div>
</template>

<script setup>
import TypeWriter from '@/components/TypeWriter.vue'
import { ref,onMounted } from 'vue'
let imgUrl = require('@/assets/img/bgi1.jpg')

let innerHeight = ref(0)
onMounted(() => {
  innerHeight = window.innerHeight
  console.log(window.innerHeight)
})
// 点击按钮滚动页面
const distance = ref(0)
const scrollToMain = () => {
  distance.value = window.innerHeight
  console.log(window.innerHeight)
  console.log(distance.value)
  window.scrollTo({
    top: distance.value - 40,
    behavior: 'smooth',
  })
}
</script>

<style scoped>
.cover {
  background-color: red;
  width: 100%;
  height: 100%;
}
.coverImg {
  position: relative;
  width: 100%;
  height: 100%;
  background-position: center center;
  background-size: cover;
}
.coverImg::after {
  content: '';
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3);
  position: absolute;
  top: 0;
  left: 0;
}

/* 居中标题及logo */

/* logo */
.mainTitle .logo {
  width: 100px;
  height: 100px;
  background-size: contain;
  background-image: url(@/assets/img/logo.png);
}
.cover .headline {
  display: flex;
  flex-direction: column;
  align-items: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* display: flex; */
}

.headline .mainTitle {
  display: flex;
  align-items: center;
  width: 400px;
  /* justify-content: ; */
  justify-content: center;
  text-align: center;
  margin-bottom: 5px;
}
.title {
  height: 50px;
  line-height: 50px;
}
.headline {
  font-size: 45px;
  margin-right: 15px;
}
.headline .title {
  margin-left: 10px;
  font-size: 42px;
  font-weight: 900;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
  text-indent: 0.1em;
  color: rgb(230, 230, 230);
  text-shadow: 2px 2px 3px rgba(0, 0, 0, 0.5);
}

/* 下拉按钮 */
.cover .scrollToMain {
  width: 200px;
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: space-between;
  /* font-size: 32px; */
  position: absolute;
  bottom: 0px;
  right: 50%;
  transform: translateX(50%);
}
.scrollToMain:hover {
  cursor: pointer;
}
.scrollToMain .icon-jiantouxia {
  color: #fff;
  height: 35px;
  line-height: 35px;
  font-size: 30px;
  /* animation: pullDown1 2s ease-out  infinite; */
}
.scrollToMain .arrow_one {
  animation: pullDown1 1.2s ease-out infinite 0.6s;
}

.scrollToMain .arrow_two {
  animation: pullDown2 1.2s ease-out infinite 0.3s;
}
.scrollToMain .arrow_three {
  animation: pullDown3 1.2s ease-out infinite;
}
@keyframes pullDown1 {
  from {
    /* transform: translateY(px); */
    opacity: 1;
    /* background-color: pink; */
  }
  to {
    opacity: 0;
    /* background-color: red; */
    transform: translateY(30px);
  }
}
@keyframes pullDown2 {
  from {
    transform: translateY(-30px);
    opacity: 1;
    /* background-color: pink; */
  }
  to {
    opacity: 0;
    /* transform: translateY(30px); */
  }
}
@keyframes pullDown3 {
  from {
    transform: translateY(-60px);
    opacity: 1;
  }
  to {
    transform: translateY(-30px);

    opacity: 0;
  }
}
</style>
