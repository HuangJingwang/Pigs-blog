<template>
  <!-- 路由组件 -->
  <router-view></router-view>
  <!-- 页脚 -->
  <Footer v-if="$route.meta.showComponent"></Footer>
  <!-- 登录模块 -->
  <UserModal ></UserModal>
  <!-- 头部导航 -->
  <Header
    v-if="$route.meta.showComponent"
  ></Header>
  <!-- 回到顶部 -->
<BackToTop></BackToTop>
<!-- 文章管理模块 -->
  
<!-- test element -->
<el-button text @click="dialogVisible = true">
    click to open the Dialog
  </el-button>

  <el-dialog
    v-model="dialogVisible"
    title="Tips"
    width="30%"
    :before-close="handleClose"
    :lock-scroll="false"
  >
    <span :style="{fontSize : "32px"}">This is a message</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>
        <el-button type="primary" @click="dialogVisible = false">
          好的
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { ElMessageBox } from 'element-plus'


import { onMounted, ref,  } from 'vue'
import { useStore } from 'vuex'
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
import UserModal from './components/UserModal'
import BackToTop from './components/BackToTop.vue'
import ArticleModal from './components/ArticleModal'
import { size } from 'lodash'
const { state, dispatch } = useStore()

let result = ref(null)
// 请求分类列表数据
onMounted(async() => {
  dispatch('reqGroupList')
})




const dialogVisible = ref(false)

// const handleClose = (done) => {
//   ElMessageBox.confirm('Are you sure to close this dialog?')
//     .then(() => {
//       done()
//     })
//     .catch(() => {
//       // catch error
//     })
// }
</script>
<style>
.basic-box {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.15), 0 8px 26px 0 rgba(0, 0, 0, 0.15);
}
.box-shadow {
  box-shadow: 0 5px 10px 0 rgba(0, 0, 0, 0.15), 0 8px 26px 0 rgba(0, 0, 0, 0.15);
}

.dialog-footer button:first-child {
  margin-right: 10px;
}
</style>
