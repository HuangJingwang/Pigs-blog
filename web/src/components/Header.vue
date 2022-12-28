<template>
  <div class="nav">
    <div class="container">
      <!-- logo图标及标题 -->
      <h1 class="logo">
        <span class="logoImg iconfont icon-Bad-Pig"></span>
        <div class="text">Pigs blog</div>
      </h1>

      <!-- 导航按键 -->
      <div class="navigation">
        <div class="home" @click="$router.push('/index')">
          <span class="icon iconfont icon-zhuye1"></span>
          <span>Home</span>
        </div>
        <div class="write">
          <span class="icon iconfont icon-yongyan"></span>
          <span>Write</span>
        </div>
        <div class="archives" @click="$router.push('/archives')">
          <span class="icon iconfont icon-a-Frame174"></span>
          <span>Archives</span>
        </div>
        <div class="category" @click="$router.push('/category')">
          <span class="icon iconfont icon-yingyong"></span>
          <span>Category</span>
        </div>
        <div class="about">
          <span class="icon iconfont icon-guanyu"></span>
          <span>About</span>
        </div>
        <div class="singIn">
          <span class="icon iconfont icon-yonghufill"></span>
          <span>Sign in</span>
        </div>
        <div class="search">
          <span class="icon iconfont icon-sousuo"></span>
          <span>Search</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>


export default {}
</script>

scope
<style scoped>
/* 导航栏基础样式 */
.nav {
  z-index: 100;
  /* transform: translateZ(100); */

  width: 100%;
  height: 80px;
  color: #fff;
  position: fixed;
  top: 0;
  box-shadow: 0 10px 8px rgba(0, 0, 0, 0.3);
}

.nav .container {
  padding: 0 100px;
  height: 75px;
  margin: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: relative;
  z-index: 1;
}
/* 导航栏添加半透明遮罩 */
.nav::after {
  content: '';
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  background: rgba(0, 0, 0, 0.2);
}
/* 左边logo及标题样式 */
.nav .logo {
  font-size: 35px;
  line-height: 75px;
}
.nav .text {
  float: left;
}
.nav .logoImg {
  float: left;
  margin-right: 20px;
  font-weight: 900;
  font-size: 45px;
}
/* 右侧导航按钮样式 */
.nav .navigation {
  display: flex;
}
.nav .navigation div {
  padding: 10px;
  margin-left: 2px;
  display: flex;
  border-radius: 8px;
  transition: all 0.3s;
}

.nav .navigation div:hover {
  color: #4b9696;
  background: rgba(0, 0, 0, 0.5);
}
.nav .navigation div span {
  display: block;
  font-size: 18px;
}
.nav .navigation .icon {
  margin-right: 12px;
}
</style>
