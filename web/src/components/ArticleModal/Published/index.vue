<template>

  <el-table
    :data="list"
    :row-key="getRowKey"
    :height="425"
    style="width: 100%"
    ref="multipleTableRef"
    :header-cell-style="{ 'text-align': 'center' }"
    @selection-change="handleSelectionChange"
  >
    <!-- 多选框 -->
    <el-table-column type="selection" :reserve-selection="true" width="30" fixed />
    <!--  编辑按键-->
    <el-table-column fixed label="Operations" width="200" align="center">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="deleteArticle(scope)"
          >Delete</el-button
        >
        <!-- <el-button link type="primary" size="small" @click="deleteArticleCompletely(scope)"
            >Delete completely</el-button
          > -->
        <el-button link type="primary" size="small" @click="editorArticle(scope)"
          >Edit</el-button
        >
      </template>
    </el-table-column>
    <!-- 账号 -->
    <el-table-column prop="account" label="author" width="180" align="center" />
    <!-- 標題 -->
    <el-table-column prop="title" label="title" width="240" align="center" />
    <!-- 封面 -->
    <el-table-column label="cover" width="120" align="center">
      <template #default="scope">
        <div
          class="coverImg"
          @click="showImg(scope.row.img_url)"
          :style="{ backgroundImage: `url(${scope.row.img_url})` }"
        ></div>
      </template>
    </el-table-column>
    <!-- 跟新时间 -->
    <el-table-column prop="date" label="Date" width="180" align="center" />
    <!-- 浏览量 -->

    <!-- 标签 -->
    <el-table-column label="tags" width="250" align="center">
      <template #default="scope">
        <el-tag class="tag" v-for="(tag, index) in scope.row.tags" :key="index">
          {{ tag }}
        </el-tag>
      </template>
    </el-table-column>
    <!-- 分类 -->
    <el-table-column align="center" prop="group_id" label="group" width="100" />
    <el-table-column prop="page_view" label="views" width="80" align="center" />
  </el-table>
  <!-- 分页器 -->
  <div class="pagination">
    <el-pagination
      background
      layout="prev, pager, next"
      :page-size="5"
      :pager-count="5"
      :current-page="currentPage"
      @current-change="changePage"
      :total="totalArticle"
    />
  </div>
  <!-- 预览图片 -->
  <el-dialog v-model="dialogVisible">
    <img w-full :src="picture_url" class="preview" alt="Preview Image" />
  </el-dialog>
</template>
<script setup>
import { computed, ref, onMounted, watch } from "vue"
import { getArticleDelete } from "@/api"
import { useRouter } from "vue-router"
import {useDeleteArticle} from '@/hooks/useDeleteArticle'
import {useUserStore}from '@/store/user'
import {ElMessageBox } from 'element-plus'
const userStore = useUserStore()

const router = useRouter()
const props = defineProps(["publishedData"])
const emits = defineEmits(["getPublishedPage", "getPublishedArticles"])
// list 中需要的数据 ：1、标题 2、时间 、3、封面 4、作者（仅管理员账户可见所有文章） 5、浏览量 6 、标签 7分类
// 若数据值为默认值,则为空
// 列表数据
const getRowKey = (row) => {
  return row.id
}
let list = computed(() => {
  props.publishedData.resultList.forEach((item) => {
    // 处理标题数据
    item.title = item.title == "Unspecified" ? "noData" : item.title
    // 处理时间数据
    item.date = item.update_at.slice(0, 10)
    // 处理tags
    item.tags = item.tags == "Unspecified" ? [] : item.tags.split(",")
  })
  return props.publishedData.resultList
})
// 分页器
// 文章总数
let totalArticle = computed(() => props.publishedData.totalResult)
let currentPage = ref(1)
// 页数变更时，向父组件传递数据，有父组件发起请求，更新列表
const changePage = (value) => {
    console.log(value)
  currentPage.value = value
  // 触发自定义事件,返回页面数据给父组件
  console.log('change page')
  emits("getPublishedPage", value)
}
// 多选框
let selectedArticles = [] //已选中文章的id
const multipleTableRef = ref()
const multipleSelection = ref([])
// 检测多选变化
const handleSelectionChange = (selections) => {
  multipleSelection.value = selections
  console.log(selections)
  // val为选中元素
  selections.forEach((item) => {
    selectedArticles.push(item)
    console.log(item.id)
  })

  console.log(selectedArticles)
}
// 点击预览封面
let picture_url = ref("")
let dialogVisible = ref(false)
const showImg = (url) => {
  console.log(url)
  picture_url.value = url
  dialogVisible.value = true
}

// 处理文章
//点击，删除文章按钮事件

const deleteArticle = async (scope) => {
 let result = await  useDeleteArticle(scope.row.account,scope.row.id,changeArticleList)
}

const editorArticle = (scope) => {
if(userStore.userInfo.role.indexOf('root')== -1){
  ElMessageBox.alert('操作失败,请联系管理员申请权限', 'ERROR', {
      confirmButtonText: 'OK',
      callback: (action) => {},
    })
    return
}
    if (userStore.userInfo.account !== scope.row.account) {
    ElMessageBox.alert('操作失败,这篇文章不是你的', 'ERROR', {
      confirmButtonText: 'OK',
      callback: (action) => {},
    })
    return
  }
  const writePath = router.resolve({
    path: '/write',
    query: {
      id: scope.row.id,
    },
  })
  window.open(writePath.href, '_blank')
}
// const deleteArticleCompletely = (scope) => {
//   console.log(scope.row.id)
// }

// const editorArticle = (scope) => {
//   useEditorArticle(scope.row.account, scope.row.id)
// }

// const editorArticle = (scope) => {
//   let id = scope.row.id
//   console.log(id)
//   const writePath = router.resolve({
//     path: "/write",
//     query: {
//       id: id,
//     },
//   })
//   window.open(writePath.href, "_blank")
// }
// 修改文章列表
function changeArticleList(){
  emits("getPublishedArticles")
}
</script>

<style scoped>
.coverImg {
  height: 60px;
  width: 120px;
  background-size: contain;
  background-color: #eee;
  position: relative;
}
.coverImg::after {
  content: "";
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.2);
  transition: all 0.3s;
  position: absolute;
}
.coverImg:hover::after {
  background-color: rgba(0, 0, 0, 0);
}
/* .previewImg{
    position: relative;
    right: -50px;
    bottom: 30px;
    font-size: 24px;
    color: rgb(85, 85, 85);
  } */
.pagination {
  width: 100%;
  margin-top: 15px;
  display: flex;
  text-align: center;
  justify-content: center;
}
/* 图片预览窗口 */
.preview {
  width: 100%;
  background-size: contain;
}
</style>
