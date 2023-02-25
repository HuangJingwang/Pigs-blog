<template>
    <div class="container" @click="test">
草稿箱
    </div>
</template>

<script setup>
import {computed} from 'vue'
const props = defineProps(['draftList'])
console.log(props)
// list 中需要的数据 ：1、标题 2、时间 、
let list =computed(()=> props.draftList)
const test = () => {
    console.log(list)
}
</script>

<style lang="scss" scoped>

</style>