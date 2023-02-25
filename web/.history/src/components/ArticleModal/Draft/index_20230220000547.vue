<template>
  <div class="container">123</div>
  <div class="box">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="title" label="Date" width="180" />
      </el-table>
  </div>
</template>

<script setup>
import { computed, } from "vue"
const props = defineProps(["draftList"])
// list 中需要的数据 ：1、标题 2、时间 、3、封面 4、作者（仅管理员账户可见所有文章） 5、浏览量 6 、标签 7分类
// 若数据值为默认值,则为空
let tableData= [{
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      }, {
        date: '2016-05-04',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1517 弄'
      }, {
        date: '2016-05-01',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1519 弄'
      }]
let list = computed(() => {
  return props.draftList
})

const test = () => {
  console.log(list)
}

const handleClick = () => {
  console.log("click")
}
</script>

<style scoped>
.container {
  width: 100px;
  height: 100px;
  background-color: red;
}
</style>
