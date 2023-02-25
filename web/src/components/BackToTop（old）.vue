<template>
  <div :class="['btn', 'iconfont', 'icon-huidaodingbu', { btn_hide: hide }, { btn_show: !hide }]" @click="backTop"></div>
</template>

<script setup>
import { ref } from 'vue'
import debounce from '@/utils/debounce'
// 判断样式
let hide = ref(true)
window.addEventListener('scroll', () => {
// 显示隐藏按钮
  if (window.pageYOffset > window.innerHeight) {
    hide.value = false
  } else {
    hide.value = true
  }
})
// 返回功能
const backTop = () => {
    window.scrollTo(
        {
            top: 0,
            behavior:'smooth'
        }
    )
}
</script>

<style scoped>
/* 基础 */
.btn {
  width: 45px;
  height: 50px;
  position: fixed;
  font-size: 32px;
  text-align: center;
  line-height: 50px;
  background-color: rgb(210, 208, 208);
  border-radius: 10px   ;
  right: 100px;
  transition: all 0.3s;
}
/* 隐藏 */
.btn_hide {
  bottom: -50px;
}
/* 显示 */
.btn_show {
  bottom: 50px;
}
</style>
