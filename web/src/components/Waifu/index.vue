<template>
  <div></div>
</template>

<script setup>
import { onMounted } from "vue"
import { useUserStore } from "@/store/user";
const userStore = useUserStore()
onMounted(() => {
if(!userStore.waifuActive)createScript()
})
function createScript() {
    let script = document.createElement("script")
  script.type = "text/javascript"
  script.src =
    "https://fastly.jsdelivr.net/gh/stevenjoezhang/live2d-widget@latest/autoload.js"
    document.body.appendChild(script)
userStore.waifuActive = true
}
</script>

<style  scoped>
#waifu-toggle.waifu-toggle-active {
}
</style>
