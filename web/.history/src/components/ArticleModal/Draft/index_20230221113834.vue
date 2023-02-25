<template>
  <el-table
    :data="list"
    style="width: 100%"
    ref="multipleTableRef"
    :header-cell-style="{ 'text-align': 'center' }"
    @selection-change="handleSelectionChange"
  >
    <!-- 多选框 -->
    <el-table-column type="selection"
    :reserve-selection="true"
     width="55" fixed />
    <!--  编辑按键-->
    <el-table-column fixed label="Operations" width="200" align="center">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handleClick(scope)"
          >Delete</el-button
        >
        <el-button link type="primary" size="small">Edit</el-button>
        <el-button link type="primary" size="small">Publish</el-button>
      </template>
    </el-table-column>
    <!-- 账号 -->
    <el-table-column prop="account" label="author" width="180" align="center" />

    <el-table-column label="cover" width="180" align="center">
      <!-- 封面 -->
      <template #default="scope">
        <div class="coverImg" @click="showImg(scope.row.title)">
          {{ scope.row.title }}
        </div>
      </template>
    </el-table-column>
    <!-- 跟新时间 -->
    <el-table-column prop="date" label="Date" width="180" align="center" />
    <!-- 浏览量 -->
    <el-table-column prop="page_view" label="views" width="100" align="center" />
    <!-- 标签 -->
    <el-table-column label="tags" width="300" align="center">
      <template #default="scope">
        <div class="tag" v-for="(tag, index) in scope.row.tags" :key="index">
          {{ tag }}
        </div>
      </template>
    </el-table-column>
    <!-- 分类 -->
    <el-table-column align="center" prop="group_id" label="group" width="100" />
  </el-table>
  <div class="pagination">
    <el-pagination background layout="prev, pager, next" 
    :page-size=5
    :pager-count=5
    :current-page="currentPage"
    :total="totalArticle" />
  </div>
  <!-- 预览图片 -->
  <el-dialog v-model="dialogVisible">
    <img w-full :src="picture_url" class="preview" alt="Preview Image" />
  </el-dialog>
</template>
<script setup>
import { computed, ref, onMounted } from "vue"
const props = defineProps(["draftData"])
// list 中需要的数据 ：1、标题 2、时间 、3、封面 4、作者（仅管理员账户可见所有文章） 5、浏览量 6 、标签 7分类
// 若数据值为默认值,则为空
// 列表数据
let list = computed(() => {
  props.draftData.resultList.forEach((item) => {
    // 处理标题数据
    item.title = item.title == "Unspecified" ? "noData" : item.title
    // 处理时间数据
    console.log("时间", item.update_at)
    console.log("标签", item.tags)
    item.date = item.update_at.slice(0, 10)
    console.log(item.date)
    // 处理tags
    item.tags = item.tags == "Unspecified" ? [] : item.tags.split(",")
    console.log(item.tags)
  })
  return props.draftData.resultList
})

// 分页器
// 文章总数
let totalArticle = computed(() => props.draftData.totalResult)
let currentPage = ref(1)
// 页数变更时，向父组件传递数据，有父组件发起请求，更新列表
watch(currentPage, () => {
  
})

// 多选框
let selectedArticle = []
const multipleTableRef = ref()
const multipleSelection = ref([])
// 检测多选变化
const handleSelectionChange = (selections) => {
  multipleSelection.value = selections
  console.log(selections)
  // val为选中元素
  selections.forEach((item) => {
    selectedArticle.push(item)
    console.log(item.id)
  })
  console.log(selectedArticle)
}

//点击按钮事件
const handleClick = (value) => {
  console.log(value)
}

// 点击预览封面
const showImg = (url) => {
  console.log(url)
}
let dialogVisible = ref(false)
  let picture_url  = ref('')

</script>

<style scoped>
.coverImg {
  height: 80px;
  width: 150px;
  background-color: pink;
}
.pagination{
  width: 100%;
  margin-top: 15px;
  display: flex;
  text-align: center;
  justify-content: center;
}
</style>
