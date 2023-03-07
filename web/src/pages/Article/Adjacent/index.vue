<template>
  <!-- 上一篇 -->
  <div
    :class="['pre', { pre_forbidden: true }]"
    @click="toPreArticle(preArticle.id)"
    :style="{ cursor: `${pre_cursor}`}"
  >
    <div
      class="coverImg_pre"
      :style="{ backgroundImage: `url(${preArticle.img_url})` }"
    ></div>
    <div class="articleCard_pre" :style="{ cursor: `${pre_cursor}`}">
      <span
        class="btn_pre iconfont icon-qiehuanqizuo"
        :style="{ cursor: `${pre_cursor}`}"
      ></span>
      <div class="title" :style="{ cursor: `${pre_cursor}`}">{{ preArticle.title }}</div>
    </div>
  </div>

  <!-- 下一篇 -->
  <div class="next" @click="toNextArticle(nextArticle.id)"  :style="{ cursor: `${next_cursor}`}">
    <div
      class="coverImg_next"
      :style="{ backgroundImage: `url(${nextArticle.img_url})` }"
    ></div>
    <div class="articleCard_next"  :style="{ cursor: `${next_cursor}`}">
      <span class="btn_next iconfont icon-qiehuanqiyou"  :style="{ cursor: `${next_cursor}`}"></span>
      <div class="title"  :style="{ cursor: `${next_cursor}`}">{{ nextArticle.title }}</div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, watch, ref } from "vue"
import { useRouter } from "vue-router"
import { getPreArticle, getNextArticle } from "@/api"

const router = useRouter()
const props = defineProps(["id"])
let preArticle = ref({
  id: -100,
  title: "untitled",
  img_url: "",
})
let nextArticle = ref({
  id: -100,
  title: "untitled",
  img_url: "",
})

let pre_cursor = ref('pointer')
let next_cursor = ref('pointer')
watch(
  props,
  async () => {
    let preResult = await getPreArticle(props.id)
    if (JSON.stringify(preResult.data) !== "{}") {
      preArticle.value = preResult.data
      pre_cursor.value = 'pointer'
    } else {
      // 没有数据
      console.log("没有数据,使用默认数据")
      pre_cursor.value = 'not-allowed'
      preArticle.value = {
        id: -100,
        title: "没有了捏!",
        img_url: "https://moon.starrysummer.com/05650df26e4b49ae86f459bfdfa0e293.jpg",
      }
    }
    let nextResult = await getNextArticle(props.id)
    if (JSON.stringify(nextResult.data) !== "{}") {
      nextArticle.value = nextResult.data
      next_cursor.value = 'pointer'
    } else {
      // 为nextArticle 赋默认值
      console.log("没有数据,使用默认数据")
      next_cursor.value = 'not-allowed'
      // console.log(nextResult == {})
      nextArticle.value = {
        id: -100,
        title: "没有了捏!",
        img_url: "https://moon.starrysummer.com/05650df26e4b49ae86f459bfdfa0e293.jpg",
      }
    }
  },
  {
    immediate: true,
  }
)

// 点击事件

const toPreArticle = (id) => {
  jumpArticle(id)
}
const toNextArticle = (id) => {
  jumpArticle(id)
}
//跳转路由方法
function jumpArticle(id) {
  //携带文章id 跳转到article页面
  if (id >= 0) {
    router.push({
      path: "/article",
      query: {
        id: id,
      },
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
  content: "";
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
  /* cursor: pointer; */
  position: relative;
  height: 100%;
}

.title {
  /* cursor: pointer; */
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
  /* cursor: pointer; */
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
