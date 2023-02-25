<template>
  <!-- <el-button text @click="dialogVisible = true"> click to open the Dialog </el-button> -->
  <el-dialog v-model="state.showArticleModal" title="文章管理" width="1200px" :before-close="handleClose" :lock-scroll="true">
    <span>This is a message</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="state.showArticleModal = false">Cancel</el-button>
        <el-button type="primary" @click="state.showArticleModal = false"> Confirm </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ref } from "vue"
import { useStore } from "vuex";
import { ElMessageBox } from "element-plus"
// console.log()
const {state} = useStore()
const handleClose = (done) => {
    .then(() => {
      done()
    })
    .catch(() => {
      // catch error
    })
}
</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
