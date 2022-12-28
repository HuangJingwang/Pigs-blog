<template>
  <div class="previewBox" ref="previewBox">
    <md-editor
      class="md-editor"
      v-model="state.text"
      :editor-id="state.id"
      :theme="state.theme"
      preview-only />
    <md-catalog
      class=""
      :editor-id="state.id"
      :scroll-element="scrollElement"
      :offsetTop="offsetTop"
      :scrollElementOffsetTop="state.scrollElementOffsetTop" />
  </div>
</template>

<script setup>
import { reactive, ref, onMounted } from 'vue'
import MdEditor from 'md-editor-v3'

const MdCatalog = MdEditor.MdCatalog
const scrollElement = document.documentElement

const state = reactive({
  theme: 'dark',
  text: '# 标题\n## 123\n ### 123\n# 234\n## 234\n### 234\n# 345\naerkhbnkfdml;amrgbfkz\n123\n12\n1\n1\n1\n1\n1\n1\n1\n1\n1\n123456\naerkhbnkfdml;amrgbfkz\n123\n12\n1\n1\n1\n1\n1\n1\n1\n1\n1\n123456\naerkhbnkfdml;amrgbfkz\n123\n# qqqq \n12\n1\n1\n1\n1\n1\n1\n1\n1\n1\n123456\naerkhbnkfdml;amrgbfkz\n123\n# qqqq \n12\n1\n1\n1\n1\n1\n1\n1\n1\n1\n123456\naerkhbnkfdml;amrgbfkz\n123\n# qqqq \n12\n1\n1\n1\n1\n1\n1\n1\n1\n1\n123456',
  id: 'my-editor',
  scrollElementOffsetTop: 300,
  offsetTop: 300,
})
const previewBox = ref(null)
onMounted(() => {})
</script>

<style scoped>
.md-editor {
  width: 900px;
  padding: 30px;
  background-color: pink;
}
.previewBox {
  display: flex;
}
</style>
