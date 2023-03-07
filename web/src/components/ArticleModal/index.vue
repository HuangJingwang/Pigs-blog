<template>
  <el-dialog
    v-model="userStore.showArticleModal"
    :lock-scroll="false"
    :append-to-body="true"
    title="文章管理"
    :width="1000"
    :before-close="handleClose"
  >
    <!-- 头部导航 -->
    <div class="header">
      <div
        :class="['btn_published', { active: btn_active == 'published' ? true : false }]"
        @click="showPublished"
      >
        已发表
      </div>
      <div
        :class="['btn_draft', { active: btn_active == 'draft' ? true : false }]"
        @click="showDraft"
      >
        草稿箱
      </div>
      <div
        :class="['btn_recycleBin', { active: btn_active == 'recycleBin' ? true : false }]"
        @click="showRecycleBin"
      >
        垃圾箱
      </div>
      <div class="byAuthor">
        <input type="checkbox" v-model="byAuthor" id="checkbox" />
        <label for="checkbox">
          <span class="text">只看我</span>
        </label>
      </div>
    </div>
    <hr class="line" />
    <div v-show="btn_active == 'published' ? true : false">
      <Published
        :publishedData="publishedData"
        @getPublishedPage="getPublishedPage"
        @getPublishedArticles="getPublishedArticles"
      />
    </div>
    <div v-show="btn_active == 'draft' ? true : false">
      <Draft
        :draftData="draftData"
        @getDraftPage="getDraftPage"
        @getDraftArticles="getDraftArticles"
      />
    </div>
    <div v-show="btn_active == 'recycleBin' ? true : false">
      <RecycleBin
        :recycleData="recycleData"
        @getRecyclePage="getRecyclePage"
        @getRecycleArticles="getRecycleArticles"
      />
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button>批量删除</el-button>
        <el-button @click="userStore.showArticleModal = false">Cancel</el-button>
        <el-button type="primary" @click="userStore.showArticleModal = false">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref, onMounted, watch } from "vue"
import Published from "./Published"
import Draft from "./Draft"
import RecycleBin from "./RecycleBin"
import { getArticleHandleList } from "@/api"
import { useUserStore } from "@/store/user"
const userStore = useUserStore()
// import { ElMessageBox } from "element-plus"
// 关闭按钮
const handleClose = (done) => {
  userStore.showArticleModal = false
}
// 设置按钮active状态
let btn_active = ref("published")
// 设置事件更改btn——active
const showPublished = () => {
  btn_active.value = "published"
}
const showDraft = () => {
  btn_active.value = "draft"
}
const showRecycleBin = () => {
  btn_active.value = "recycleBin"
}
let byAuthor = ref(false)
//请求获取三种数据

//#region
let draftParams = ref({
  account: userStore.userInfo.account,
  pageNo: 1,
  status: "draft",
})

// 获取
async function getDraftArticles() {
  let result = await getArticleHandleList(draftParams.value, byAuthor.value)
  if (result.code == 200) draftData.value = result.data
  else if (result.code == 500) draftParams.value.pageNo -= 1
}
// 传给draft组件的数据
let draftData = ref(null)
// 获取新的页码
const getDraftPage = (val) => {
  // 更改页码
  // pageNo 始终小于等于最大页码
  draftParams.value.pageNo = val
}
watch(
  [draftParams, byAuthor],
  () => {
    getDraftArticles()
    console.log("watch success")
  },
  { immediate: true, deep: true }
)

//#endregion

//#region
// 回收站文章数据
let recycleData = ref(null)
// 回收站参数
let recycleParams = ref({
  account: userStore.userInfo.account,
  pageNo: 1,
  status: "deleted",
})
async function getRecycleArticles() {
  let result = await getArticleHandleList(recycleParams.value, byAuthor.value)
  if (result.code == 200) recycleData.value = result.data
  if (result.code == 500) recycleParams.value.pageNo -= 1
}

const getRecyclePage = (val) => {
  console.log(val)
  // 更改页码
  // pageNo 始终小于等于最大页码
  recycleParams.value.pageNo = val
  console.log(recycleParams.value.pageNo,'pageno')
}
watch(
  [recycleParams, byAuthor],
  () => {
    getRecycleArticles()
    console.log('watch success')
  },
  {
    immediate: true,
    deep: true,
  }
)
//#endregion

//#region

// 已發佈文章數據
let publishedData = ref(null)
let publishedPrams = ref({
  account: userStore.userInfo.account,
  pageNo: 1,
  status: "published",
})
async function getPublishedArticles() {
  let result = await getArticleHandleList(publishedPrams.value, byAuthor.value)
  if (result.code == 200) publishedData.value = result.data
  else if (result.code == 500) recycleParams.value.pageNo -= 1
}
const getPublishedPage = (val) => {
  // 更改页码
  // pageNo 始终小于等于最大页码
  recycleParams.value.pageNo = val
}
watch(
  [publishedPrams, byAuthor],
  () => {
    console.log('watch published page')
    getPublishedArticles()
  },
  { immediate: true, deep: true }
)
//#endregion
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
.header {
  display: flex;
  width: 320px;
  justify-content: space-between;
  padding-left: 20px;
  margin-bottom: 10px;
}
.line {
  border: 1px solid rgba(0, 0, 0, 0.3);
}
/* 导航按钮 */
.btn_published,
.btn_draft,
.btn_recycleBin {
  position: relative;
  width: 80px;
  font-size: 16px;
  height: 30px;
  line-height: 30px;
  text-align: center;
}
.byAuthor {
  margin-left: 10px;
  width: 80px;
  line-height: 30px;
  text-align: center;
}
.byAuthor .text {
  font-size: 16px;
  margin-left: 5px;
}
.btn_published::after,
.btn_draft::after,
.btn_recycleBin::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  height: 2px;
  width: 0;
  border-radius: 1px;
  background-color: rgb(3, 145, 167);
  transition: all 0.2s;
}
.btn_published:hover::after,
.btn_draft:hover:after,
.btn_recycleBin:hover:after {
  width: 100%;
}
.active {
  border-radius: 5px;
  background-color: #eee;
}
</style>
