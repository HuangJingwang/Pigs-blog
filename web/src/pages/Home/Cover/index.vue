<template>
  <div
    class="cover"
    :style="{ backgroundImage: `url(${imgUrl})`, height: `${innerHeight}` }"
  >
    <!-- <div class="coverImg" :style="{ backgroundImage: `url(${imgUrl})` }"></div> -->
    <div class="headline">
      <div class="mainTitle" @click="test">
        <div class="logo"></div>
        <p class="title">Pigs-blog</p>
      </div>
      <TypeWriter></TypeWriter>
    </div>
    <div class="transitionBox">
      <div class="header">
        <!--Waves Container-->
        <div>
          <svg
            class="waves"
            xmlns="http://www.w3.org/2000/svg"
            xmlns:xlink="http://www.w3.org/1999/xlink"
            viewBox="0 24 150 28"
            preserveAspectRatio="none"
            shape-rendering="auto"
          >
            <defs>
              <path
                id="gentle-wave"
                d="M-160 44c30 0 58-18 88-18s 58 18 88 18 58-18 88-18 58 18 88 18 v44h-352z"
              />
            </defs>
            <g class="parallax">
              <use xlink:href="#gentle-wave" x="48" y="0" fill="rgba(38,83,118, 0.1)" />
              <use xlink:href="#gentle-wave" x="48" y="3" fill="rgba(38,83,118, 0.3)" />
              <use xlink:href="#gentle-wave" x="48" y="5" fill="rgba(38,83,118, 0.5)" />
              <use xlink:href="#gentle-wave" x="48" y="7" fill="rgba(38,83,118, 0.8)" />
            </g>
          </svg>
        </div>
        <!--Waves end-->
      </div>
      <!--Header ends-->

      <!--Content starts-->
      <div class="content flex">
        <!--<p></p>-->
      </div>
<div class="linear">
</div>
      <!--Content ends-->
      <div class="scrollToMain" @click="scrollToMain">
        <span class="iconfont arrow_one icon-jiantouxia"></span>
        <span class="iconfont arrow_two icon-jiantouxia"></span>
        <span class="iconfont arrow_three icon-jiantouxia"></span>
      </div>
    </div>
  </div>
</template>

<script setup>
import TypeWriter from "@/components/TypeWriter.vue"
import { ref ,computed} from "vue"
import{useUserStore} from '@/store/user'
const userStore = useUserStore()
// let imgUrl = 'https://moon.starrysummer.com/916bbd3e23ac4dd1a9e7362ce2826b85.jpg'
let imgUrl = computed(()=>userStore.coverImg)
let innerHeight = window.innerHeight + "px"
// 点击按钮滚动页面
const distance = ref(0)
const scrollToMain = () => {
  distance.value = window.innerHeight
  window.scrollTo({
    top: distance.value - 60,
    behavior: "smooth",
  })
}
</script>

<style scoped>
.cover {
  width: 100%;
  position: relative;
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
}
.cover::before {
  content: "";
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.1);
}
.coverImg {
  position: relative;
  width: 100%;
  height: 100%;
  background-position: center center;
  background-size: contain;
}
.coverImg::after {
  content: "";
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
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
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
  cursor: pointer;
  right: 50%;
  transform: translateX(50%);
}

.scrollToMain .icon-jiantouxia {
  color: #fff;
  height: 35px;
  line-height: 35px;
  font-size: 30px;
  cursor: pointer;
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

.transitionBox {
  position: absolute;
  bottom: 0;
  width: 100%;
}
.linear{
  z-index:0;
  position: absolute;
  width: 100%;
  height: 150px;
  background: linear-gradient( rgba(40,80,110,1), rgba(40,80,110,0));
}
/* 波浪 */

@import url(//fonts.googleapis.com/css?family=Lato:300:400);

body {
  margin: 0;
}

.flex {
  /*Flexbox for containers*/
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
}

.waves {
  position: relative;
  width: 100%;
  height: 15vh;
  margin-bottom: -4px; /*Fix for safari gap*/
  min-height: 100px;
  max-height: 150px;
}

/* Animation */

.parallax > use {
  animation: move-forever 25s cubic-bezier(0.55, 0.5, 0.45, 0.5) infinite;
}
.parallax > use:nth-child(1) {
  animation-delay: -2s;
  animation-duration: 7s;
}
.parallax > use:nth-child(2) {
  animation-delay: -3s;
  animation-duration: 10s;
}
.parallax > use:nth-child(3) {
  animation-delay: -4s;
  animation-duration: 13s;
}
.parallax > use:nth-child(4) {
  animation-delay: -5s;
  animation-duration: 20s;
}
@keyframes move-forever {
  0% {
    transform: translate3d(-90px, 0, 0);
  }
  100% {
    transform: translate3d(85px, 0, 0);
  }
}
/*Shrinking for mobile*/
@media (max-width: 768px) {
  .waves {
    height: 40px;
    min-height: 40px;
  }
  .content {
    height: 30vh;
  }
  h1 {
    font-size: 24px;
  }
}

</style>
