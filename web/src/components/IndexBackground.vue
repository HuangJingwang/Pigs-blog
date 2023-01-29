<template>
  <div class="cover">
    <div class="coverImg" :style="{ backgroundImage: `url(${imgUrl})` }"></div>
    <div class="headline">
      <div class="mainTitle" @click="test">
        <span class="logo">logo</span>
        <p class="title">Pigs-blog</p>
      </div>
      <TypeWriter></TypeWriter>
      <!-- <p class="saying" :style="{ width: `${sayingWidth}` }">{{ saying }}</p> -->
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
import { ref } from 'vue'
let imgUrl = require('@/assets/img/bgi1.jpg')
// 点击按钮滚动页面
const distance = ref(0)
const scrollToMain = () => {
console.log
  distance.value = window.innerHeight
  console.log(window.innerHeight)
  console.log(distance.value)
  window.scrollTo({
    top: distance.value - 60,
    behavior: 'smooth',
  })
}
</script>

<style scoped>
.cover {
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
.cover .headline {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  /* display: flex; */
}

.headline .mainTitle {
  text-align: center;
  margin-bottom: 5px;
}
.logo,
.title {
  display: inline-block;
  color: #fff;
  height: 50px;
  line-height: 50px;
}
.headline .logo {
  font-size: 45px;
  margin-right: 15px;
}
.headline .title {
  font-size: 32px;
  text-shadow: 2px 2px 3px rgba(0, 0, 0, 0.5);
}
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
.scrollToMain:hover{
  cursor: pointer;
}
.scrollToMain .icon-jiantouxia{
  color: #fff;
  height: 35px;
  line-height: 35px ;
  font-size: 30px;
  /* animation: pullDown1 2s ease-out  infinite; */
}
.scrollToMain .arrow_one{
  animation: pullDown1 1.2s ease-out  infinite 0.6s;
}

.scrollToMain .arrow_two{
  animation: pullDown2 1.2s ease-out  infinite 0.3s;
}
.scrollToMain .arrow_three{
  animation: pullDown3 1.2s ease-out  infinite;
}
@keyframes pullDown1 {
  from{
    /* transform: translateY(px); */
    opacity: 1;
    /* background-color: pink; */
  }
  to{
    opacity: 0;
    /* background-color: red; */
    transform: translateY(30px);

  }
}
@keyframes pullDown2 {
  from{
    transform: translateY(-30px);
    opacity: 1;
    /* background-color: pink; */
  }
  to{
    opacity: 0;
    /* transform: translateY(30px); */
  }
}
@keyframes pullDown3 { 
  from{
    transform: translateY(-60px);
    opacity: 1;
  }
  to{
    transform: translateY(-30px);

    opacity: 0;
  }
 }
</style>
