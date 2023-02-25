<template>
  <el-table
    :data="list"
    style="width: 100%"
    ref="multipleTableRef"
    @selection-change="handleSelectionChange"
  >
  <!-- 多选框 -->
    <el-table-column type="selection" width="55" fixed />
    <!-- 账号 -->
    <el-table-column
      prop="account"
      label="author"
      width="180"
      :style="{ textAlign: 'center' }"
    />
    <!-- 封面 -->
      <template #default="scope">
        <div class="coverImg" @click="showImg(scope.row.title)">{{ scope.row.title }}</div>
      </template>
    </el-table-column>
    <el-table-column prop="account" label="author" width="180" />
  </el-table>
</template>

<script setup>
import { computed, ref } from "vue"
const props = defineProps(["draftList"]) 
// list 中需要的数据 ：1、标题 2、时间 、3、封面 4、作者（仅管理员账户可见所有文章） 5、浏览量 6 、标签 7分类
// 若数据值为默认值,则为空
// 列表数据
let list = computed(() => {
  props.draftList.resultList.forEach((item) => {
    // 处理标题数据
    item.title = item.title == "Unspecified" ? "noData" : item.title
// 处理
  })
  return props.draftList.resultList
})


let selectedArticle = []
const multipleTableRef = ref()
const multipleSelection = ref([])
const handleSelectionChange = (val) => {
  multipleSelection.value = val
  console.log(val)
  // val为选中元素
  val.forEach((item) => {
    selectedArticle.push(item)
    console.log(item.id)
  })
  console.log(selectedArticle)
}



// 点击预览封面
const showImg = (url) => {
  console.log(url)
}
</script>

<style scoped>

.coverImg{
  height:60px;
  width: 112.5px;
  background-color: blue;
}
</style>
