<template>
    <div class="container" @click="test">
草稿箱
    </div>
</template>

<script setup>
import {computed} from 'vue'
const props = defineProps(['draftList'])
console.log(props)
let list =computed(()=> props.draftList)
const test = () => {
    console.log(list)
}
</script>

<style lang="scss" scoped>

</style>