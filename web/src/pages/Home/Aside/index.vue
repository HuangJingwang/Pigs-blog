<template>
  <!-- 側邊信息模塊 -->
  <el-affix :offset="offsetTop">
    <div class="aside" ref="aside">
      <!-- 非漂浮组件,随滚动移动 -->
      <!-- 漂浮组件 -->
      <!-- 1,用户信息 -->
      <div class="userInfo basic-box">
        <div class="avatar" :style="{ backgroundImage: `url(${userData.img_url})` }"></div>
        <div class="userName">{{ userData.nickname }}</div>
        <div class="articleNum">文章数: {{ userData.articles_count }}</div>
        <div class="viewNum">浏览量: {{ userData.page_view }}</div>
        <div class="github"><span class="iconfont icon-GitHub"></span></div>
      </div>
      <!-- 2,时钟组件 -->
        <!-- <Clock /> -->
    </div>
  </el-affix>
</template>

<script setup>
import { ref, onMounted } from "vue"
import { getHomeUserInfo } from "@/api"
import Clock from "./Clock"
// 侧边栏悬浮效果
// 监听文档滚动事件
let aside = ref(null)
let offsetTop = ref(70)
let userAccount = ''
// 获取组件数据
let userData = ref({
  account: "",
  img_url:'',
  articles_count: 0,
  github_url: "",
  nickname: "",
  page_view: 0,
})
onMounted(async () => {
  let result = await getHomeUserInfo(userAccount)
  console.log("homeUserInfo", result)
  if (result.code === 200) {
    for (let key in userData.value) {
      userData.value[key] = result.data[key]
    }
  }
})
</script>

<style scoped>
.aside {
  /* flex: 1;
   */
   width: 255px;
  position: sticky;
}
.aside .userInfo {
  width: 100%;
  padding-bottom: 30px;
  padding-top: 40px;
}
.aside .avatar {
  margin: 0 auto;
  width: 120px;
  background-position: center center;
  background-size: cover;
  height: 120px;
  border-radius: 50%;
  transition: all 0.2s;
}
.avatar:hover {
  transform: scale(1.1);
}
.userName {
  width: 100%;
  text-align: center;
  margin: 20px 0;
  font-size: 20px;
}
.articleNum,
.viewNum {
  width: 50%;
  display: inline-block;
  text-align: center;
  font-size: 14px;
}
.github {
  text-align: center;
  margin-top: 30px;
}
.github .iconfont {
  font-size: 32px;
  cursor: pointer;
}
</style>
