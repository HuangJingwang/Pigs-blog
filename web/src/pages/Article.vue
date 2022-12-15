<template>
  <div class="container">
    <MdEditor
      v-model="text"
      @onChange="onChange"
      @onSave="onSave"
      class="editor" />
  </div>
</template>

<script>
import { defineComponent, ref } from 'vue'
import MdEditor from 'md-editor-v3'

export default defineComponent({
  components: { MdEditor },

  setup() {
    let text = ref('')
    const onChange = val => {}
    const onSave = (v, h) => {
      console.log(text)
      console.log(v)
      console.log(h)
    }
    return {
      onChange,
      onSave,
      text,
    }
  },
})
</script>

<style scoped>
h1 {
  font-size: 50px;
}
.container {
  margin: auto;
  width: 1200px;
  position: relative;
  top: 100px;
  background: aqua;
}
/* 样式穿透 */
.container >>> #md-editor-v3-preview h1 {
  font-size: 32px;
}
.container >>> #md-editor-v3-preview h2 {
  font-size: 24px;
}
.container >>> #md-editor-v3-preview h3 {
  font-size: 18.72px;
}
.container >>> #md-editor-v3-preview h4 {
  font-size: 16px;
}
.container >>> #md-editor-v3-preview h5 {
  font-size: 13.28px;
}
.container >>> #md-editor-v3-preview h6 {
  font-size: 12px;
}
</style>
