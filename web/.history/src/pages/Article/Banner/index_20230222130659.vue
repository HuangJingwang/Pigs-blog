<template>
<div class="cover">
    <div class="articleInfo"></div>
    
</div>
</template>

<script setup>
const props = defineProps(['articleData'])
console.log(props.articleData)
</script>

<style  scoped>
.cover{
    width: 100%;
    height: 400px;
    background-color: aqua;
}
</style>