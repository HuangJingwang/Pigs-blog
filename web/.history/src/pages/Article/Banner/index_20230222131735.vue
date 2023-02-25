<template>
<div class="cover">
    <div class="articleInfo">
    <div class="title"></div>
    
    </div>
    
</div>
</template>

<script setup>
const props = defineProps(['articleData'])
console.log('props传参',props.articleData)
</script>

<style  scoped>
.cover{
    width: 100%;
    height: 400px;
    background-color: aqua;
    position: relative;
}
.cover .articleInfo{
    width: 950px;
    height: 200px;
    background-color: pink;
position: absolute;
top: 50%;
left: 50%;
transform: translate(-50%,-50%);
}
 .articleInfo .title{
    width: 100%;
    height: 60px;
    font-size: 24px;
 }
</style>