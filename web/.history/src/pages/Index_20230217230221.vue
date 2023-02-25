<template>
  <IndexBackground></IndexBackground>
  <div class="container">
    <div class="mainBody">
      <!-- <div class="default"  >
        没有文章
      </div> -->
    <!-- 展示文章卡片 -->
      <div class="articles" @click="toArticle">
        <div class="notice basic-box">123</div>


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
          ></div>
          <!-- 文章描述信息 -->
          <div
            class="articleInfo articleInfo-right"
            :class="index % 2 == 0 ? 'articleInfo-right' : 'articleInfo-left'"
          >
            <h1 class="title" :data-id="article.id">{{ article.title }}</h1>
            <div class="breads">
              <div class="data">
                <span class="iconfont"></span>
                {{ article.update_at }}
              </div>
              <div class="category">{{article.group_id}}</div>
              <!-- <div class="tags">css</div> -->
              <div class="preview">{{ article.page_view }}</div>
            </div>
            <div class="introduction" :data-id="article.id">
        {{ article.introduction }}
            </div>

            <!-- <div class="tags">{{ article.tags }}</div> -->
            <div class="tags">
            <el-tag v-for="tag in article.tags" :key="index">{{ tag }}</el-tag>
            </div>
          </div>
        </div>
      </div>


      <!-- 侧边功能模块 -->
      <div class="aside">
        <!-- 漂浮组件 -->  
        <div class="components" ref="components">
          <div class="info basic-box"></div>
          <div class="clock basic-box"></div>
        </div>
        <!-- 非漂浮组件,随滚动移动 -->
      </div>
    </div>
    <div class="loadMore">
      <a v-show="hasNext" href="javascript:void(0)" @click="loadMore">
        loadMore...
      </a>
      <p v-show="!hasNext">没有了捏!</p>
    </div>
  </div>
</template>

<script setup>
import { useStore } from 'vuex'
import { useRoute, useRouter } from 'vue-router'
import IndexBackground from '@/components/IndexBackground'
import { ref, onMounted, computed } from 'vue'
import { isBoolean } from 'lodash'
const { dispatch, state } = useStore()
const route = useRoute()
const router = useRouter()

// 渲染首页列表数据
// 获取数据
let articleList = computed(() => {
  return state.articleData.articleList
})
onMounted(() => {
  // 仅在第一次加载时发送获取初始数据的请求
  if (articleList.value.length == 0) {
    dispatch('reqArticleData')
  }
})
// 点击获取更多文章
// 获取当前文章页数
let hasNext = computed(() => {
  return state.articleData.hasNext
})
// 点击加载更多
const loadMore = () => {
  // 是否有下一页
  if (hasNext) {
    state.currentPageNo += 1
    dispatch('reqArticleData')
  }
}

// 侧边栏悬浮效果
// 监听文档滚动事件
let components = ref(null)
onMounted(() => {
  window.addEventListener('scroll', () => {
    if (components.value && window.scrollY > components.value.offsetTop - 30) {
      components.value.style.top = 70 + 'px'
    }
  })
})

// 跳转至文章详情页
const toArticle = (e) => {
  // console.log(123)
  let id = e.target.dataset.id
  if (id) {
    // state.user.key = '1234'
    console.log(id)
    router.push({
      path: '/article',
      query: {
        id: id,
      },
    })
  }
}

//三方登录
onMounted(() => {
  // 获取key 并存储
  // 将key存入state中
  let key = route.query.key //获取key
  let status = sessionStorage.getItem('status')
  if (status === 'active' && key) {
    // 存储key
    state.user.key = key
  }
})
</script>

<style scoped>
.container {
  padding-top: 30px;
  width: 1250px;
  margin: auto;
}
.container .articles {
  margin-right: 30px;
}

/* 公告栏 */
.container .notice{
  height: 30px;
  margin-bottom: 15px;
}
/* 文章卡片 */
.articleCard {
  /* 图片添加圆角 */
  overflow: hidden;
  width: 975px;
  height: 240px;
  margin-bottom: 20px;
}
/* 梯形图片 */
.articleImg {
  position: relative;
  width: 450px;
  height: 240px;
  background-size: cover;
  transition: all 0.5s;
  /* background-color: #000; */
}

.articleImg-left {
  float: left;
  background-image: url(@/assets/img/articleImg.jpg);
}
.articleImg-right {
  float: right;
  background-image: url(@/assets/img/articleImg.jpg);
}

/* 方案2 使用伪元素遮罩盖住三角形部分 */
.articleImg-left::before,
.articleImg-right::before {
  position: absolute;
  width: 100px;
  height: 100%;
  top: 0;
  content: '';
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
.articleImg-left:hover {
  transform: translateX(-20px);
}
.articleImg-right:hover {
  transform: translateX(20px);
}

/* 文章简介信息 */
.articleInfo {
  width: 500px;
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
.articleInfo .introduction {
  text-align: left;
  line-height: 30px;
  font-size: 14px;
  /* 溢出文本显示 ... */
  display: -webkit-box;

  -webkit-box-orient: vertical;

  -webkit-line-clamp: 2;

  overflow: hidden;
}

.container .mainBody {
  /* background-color: red; */
  display: flex;
  /* justify-content: space-between; */
}
.mainBody .aside {
  flex: 1;
}
.aside .components {
  /* position: absolute;
   */
  position: sticky;
}
.aside .info {
  padding: 15px;
  height: 200px;
  width: 245px;
  overflow: hidden;
  margin-bottom: 20px;
}
.aside .clock {
  height: 300px;
  margin-bottom: 20px;
}
.aside .hotArticle {
  height: 250px;
  width: 245px;
  position: fixed;
  bottom: 20px;
}
/* 加载更多文章 */
.container .loadMore {
  text-align: center;
  margin: auto;
  /* background-color: pink; */
}
.loadMore a {
  font-size: 16px;
}
</style>
