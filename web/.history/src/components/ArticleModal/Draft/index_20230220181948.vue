<template>
  <div class="container" @click="test">123</div>
  <div class="box">
      <el-table :data="list" style="width: 100%">
        <el-table-column prop="title" label="123" width="180" />
      </el-table>
  </div>
</template>

<script setup>
import { computed, } from "vue"
const props = defineProps(["draftList"])
// list 中需要的数据 ：1、标题 2、时间 、3、封面 4、作者（仅管理员账户可见所有文章） 5、浏览量 6 、标签 7分类
// 若数据值为默认值,则为空

      // 列表数据
let list = computed(() => {
  props.draftList.resultList.forEach((item) => {
    item.title = item.title.Unspecified? 
  })

  return props.draftList.resultList
})

const test = () => {
    console.log(props.draftList)
    console.log(list)
}


</script>

<style scoped>
.container {
  width: 100px;
  height: 100px;
  background-color: red;
}
</style>
