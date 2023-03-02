<template>
  <div class="container">
    <div class="saying">
      {{ typeOptions.output }}
      <span class="typed-cursor" v-show="sayingCursor">|</span>
    </div>
    <div class="author">
      {{ typeOptions2.output }}
      <span class="typed-cursor" v-show="authorCursor">|</span>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted, ref } from "vue"
import { getSayings } from "@/api"
import EasyTyper from "easy-typer-js"
let sayingCursor = ref(true)
let authorCursor = ref(false)
let typeOptions = reactive({
  output: "",
  isEnd: false,
  speed: 200,
  singleBack: false,
  sleep: 20,
  type: "rollback",
  backSpeed: 500,
  sentencePause: true,
  // 其他参数省略
})
let typeOptions2 = reactive({
  output: "",
  isEnd: false,
  speed: 200,
  singleBack: false,
  sleep: 3000,
  type: "rollback",
  backSpeed: 500,
  sentencePause: true,
  // 其他参数省略
})
// 进入页面请求saying
onMounted(() => {
  getSayings()
    .then((res) => {
      console.log("res", res.data.logion)
      console.log("res author", res.data.author)
      new EasyTyper(typeOptions, res.data.logion, () => {
        new EasyTyper(typeOptions2,  '————'+ res.data.author,()=>{authorCursor.value = false})
        sayingCursor.value = false
        authorCursor.value = true
      })
    })
    .catch((err) => {
      console.log(err)
    })
})
</script>

<style scoped>
.container {
  width: 1350px;
  min-height: 200px;
  margin-top: 15px;
  position: relative;
  display: flex;
  flex-direction: column;
justify-content: space-around;
}
.saying {
  margin-top: 20px;
  font-size: 24px;
  line-height: 40px;
  display: block;
  text-align: center;
  position: absolute;
  top: 60px;
  left: 50%;
  transform: translate(-50%, -50%);
  color: rgb(200, 192, 192);
  text-shadow: 2px 2px 3px rgba(0, 0, 0, 0.5);
  margin-bottom: 30px;
}
.author {
  margin-top: 30px;
  width: 100%;
  text-align: start;
  font-size: 24px;
  line-height: 40px;
  color: rgb(200, 192, 192);
  padding-left: 900px;
  position: absolute;  
  top: 140px;
  left: 50%;
  transform: translate(-50%, -50%);
}
.typed-cursor {
  opacity: 1;
  animation: blink 1s infinite;
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
