<template>
  <Cover></Cover>
  <div class="container">
    <div class="mainBody">
      <!-- 展示文章卡片 -->
      <div class="articles" @click="toArticle">
      <!-- ，消息/文章列表设置栏 -->
        <div class="notice basic-box">
          <div class="left">
            <div class="byView"><span class="icon_hot"></span> 热度排序</div>
            <div class="byAuthor"><span class="icon_author"></span>作者: all</div>
          </div>
          <div class="right">
            <span class="iconfont icon-tongzhi"></span>
            <div class="scrollText">
              <span class="scrollItem">项目源码已上传GitHub</span>
            </div>
          </div>
        </div>
        <div
          class="articleCard basic-box"
          v-for="(article, index) in articleList"
          :key="article.id"
        >
          <!-- 图片 -->
          <div
            class="articleImg"
            :class="index % 2 == 0 ? ' articleImg-left' : 'articleImg-right'"
            :data-id="article.id"
            :style="{ backgroundImage: `url(${article.img_url})` }"
          ></div>
          <!-- 文章描述信息 -->
          <div
            class="articleInfo articleInfo-right"
            :class="index % 2 == 0 ? 'articleInfo-right' : 'articleInfo-left'"
          >
            <h1 class="title" :data-id="article.id">{{ article.title }}</h1>
            <div class="breads">
              <!-- <div class="author">
                <span class="iconfont icon-gaojian-zuozhe"></span>
                {{ article.nickname }}
            </div> -->
              <div class="date">
                <span class="iconfont icon-shijian"></span>
                更新于: {{ article.update_at }}
              </div>
              |
              <div class="category">
                <span class="iconfont icon-fenlei"></span>分类: {{ article.group_name }}
              </div>
              |
              <div class="preview">
                <span class="iconfont icon-chakan1"></span>浏览量: {{ article.page_view }}
              </div>
            </div>
            <div class="introduction" :data-id="article.id">
              {{ article.introduction }}
            </div>
            <div class="tags">
              <el-tag v-for="tag in article.tags" :key="index">{{ tag }}</el-tag>
            </div>
          </div>
        </div>
      </div>
      <!-- 侧边功能模块 -->
      <Aside />
    </div>
    <div class="loadMore">
      <a v-show="hasNext" href="javascript:void(0)" @click="loadMore"> loadMore... </a>
      <p v-show="!hasNext">没有了捏!</p>
    </div>
  </div>
</template>

<script setup>
// import {storeToRefs} from 'pinia'
import { useUserStore } from "@/store/user"
import { useArticleStore } from "@/store/article"
import { useRoute, useRouter } from "vue-router"
import Cover from "./Cover"
import Aside from "./Aside"
import { ref, onMounted, computed, watch } from "vue"
const route = useRoute()
const router = useRouter()
const userStore = useUserStore()
const articleStore = useArticleStore()

let byViews = ref(false)
let byMine = ref(false)

// 渲染首页列表数据
// 获取数据
let articleList = computed(() => {
  return articleStore.homeArticles.articlesList
})
let pageNo = computed(() => {
  return articleStore.homeArticles.currentPage
})

watch(
  pageNo,
  () => {
    let data = {
      pageNo: pageNo.value,
    }
    // 发起请求获取文章
    articleStore.reqArticleList(data)
    console.log(articleStore.homeArticles, "articles")
  },
  { deep: true, immediate: true }
)
// onMounted(() => {
//   articleStore.reqArticleList()
//   // 仅在第一次加载时发送获取初始数据的请求
//   if (articleList.value.length == 0) {
//     dispatch("reqArticleData")
//   }
// })
// 点击获取更多文章
// 获取当前文章页数
let hasNext = computed(() => {
  return articleStore.homeArticles.hasNext
})
// 点击加载更多
const loadMore = () => {
  // 是否有下一页
  if (hasNext.value) {
    articleStore.homeArticles.currentPage += 1
  }
}

// 跳转至文章详情页
const toArticle = (e) => {
  // console.log(123)
  let id = e.target.dataset.id
  if (id) {
    router.push({
      path: "/article",
      query: {
        id: id,
      },
    })
  }
}

//三方登录
onMounted(() => {
  // 获取key 并存储
  let key = route.query.key //获取key
  let status = sessionStorage.getItem("status")
  if (status === "active" && key) {
    // 存储key
    userStore.key = key
  }
})
</script>
<style scoped>
.container {
  position: relative;
  padding-top: 20px;
  width: 1250px;
  margin: auto;
}
.container .articles {
  margin-right: 20px;
  width: 975px;
}

