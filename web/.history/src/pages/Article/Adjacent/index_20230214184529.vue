<template>
  <!-- 上一篇 -->
  <div class="pre" @click="toPreArticle">
    <div
      class="coverImg_pre"
      :style="{ backgroundImage: `url(${imgUrl})` }"
    ></div>
    <div class="articleCard_pre">
      <span class="btn_pre iconfont icon-qiehuanqizuo" :data-id="preArticle.id"></span>
      <div class="title" :data-id="preArticle.id">{{ preArticle.title }}</div>
    </div>
  </div>

  <!-- 下一篇 -->
  <div class="next" @click="toNextArticle">
    <div
      class="coverImg_next"
      :style="{ backgroundImage: `url(${imgUrl})` }"
    ></div>
    <div class="articleCard_next">
      <span class="btn_next iconfont icon-qiehuanqiyou" :data-id="nextArticle.id"></span>
      <div class="title" :data-id="nextArticle.id">{{nextArticle.title}}</div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import {useRouter} from 'vue-router'
import { getPreArticle, getNextArticle } from '@/api'

const router = useRouter()
const props = defineProps({ id })
let imgUrl = require('@/assets/img/articleImg.jpg')
let preArticle = ref({
  id: -100,
  title: 'untitled',
})
let nextArticle = ref({
  id: -100,
  title: 'untitled',
})
onMounted(async () => {
  let preResult = await getPreArticle(props.id)
  if (preResult.code == 200) preArticle.value = preResult.data

  let nextResult = await getNextArticle(props.id)
  if (nextResult.code == 200) nextArticle.value = nextResult.data
})

// 点击事件

const toPreArticle = (e) => {
  let id = e.target.dataset.id
  console.log(id)
  jumpArticle(id)

}
const toNextArticle = (e) => {

  let id = e.target.dataset.id
  console.log(id)
  jumpArticle(id)
}

//跳转路由
function jumpArticle(id) {
  //携带文章id 跳转到article页面
  if (id >= 0) {
    router.push({
      path: '/article',
      query: {
        id:id
      }
    })
  }

}
</script>

<style scoped>
.pre,
.next {
  position: relative;
  min-width: 150px;
  width: 50%;
  height: 180px;
  overflow: hidden;
  background-size: cover;
  background-repeat: no-repeat;
}
.pre .coverImg_pre,
.next .coverImg_next {
  position: absolute;
  background-color: red;
  background-repeat: no-repeat;
  background-size: cover;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
  transition: all 0.3s;
}
/* 半透遮罩 */

.pre .coverImg_pre::after,
.next .coverImg_next::after {
  content: '';
  position: absolute;
  top: 0;
  height: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  transition: all 0.3s;
}

.pre:hover .coverImg_pre,
.next:hover .coverImg_next {
  transform: scale(1.1, 1.1);
}

.pre:hover .coverImg_pre::after,
.next:hover .coverImg_next::after {
  background-color: rgba(0, 0, 0, 0.2);
}

.pre .articleCard_pre,
.next .articleCard_next {
  position: relative;
  height: 100%;
}

.title {
  width: 300px;
  text-align: center;
  color: #fff;
  font-size: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  overflow: hidden;
  text-overflow: ellipsis;

}
.pre .btn_pre,
.next .btn_next {
  color: rgb(221, 221, 221);
  font-size: 50px;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  transition: all 0.3s;
}

.pre:hover .btn_pre,
.next:hover .btn_next {
  color: rgb(94, 123, 149);
}
.next .btn_next {
  right: 0;
}
</style>
