<template>
  <div class="container" @click="test">
    <el-table :data="list">
    

      <el-table-column fixed="right" label="Operations" width="120">
        <template #default>
          <el-button link type="primary" size="small" @click="handleClick"
            >Detail</el-button
          >
          <el-button link type="primary" size="small">Edit</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script setup>
import { computed } from "vue"
const props = defineProps(["draftList"])
console.log(props)
// list 中需要的数据 ：1、标题 2、时间 、3、封面 4、作者（仅管理员账户可见所有文章） 5、浏览量 6 、标签 7分类
// 若数据值为默认值,则为空

let list = computed(() => {
 return  props.draftList
})
const test = () => {
  console.log(list)
}
</script>

<style lang="scss" scoped></style>
