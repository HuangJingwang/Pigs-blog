<template>
  <div class="options">
    <div class="overview">网站总览</div>
    <div class="overview">个人信息</div>
    <el-collapse v-model="activeNames">
      <el-collapse-item title="文章管理" name="1">
        <div class="published">已发布文章</div>
        <div class="draft">草稿箱</div>
        <div class="rubbish">垃圾箱</div>
      </el-collapse-item>
      <el-collapse-item title="文章分类" name="2">
        <div class="category">分类管理</div>
        <div class="tags">标签管理</div>
      </el-collapse-item>
    </el-collapse>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const activeNames = ref(['1'])
const handleChange = () => {
  console.log(123)
}
</script>

<style scoped>
.options {
  width: 300px;
  height: 100%;
  background-color: rgb(84, 92, 100);
}
.overview {
  /* background-color: ; */
  text-align: center;
  line-height: 50px;
  font-size: 14px;
  height: 50px;
  color: #fff;
}

.options >>> .el-collapse .el-collapse-item__header {
  background-color: rgb(84, 92, 100);
  margin: 0;
  color: #fff;
}
.options >>> .el-collapse .el-collapse-item__content {
  border-color: rgb(67, 74, 80);
  padding: 0;
}

.options >>> .el-collapse .el-collapse-item__content div {
  color: #fff;
  line-height: 40px;
  text-align: center;
  height: 40px;
  background-color: rgb(67, 74, 80);
  border-bottom: none;
}

.options >>> .el-collapse .el-collapse-item__header {
  padding-left: 125px ;
  /* text-align: center; */
  border-bottom: 1px solid rgb(84, 92, 100);
}
::v-deep .el-collapse {
  border-color: rgb(84, 92, 100);
}
::v-deep .el-collapse-item__wrap {
  border-color: rgb(84, 92, 100);
}
</style>
