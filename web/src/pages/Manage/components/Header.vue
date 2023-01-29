<template>
  <div class="header">
    <div class="logo"></div>
    <div class="nav">
      <div class="write">
        <span class="icon iconfont icon-yongyan"></span>
        <p class="text ">新建</p>
      </div>
      <div class="home">
        <span class="icon iconfont icon-zhuye1"></span>
        <p class="text">主页</p>
      </div>
      <div class="catagories">
        <span class="icon iconfont icon-yingyong"></span>
        <p class="text">分类</p>
      </div>
      <div class="archives">
        <span class="icon iconfont icon-a-Frame174"></span>
        <p class="text">归档</p>
      </div>
      <div class="user">
        <span class="icon iconfont icon-weibiaoti-_huabanfuben"></span>
        <p class="text">用户</p>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.header {
  width: 100%;
  height: 50px;
  
  font-size: 16px;
  background-color: rgb(47, 65, 84);
  display: flex;
  justify-content: space-between;
  padding: 0 30px;
}
.header .logo {
  width: 100px;
  height: 50px;
  background-color: aqua;
}
.header .nav {
    width: 180px;

  display: flex;
  justify-self: space-between;
}
.nav .home,.catagories ,.archives ,.user,.write{
    width: 50px;
    height: 50px;
    /* background-color: red; */
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
}
.nav .icon {
    font-size: 16px;
    color: #fff;
}
.nav .text {
  font-size: 8px;
  color: #fff;
}
</style>
