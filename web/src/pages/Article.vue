<template>
  <!--  -->
  <Background />
  <div class="container">
    <div class="preview ">
      <md-editor
      class="md-editor basic-box"
      v-model="articleData.text"
      :editor-id="articleData.id"
      :theme="articleData.theme"
      preview-only />
    </div>
    <div class="aside">
    <div class="theme basic-box" @click="test"></div>
    <div class="catalog basic-box" ref="catalog">
      <md-catalog
            class="md-catalog"
            :editor-id="articleData.id"
            :scroll-element="scrollElement"
            :scrollElementOffsetTop="articleData.distance" />
    </div>
    </div>
  </div>
</template>

<script setup>
import {reactive,ref,onMounted} from 'vue'
import MdEditor from 'md-editor-v3'
const MdCatalog = MdEditor.MdCatalog
const scrollElement = document.documentElement
// 文章数据
const articleData = reactive({
  theme: 'dark',
  text: '# 1\n123456\n## 234  \n123456456\n### 3\nagjaiong\nwjaihgiu\n## afhiu\n#### 4\n##### 5\n###### 6',
  id: 'my-editor',
  distance: 300,
})
const test = () => {
  console.log(window.scrollY)
}
let catalog = ref(null)


// 目录浮动效果
onMounted(() => {
  window.addEventListener('scroll', () => {
    if (catalog.value && window.scrollY > catalog.value.offsetTop - 30) {
      catalog.value.style.top = 90 + 'px'
    }
  })
})
</script>



<style scoped>

.container {
  display: flex;
  justify-content: space-between;
  margin: auto;
  width: 1200px;
  /* height: 3000px; */
  margin-top: 30px;
  /* height: 100%; */
}
.preview{
width: 900px;
position: relative;
}
/*覆盖a标签使其失效 */
.preview::after{
  content: '';
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.preview .md-editor{
  min-height: 600px;
  /* padding: 15px; */
  padding:  30px ;
}

.aside{
  width: 270px;
}

.aside .theme{
  width: 100%;
  height: 100px;
  margin-bottom: 30px;
}
.aside .catalog{
  width: 100%;
  min-height: 100px;
  position: sticky;
  padding: 15px;
}











/* 样式穿透 */
.preview >>>  h1 a{
  font-size: 50px;
}
</style>
