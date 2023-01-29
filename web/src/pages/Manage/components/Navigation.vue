<template>
   <div class="container">
    <el-tabs v-model="activeName" class="demo-tabs" @tab-click="handleClick" closable = "true">
    <el-tab-pane label="User" name="first">User</el-tab-pane>
    <el-tab-pane label="Config" name="second">Config</el-tab-pane>
    <el-tab-pane label="Role" name="third">Role</el-tab-pane>
    <el-tab-pane label="Task" name="fourth">Task</el-tab-pane>
  </el-tabs>
   </div>
</template>

<script  setup>
import { ref } from 'vue'
// import type { TabsPaneContext } from 'element-plus'

const activeName = ref('first')

const handleClick = (tab, event) => {
  console.log(tab, event)
}
</script>

<style  scoped>
.container{
    width: 100%;
    height: 30px;
}
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
</style>