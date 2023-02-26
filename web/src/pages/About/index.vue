<template>
<Background :title="`About`"></Background>
  <!-- <div>About</div> -->
  <MdEditor
  preview-only="true"
      v-model="article.articlesText"
      @on-change="autosave"
      @onSave="handleSave"
      class="editor"
    ></MdEditor>

    <MdEditor v-model="state.text"></MdEditor>
</template>

<script setup>
import MdEditor from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
import { ref,reactive } from 'vue'
let article = ref({
  articlesText :'# 一级标题\n随便写点啥东西\n## 二级标题\n随便再写点啥\n```\nmd-editor-v3 你为啥不整个目录配置项\n```\n### 三级标题\n#### 四级标题\n### 三级标题2\n#### 四级标题\n#### 四级标题\n##### 五级标题\n###### 六级标题\n## 二级标题2\n>!!!\n## 二级标题3\n`test`'
})

let state = reactive({text:''})
// const MdCatalog = MdEditor.MdCatalog
const autosave = () => {

}
const handleSave = () => {
  console.log(state)
  console.log(article)
}


</script>

<style scoped></style>
