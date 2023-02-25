<template>
  <div class="cover" :style="{ backgroundImage: `url(${articleData.img_url})` }">
    <div class="articleInfo">
      <div class="title">
        {{ articleData.title }}
      </div>
      <div class="breads">
        <div class="author">
          <span class="iconfont icon-gaojian-zuozhe"></span>
          <p class="text">作者: {{ articleData.account }}</p>
        </div>
        <span
          :style="{
            height: '50px',
            lineHeight: '50px',
            fontSize: '24px',
          }"
          >\</span
        >
        <div class="date">
          <span class="iconfont icon-shijian1"></span>
          <span>更新于: {{ articleData.update_at }}</span>
        </div>
        <span :style="{ height: '50px', lineHeight: '50px', fontSize: '24px' }">\</span>
        <div class="category">
          <span class="iconfont icon-icon-folder"></span>
          <span> 文章分类: {{ articleData.group_name }}</span>
        </div>
        
      </div>
   
    </div>
  </div>
</template>

<script setup>
const props = defineProps(["articleData"])
console.log("文章数据", props.articleData.update_at)
console.log("props传参", props.articleData)
</script>

<style scoped>
.cover {
  width: 100%;
  height: 400px;
  position: relative;
  background-size: cover;
  background-position: center center;
}
.cover::before {
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.3);
}
.cover .articleInfo {
    background:pink;
  width: 750px;
  height: 120px;
  position: absolute;
  color: #fff;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -20%);
}
.articleInfo .title {
  width: 100%;
  font-size: 32px;
  color: #fff;
  text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.5);
  line-height: 40px;
  text-align: center;
  align-items: center;
}
.articleInfo .breads {
  width: 100%;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.breads .iconfont {
  font-size: 16px;
  margin-right: 8px;
}
.breads .author,
.breads .date,
.breads .category {
  display: flex;
  align-items: center;
  font-size: 14px;
  margin: 15px;
}

.footer{
    display: flex;
}
</style>