/* 公告栏 */
.container .notice {
  display: flex;
  width: 100%;
  height: 40px;
  margin-bottom: 20px;
  background-color: #fff;
  overflow: hidden;
}
.notice .left {
  display: flex;
  align-items: center;
  padding: 0 20px;
  color: #fff;
  width: 370px;
  height: 100%;
  position: relative;
  background-color: rgb(103, 184, 234);
}

.notice .left::after {
  content: "";
  display: block;
  position: absolute;
  top: 0;
  right: 0;
  border-top: 40px solid #fff;
  border-right: 20px solid #fff;
  border-left: 20px solid transparent;
  border-bottom: 40px solid transparent;
}
.notice .left .byView,.notice .left .byAuthor {
  margin-right: 15px;
  display: flex;
  align-items: center;
}
.byView .icon_hot,
.byAuthor .icon_author {
  height: 24px;
  width: 24px;
  background-position: center center;
  background-size: contain;
  margin-right: 10px;

}

.byView .icon_hot {
  background-image: url(@/assets/icons/hot.png);
}
.byAuthor .icon_author {
  background-image: url(@/assets/icons/author.png);
}

.notice .right {
  flex: 1;
  display: flex;
  align-items: center;
  padding: 0 25px;
  position: relative;
}

.scrollText {
  width: 100%;
  overflow: hidden;
}
.scrollItem {
  display: block;
  width: 100%;
  animation: scrollAnimation 5s linear infinite;
}
@keyframes scrollAnimation {
  0% {
    transform: translateX(100%);
  }
  100% {
    transform: translateX(-100%);
  }
}
.notice .right .iconfont {
  font-size: 32px;
  color: rgb(254, 144, 0);
  position: absolute;
  left: -16px;
}

/* 文章卡片 */
.articleCard {
  /* 图片添加圆角 */
  overflow: hidden;
  width: 100%;
  height: 240px;
  margin-bottom: 20px;
}
/* 梯形图片 */
.articleImg {
  position: relative;
  width: 450px;
  height: 240px;
  /* background-size: cover; */
  background-size: cover;
  background-repeat: no-repeat;
  /* background-image: url(http://moon.starrysummer.com/a3f269fcaaea4fecab53db70492a5d5c.png); */
  background-position: center center;
  transition: all 0.5s;
  /* background-color: #000; */
}

.articleImg-left {
  float: left;
}
.articleImg-right {
  float: right;
}

/* 方案2 使用伪元素遮罩盖住三角形部分 */
.articleImg-left::before,
.articleImg-right::before {
  position: absolute;
  width: 100px;
  height: 100%;
  top: 0;
  content: "";
  border-top: 240px solid #fff;
}
.articleImg-left::before {
  right: 0;

  border-left: 100px solid transparent;
}
.articleImg-right::before {
  left: 0;
  border-right: 100px solid transparent;
}
.articleCard:hover .articleImg-left {
  transform: translateX(-20px);
}
.articleCard:hover .articleImg-right {
  transform: translateX(20px);
}

/* 文章简介信息 */
.articleInfo {
  width: 520px;
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
  display: -webkit-box;

  -webkit-box-orient: vertical;

  -webkit-line-clamp: 2;
  font-weight: 900;
  font-size: 24px;
  color: #000;
}
/* 面包屑处理 */
.breads {
  vertical-align: middle;
}
.articleInfo .breads div {
  display: inline-block;
  margin: 0 7px;
  line-height: 20px;
  font-size: 14px;
  vertical-align: middle;
  height: 20px;
}
.articleInfo .breads div:first-child {
  margin-left: 0;
}
.breads .iconfont {
  vertical-align: middle;
  margin-right: 5px;
}
.articleInfo .tags >>> .el-tag {
  margin-right: 10px;
}
.articleInfo .introduction {
  text-align: left;
  line-height: 20px;
  font-size: 16px;
  /* 溢出文本显示 ... */
  display: -webkit-box;

  -webkit-box-orient: vertical;

  -webkit-line-clamp: 3;

  overflow: hidden;
}

.container .mainBody {
  /* background-color: red; */
  display: flex;
  /* justify-content: space-between; */
}

/* 加载更多文章 */
.container .loadMore {
  color: #fff;
  text-align: center;
  margin: auto;
  /* background-color: pink; */
}
.loadMore a {
  color: #eee;
  font-size: 16px;
}
</style>
