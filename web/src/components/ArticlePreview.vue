<template>
  <div class="previewBox">
    <!-- 文章展示主题 -->
    <md-editor
      class="md-editor basic-box"
      v-model="state.text"
      :editor-id="state.id"
      :theme="state.theme"
      preview-only />
    <div class="aside">
      <!-- 切换主题 -->
      <div class="theme basic-box" @click="testLog" ref="theme"></div>
      <!-- 目录 -->
      <!-- <teleport to="body"> -->
        <div class="catalog" ref="catalog">
          <md-catalog
            class="md-catalog basic-box"
            :editor-id="state.id"
            :scroll-element="scrollElement"
            :scrollElementOffsetTop="state.scrollElementOffsetTop" />
        </div>
      <!-- </teleport> -->
    </div>
    <!-- 热门文章 -->
    <div class="hot"></div>
  </div>
</template>

<script setup>
import { reactive, ref, onMounted, computed } from 'vue'
import MdEditor from 'md-editor-v3'
const MdCatalog = MdEditor.MdCatalog
const scrollElement = document.documentElement
const state = reactive({
  theme: 'dark',
  text: '# 1\n123\n## 2\n### 3\n#### 4\n##### 5\n###### 6',
  id: 'my-editor',
  scrollElementOffsetTop: 300,
})

// 目录跟随页面悬停
const catalog = ref(null)

onMounted(() => {
  // console.log(catalog.value)
})
// 测试
// const theme = ref(null)

const testLog = () => {
  // console.log(123)
  console.log(catalog.value.offsetTop)
  console.log(window.scrollY)
}

onMounted(() => {})
</script>

<style scoped>
.md-editor {
  width: 900px;
  padding: 30px;
}
.previewBox {
  display: flex;
  width: 100%;
  justify-content: space-between;
}
.aside .theme {
  width: 275px;
  height: 100px;
  margin-bottom: 30px;
}
.previewBox .md-catalog {
  padding: 20px 15px;
  width: 275px;
}
</style>
