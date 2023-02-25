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
      <div
        :class="['btn_published', { active: btn_active == 'published' ? true : false }]"
      @click="showPublished">
        已发表
      </div>
      <div :class="['btn_draft', { active: btn_active == 'draft' ? true : false }]" @click="showDraft">
        草稿箱
      </div>
      <div :class="['btn_dustbin', { active: btn_active == 'dustbin' ? true : false }]" @click="showDustbin">
        垃圾箱
      </div>
    </div>
    <hr class="line" />
    <Published v-show="btn_active == 'published' ? true : false" />
    <Draft  v-show="btn_active == 'draft' ? true : false" />
    <Dustbin  v-show="btn_active == 'dustbin' ? true : false" />
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
import { ref ,onMounted} from "vue"
import { useStore } from "vuex"
import Published from "./Published"
import Draft from "./Draft"
import Dustbin from "./Dustbin"
import { getPublishedList } from "@/api"
// import { ElMessageBox } from "element-plus"
const { state } = useStore()
// 关闭按钮
const handleClose = (done) => {
  state.showArticleModal = false
  console.log(state)
}

// 设置按钮active状态
let btn_active = ref("published")
// 设置事件更改btn——active
const showPublished = ()=>{
  btn_active.value = 'published'
}

const showDraft = ()=>{
  btn_active.value = 'draft'
}
const showDustbin = ()=>{
  btn_active.value = 'dustbin'
}


</script>
<style scoped>
.dialog-footer button:first-child {
  margin-right: 10px;
}
.header {
  display: flex;
  width: 280px;
  justify-content: space-between;
  padding-left: 20px;
  margin-bottom: 10px;
}
.line {
  border: 1px solid rgba(0, 0, 0, 0.3);
}
/* 导航按钮 */
.btn_published,
.btn_draft,
.btn_dustbin {
  position: relative;
  width: 80px;
  font-size: 16px;
  height: 30px;
  line-height: 30px;
  text-align: center;
}
.btn_published::after,
.btn_draft::after,
.btn_dustbin::after {
  content: "";
  position: absolute;
  bottom: 0;
  left: 50%;
  transform: translateX(-50%);
  height: 2px;
  width: 0;
  border-radius: 1px;
  background-color: rgb(3, 145, 167);
  transition: all 0.2s;
}
.btn_published:hover::after,
.btn_draft:hover:after,
.btn_dustbin:hover:after {
  width: 100%;
}
.active {
  border-radius: 5px;
  background-color: #eee;
}
</style>
