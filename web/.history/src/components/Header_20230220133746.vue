<template>
  <div :class="['header', { 'header-active': header_active }]">
    <!-- logo区 -->
    <div class="pig-left">
      <div class="logo"></div>
      <div class="title">Pigs-blog</div>
    </div>
    <!-- 搜索区 -->
    <div class="pig-middle">
      <!-- 搜索框及图标 -->
      <input type="text" class="search" placeholder="javascript 递归" />
      <span class="iconfont icon-sousuo"></span>
    </div>
    <!-- 导航区 -->
    <div class="pig-right">
      <!-- 主页 -->
      <div class="home navigation" @click="$router.push('/index')">
        <span class="iconfont icon-zhuye1"></span>
        <p>主页</p>
      </div>
      <!-- 写文章 -->
      <div class="write navigation" @click="toWrite">
        <span class="iconfont icon-yongyan"></span>
        <p>撰写</p>
      </div>
      <!-- 文章分类 -->
      <div class="categories navigation" @click="$router.push('/category')">
        <span class="iconfont icon-yingyong"></span>
        <p>分类</p>
      </div>
      <!-- 文章归档 -->
      <div class="archives navigation" @click="$router.push('/archives')">
        <span class="iconfont icon-a-Frame174"></span>
        <p>归档</p>
      </div>
      <!-- 关于本站 -->
      <div class="about navigation" @click="$router.push('/about')">
        <span class="iconfont icon-guanyu2"></span>
        <p>关于</p>
      </div>
      <!-- 用户 -->
      <div class="user navigation" @click="login_register" v-show="!state.user.isLogin">
        <span class="iconfont icon-denglu"></span>
        <p>登录</p>
      </div>
      <div class="user_login" v-show="state.user.isLogin">
        <el-dropdown>
          <div
            class="avatar"
            :style="{
              backgroundImage:
                'url(http://moon.starrysummer.com/cad31bce881d464e8a9964c97456b09e.jpg)',
            }"
          ></div>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item>绑定Github</el-dropdown-item>
              <el-dropdown-item disabled>个人主页</el-dropdown-item>
              <el-dropdown-item @click="showArticleModal">我的文章</el-dropdown-item>
              <el-dropdown-item divided @click="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue"

import { useStore } from "vuex"
import { useRouter, useRoute } from "vue-router"

const { state } = useStore()
const router = useRouter()
const route = useRoute()
// 监听页面滚动事件,改变header样式
let header_active = ref(false)
window.addEventListener("scroll", () => {
  if (document.documentElement.scrollTop > 200) {
    header_active.value = true
  } else {
    header_active.value = false
  }
})

// 导航到write 页面
const toWrite = () => {
  const writePath = router.resolve({
    path: "/write",
  })
  window.open(writePath.href, "_blank")
}

// 点击弹出登录框
const login_register = () => {
  state.user.showUserModal = true
  console.log(state.user.showUserModal)
}

// 登录状态下拉栏
// 管理文章
const showArticleModal = () => {
  state.showArticleModal = true
}

// 
</script>

<style scoped>
/* 整体布局 */
.header {
  height: 50px;
  width: 100%;
  padding: 0 20px;
  position: fixed;
  top: 0;
  transition: all 0.5s;
  display: flex;
  justify-content: space-between;
  box-shadow: 0 10px 8px rgba(0, 0, 0, 0.3);
  background: rgba(0, 0, 0, 0.3);
}

.header-active {
  height: 45px;
  background-color: rgb(124, 131, 151);
}

.header .pig-left,
.header .pig-right {
padding-right: 30px;
  width: 450px;
}

.header .pig-middle {
  min-width: 400px;
}

/* logo区 */
.pig-left {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}
.pig-left .logo {
  background-image: url(@/assets/img/logo.png);
  background-repeat: no-repeat;
  background-size: contain;
  width: 40px;
  height: 40px;
  margin-left: 50px;
}
.pig-left .title {
  font-size: 24px;
  font-weight: 900;
  color: rgb(200, 200, 200);
  margin-left: 10px;
}

/* 搜索区 */
.pig-middle {
  position: relative;
  display: flex;
  align-items: center;
  padding: 0 30px;
}
.search {
  padding-left: 20px;
  padding-right: 30px;
  border-radius: 13px;
  background-color: rgba(200, 200, 200, 0.5);
  border: none;
  height: 26px;
  width: 100%;
  min-width: 100px;
  outline: rgb(44, 44, 44);
  color: rgb(39, 38, 38);
}
.search::-webkit-input-placeholder {
  color: rgb(97, 95, 95);
}

.pig-middle .iconfont {
  position: absolute;
  top: 60%;
  transform: translateY(-50%);
  right: 30px;
  width: 26px;
  height: 26px;
  font-size: 16px;
}

/* 导航区 */
.pig-right {
  display: flex;
  align-items: center;
  justify-content: flex-end;
}
.pig-right .home,
.pig-right .write,
.pig-right .categories,
.pig-right .archives,
.pig-right .about,
.pig-right .user {
  padding-top: 3px;
  border-radius: 5px;
  position: relative;
  overflow: hidden;
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 50px;
  height: 30px;
  margin-right: 16px;
  text-align: center;
  font-size: 12px;
}
.pig-right .home p,
.pig-right .write p,
.pig-right .categories p,
.pig-right .archives p,
.pig-right .about p,
.pig-right .user p {
  position: absolute;
  top: 32px;
  transition: all 0.3s;
  color: rgb(200, 200, 200);
  zoom: 0.9;
}
.navigation .iconfont {
  color: rgb(200, 200, 200);
  font-size: 24px;
  transition: all 0.3s;
}
/* .navigation:hover {
  background:rgb(120, 120, 120);
  } */
.navigation:hover .iconfont {
  color: #eee;
  font-size: 15px;
}
.navigation:hover p {
  color: #eee;
  top: 18px;
}
.user_login .avatar {
  width: 32px;
  height: 32px;
  background-position: center center;
  background-size: contain;
  border-radius: 50%;
}
</style>
