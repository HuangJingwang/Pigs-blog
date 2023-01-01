<template>
  <IndexBackground></IndexBackground>
  <div class="container">
    <!-- 展示文章卡片 -->
    <div
      class="articleCard basic-box"
      v-for="(item, index) in arr"
      :key="index"
      @click="$router.push('/article')">
      <!-- 图片 -->
      <div
        class="articleImg"
        :class="index % 2 == 0 ? ' articleImg-left' : 'articleImg-right'"></div>
      <!-- 文章描述信息 -->
      <div
        class="articleInfo articleInfo-right"
        :class="index % 2 == 0 ? 'articleInfo-right' : 'articleInfo-left'">
        <h1 class="title">css学习笔记</h1>
        <div class="breads">
          <div class="data">
            <span class="iconfont"></span>
            2022-11-04
          </div>
          <div class="category">学习笔记</div>
          <div class="tags">css</div>
        </div>
        <div class="detail">
          图片裁剪处clip-path 可以把图片自己的需要裁剪出各种形状
          ,图片裁剪处clip-path 可以把图片自己的需要裁剪出各种形状
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import axios from 'axios'
// 引入请求数据方法
// import { getArticleList } from '@/api/index.js'
import IndexBackground from '@/components/IndexBackground'
import { ref, onMounted } from 'vue'
export default {
  components: { IndexBackground },
  setup() {
    // let articleImgUrl = require('@/static/img/articleImg1.jpg')    :style="{ backgroundImage: `url(${articleImgUrl})` }"
    let arr = ref([1, 2, 3, 4, 5, 6, 7, 8])
    let articleInfoList = []

    // onMounted(async () => {
    //   // await getTestData()
    // })
    return {
      // articleImgUrl,
      arr,
    }
  },
}
</script>

<style scoped>
.container {
  padding: 30px;
  padding-top: 30px;
  width: 1100px;
  margin: auto;
}
/* 文章卡片 */
.articleCard {
  /* 图片添加圆角 */
  overflow: hidden;
  width: 100%;
  height: 240px;
  margin-bottom: 40px;
}
/* 梯形图片 */
.articleImg {
  /* 使用边框画出梯形图案    无法为边框平铺背景图片 */
  /* border-bottom: 240px solid pink;
  border-right: 100px solid transparent;
  border-image-source: url('@/static/img/articleImg1.jpg');
  background-size: cover; */
  position: relative;
  width: 450px;
  height: 240px;
  background-size: cover;
  transition: all 0.5s;
  /* background-color: #000; */
}

.articleImg-left {
  float: left;
  background-color: pink;
}
.articleImg-right {
  float: right;
  background-color: skyblue;
}

/* 方案2 使用伪元素遮罩盖住三角形部分 */
.articleImg-left::before {
  position: absolute;
  width: 100px;
  height: 100%;
  top: 0;
  right: 0;
  content: '';
  border-top: 240px solid #fff;
  border-left: 100px solid transparent;
}
.articleImg-right::before {
  position: absolute;
  width: 100px;
  height: 100%;
  top: 0;
  left: 0;
  content: '';
  border-top: 240px solid #fff;
  border-right: 100px solid transparent;
}
.articleImg-left:hover {
  transform: translateX(-15px);
}
.articleImg-right:hover {
  transform: translateX(15px);
}

/* 文章简介信息 */
.articleInfo {
  width: 580px;
  height: 240px;
  padding: 40px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-around;
}
.articleInfo-right {
  float: right;
}
.articleInfo-left {
  float: left;
}
.articleInfo .title {
  font-weight: 900;
  font-size: 24px;
  color: #000;
}
/* 面包屑处理 */
.articleInfo .breads div {
  display: inline-block;
  margin-right: 15px;
}
.articleInfo .detail {
  text-align: left;
  line-height: 30px;
  font-size: 14px;
  /* 溢出文本显示 ... */
  display: -webkit-box;

  -webkit-box-orient: vertical;

  -webkit-line-clamp: 2;

  overflow: hidden;
}
</style>
