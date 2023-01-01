<template>
  <div class="container">
    <div class="pubConfig">
      <input
        type="text"
        class="title"
        placeholder="请输入标题..."
        v-model="state.title" />
      <!-- <div class="route">{{ $route.meta.showComponent }}</div> -->
      <div class="right">
        <div class="saveInfo">文章将保存至草稿箱</div>
        <button class="draft" >草稿箱</button>
        <button class="public" @click="config">发布</button>
        <div class="user">头像</div>
      </div>
    </div>
    <MdEditor v-model="state.text" class="editor"></MdEditor>
    <!-- <MdCatalog></MdCatalog> -->
  </div>
</template>

<script setup>
import { reactive, onMounted } from 'vue'
import MdEditor from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
const MdCatalog = MdEditor.MdCatalog
const scrollElement = document.documentElement
const state = reactive({
  title: '',
  text: '# 标题\n## 123\n ### 123\n# 234',
  time: 123,
})

onMounted(() => {})
</script>

<style scoped>
.container {
  width: 100%;
  min-height: 100%;
  display: flex;
  flex-direction: column;
}

.container .pubConfig {
  padding: 12px 50px;
  height: 80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.container .title {
  background: #eee;
  height: 100%;
  width: 1000px;
  border: 0px solid #000;
  font-weight: 900;
  font-size: 32px;
}

.container .right {
  display: flex;
  align-items: center;
}
.container .user {
  width: 45px;
  height: 45px;
  line-height: 45px;
  text-align: center;
  border-radius: 50%;
  background-color: pink;
  margin-left: 20px;
}
.container .public {
  padding: 0 20px;
  height: 32px;
  color: #fff;
  font-size: 14px;
  background: pink;
  border: 1px solid pink;
  border-radius: 10%;
  margin-left: 20px;
}
.container .draft {
  padding: 0 20px;
  height: 32px;
  color: #fff;
  font-size: 14px;
  background: pink;
  border: 1px solid pink;
  border-radius: 10%;
  margin-left: 20px;
}
.container .editor {
  flex: 1;
}
</style>
