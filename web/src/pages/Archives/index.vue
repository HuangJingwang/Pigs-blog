<template>
  <div>
    <Background :title="`Archive`"></Background>
    <div class="container basic-box">
      <!-- 初版归档结构 -->
      <!-- <div class="total">{{ total }} articles in total</div>
      <div
        class="year_month"
        v-for="(item, index) in archivesList"
        :key="item.id"
      >
        <div class="title">{{ item.year_month }}</div>
        <div
          class="article"
          v-for="(article, index) in item.data"
          :key="article.id"
        >
          <span class="articleTitle">{{ article.title }}</span>
          <span class="date">{{ article.month }}-{{ article.day }}</span>
        </div>
      </div> -->

      <!-- 第二版 -->
      <div class="head">
        <div class="title">Archives</div>
      </div>
      <div class="body" @click="toArticle">
        <div class="articles" v-for="item in archivesList" :key="item.id">
          <div class="box">
            <div class="list">
              {{ item.year }}年{{ item.month }} 月
              <span class="bigNode">
                <div class="axis">
                  <span class="joint iconfont icon-jiedianguanli"></span>
                </div>
              </span>
            </div>
            <span class="articleNum">{{ item.data.length }} articles</span>
          </div>
          <div class="article" v-for="article in item.data" :key="article.id">
            <div class="date">{{ article.month }}-{{ article.day }}</div>
            <span class="node">
              <div class="axis">
                <span class="dot1"></span>
                <span class="dot2"></span>
                <span class="dot3"></span>
              </div>
            </span>
            <div class="title" :data-id="article.id">{{ article.title }}</div>
          </div>
        </div>
      </div>
      <div class="foot">{{ total }} articles in total</div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted, toRefs, computed } from 'vue'
import { useRouter } from 'vue-router'
import Background from '@/components/Background.vue'
import { getArchives } from '@/api'
const router = useRouter()
let archivesList = ref([])
let total = ref(0)
// 页面中展示归档数据
onMounted(async () => {
  let result = await getArchives()
  let arr = []
  let obj = {}
  if (result.success) {
    total.value = result.data.length
    result.data.forEach((el1, index) => {
      const date = el1.create_at
      el1.year = date.substring(0, 4)
      el1.month = date.substring(5, 7)
      el1.day = date.substring(8, 10)
      el1.year_month = `${el1.year}-${el1.month}`
      // 判斷元素的特徵值是否爲第一次出現
      if (obj[el1.year_month] == undefined) {
        obj[el1.year_month] = 1
        // 將數據存入數組
        archivesList.value.push({
          date: el1.create_at,
          year_month: el1.year_month,
          year: el1.year,
          month: el1.month,
          data: [el1],
          id: index,
        })
      } else {
        // 若不是第一次出現，則推入data
        archivesList.value.forEach((el2, index) => {
          if (el1.year_month == el2.year_month) {
            archivesList.value[index].data.push(el1)
          }
        })
      }
    })
  }
})
//跳转至文章详情
const toArticle = (e) => {
  let id = e.target.dataset.id
  if (id) {
    // state.user.key = '1234'
    router.push({
      path: '/article',
      query: {
        id: id,
      },
    })
  }
}
// 文章总数
// let total = computed(() => {
//   return state.archivesData.total
// })
// // 文章列表
// let archivesList = computed(() => {
//   return state.archivesData.archivesList
// })
</script>

<style scoped>
/* .container {
  padding: 50px;
  position: relative;
  top: -50px;
  width: 1200px;
  margin: auto;
}
.year_month .title {
  font-size: 32px;
  font-weight: 900;
  margin: 15px;
}
.year_month .article {
  font-size: 18px;
  padding: 0 30px;
  display: flex;
  height: 50px;
  line-height: 50px;
  justify-content: space-between;
  transition: all .5s;
}
.container .total {
  font-family: Arial, Helvetica, sans-serif; ;
  font-size: 24px;
  margin-left: 2rem;
  padding-bottom: 30px;
  border-bottom: 1px solid #eee;
}.article:hover{
  background-color: #eee;
  color:rgb(153, 233, 206) ;
}
.article .articleTitle{
  cursor: pointer;
} */

.container {
  padding: 30px;
  position: relative;
  top: -50px;
  width: 1200px;
  margin: auto;
}

.head .title {
  /* padding: 030px; */
  font-weight: 900;
  padding-left: 30px;
  font-size: 42px;
  /* margin-bottom: 15px; */
  padding-bottom: 30px;
  border-bottom: 1px solid #eee;
}

.body {
  border-bottom: 1px solid #eee;
}
.body .foot {
  text-align: right;
  padding-top: 30px;
  border-top: 1px solid #eee;
  margin-top: 30px;
}
.body .list {
  position: relative;
  width: 200px;
  height: 80px;
  line-height: 80px;
  text-align: right;
  font-size: 24px;
  padding-right: 5px;
}

.articles .article {
  display: flex;
}
.article .date {
  width: 200px;
  text-align: right;
  padding-right: 10px;
}
.article .title {
  display: -webkit-box;

-webkit-box-orient: vertical;

-webkit-line-clamp: 1;
overflow: hidden;
  font-size: 22px;
  /* width: 500px; */
  flex: 1;
  padding-left: 10px;
  transition: all 0.3s;
}
.article .date,
.article .title {
  height: 50px;
  line-height: 50px;
}
.list .bigNode {
  display: inline-block;
  position: absolute;
  right: -40px;
  width: 40px;
  height: 80px;
}

.article .node {
  position: relative;
  width: 40px;
}
.bigNode .axis,
.node .axis {
  height: 100%;
  width: 5px;
  background-color: rgb(64, 64, 42);
  margin: auto;
}

.bigNode .joint {
  position: relative;
  right: 18px;
  color: rgb(49, 99, 148);
  font-size: 40px;
  text-align: center;
}
.dot1,
.dot2,
.dot3 {
  display: inline-block;
  position: absolute;
  border-radius: 50%;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  transition: all 0.3s;
}
.node .dot1 {
  width: 20px;
  height: 20px;
  /* background-color: pink;
   */
  background-color: rgba(22, 90, 103);
}
.node .dot2 {
  width: 10px;
  height: 10px;
  background-color: rgba(255, 255, 255);
}
.node .dot3 {
  width: 0px;
  height: 0px;
  background-color: rgb(28, 155, 172);
}
.article .title:hover {
  background-color: #eee;
  color: rgb(5, 98, 67);
}
.article:hover .dot1 {
  width: 28px;
  height: 28px;
}
.article:hover .dot2 {
  width: 22px;
  height: 22px;
}
.article:hover .dot3 {
  width: 10px;
  height: 10px;
}

.articleNum {
  height: 80px;
  line-height: 80px;
  color: rgb(143, 141, 141);
  margin-left: 50px;
  font-size: 14px;
}
.box {
  display: flex;
}
</style>
