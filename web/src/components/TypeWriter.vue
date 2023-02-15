<template>
<div class="container">
  <div class="saying">
    {{ typeOptions.output }}
    <span class="typed-cursor">|</span>
  </div></div>
</template>

<script setup>
import { reactive, onMounted } from 'vue'
import EasyTyper from 'easy-typer-js'
let typeOptions = reactive({
  output: '',
  isEnd: false,
  speed: 200,
  singleBack: false,
  sleep: 20,
  type: 'rollback',
  backSpeed: 500,
  sentencePause: true,
  // 其他参数省略
})
onMounted(() => {
  const typed = new EasyTyper(typeOptions, `zzy你什么时候把分类页面写出来?!O.o`)
})
</script>

<style scoped>

.container{
  margin-top: 15px;
  width:1350px;
  background-color: #fff;
  position: relative;
}
.saying {
  position: absolute;
  top: 50%;
  left: 50%;
transform: translate(-50%,-50%);
  /* font-family: monospace; */
  color: rgb(200, 192, 192);
  font-size: 24px;
  text-shadow: 2px 2px 3px rgba(0, 0, 0, 0.5);
  height: 20px;
  line-height: 20px;
}

.typed-cursor {
  opacity: 1;
  animation: blink 1s infinite ;
}
@keyframes blink {
  0% {
    opacity: 1;
  }

  50% {
    opacity: 0;
  }

  100% {
    opacity: 1;
  }
}
</style>
