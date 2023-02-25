<template>
  <!-- <el-button text @click="dialogVisible = true"> click to open the Dialog </el-button> -->
  <el-dialog
    v-model="state.showArticleModal"
    title="文章管理"
    width="1300px"
    :before-close="handleClose"
    :lock-scroll="false"
  >
    <!-- <span>This is a message</span> -->
    <!-- 子组件 -->

    <!-- 头部导航 -->
    <div class="header">
    <div class="btn_published"></div>
    <div class="btn_draft"></div>
    <div class="btn_dustbin"></div>
    </div>
    <Published />
    <Draft />
    <Dustbin />
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="state.showArticleModal = false">Cancel</el-button>
        <el-button type="primary" @click="state.showArticleModal = false">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
// import { ref } from "vue"
import { useStore } from "vuex"
import Published from "./Published"
import Draft from "./Draft"
import Dustbin from "./Dustbin"

// 引入子组件

// import { ElMessageBox } from "element-plus"
const { state } = useStore()
// 关闭按钮
const handleClose = (done) => {
  state.showArticleModal = false
  console.log(state)
}
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
.header{
  display: flex;

}
.btn_published,.btn_draft,.btn_dustbin{
  width: 100px;
  background-color: aqua;
  height: 20px;
}
.el-overlay-dialog >>>
</style>
