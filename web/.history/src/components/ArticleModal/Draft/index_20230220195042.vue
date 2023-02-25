<template>
  <div class="container" @click="test">123</div>
  <el-table
    :data="list"
    style="width: 100%"
    ref="multipleTableRef"
    @selection-change="handleSelectionChange"
  >
  <!-- 多选框 -->
    <el-table-column type="selection" width="55" />
    <!-- 账号 -->
    <el-table-column
      prop="account"
      label="author"
      width="180"
      :style="{ textAlign: 'center' }"
    />
<!-- 日期 -->
    <el-table-column label="Date" width="120">
    <!-- 封面 -->
      <template #default="scope">
        <div class="coverImg">{{ scope.row.title }}</div>
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
    item.title = item.title == "Unspecified" ? "noData" : item.title
  })
  return props.draftList.resultList
})
const test = () => {
  console.log(list)
}
const multipleTableRef = ref()
const multipleSelection = ref([])
const handleSelectionChange = (val) => {
  multipleSelection.value = val
  console.log(val)
  // val为选中元素
  let selectedArticle = []
}
</script>

<style scoped>
.container {
  width: 100px;
  height: 100px;
  background-color: red;
  text-align: center;
}
.coverImg{
  height: 100px;
  width: 200px;
  background-color: blue;
}
</style>
