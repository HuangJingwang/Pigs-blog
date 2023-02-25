<template>
<div class="cover">
    <div class="articleInfo">
    <div class="title">标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字标题很多字</div>
    
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
    width: 700px;
    height: 200px;
    background-color: pink;
position: absolute;
top: 50%;
left: 50%;
transform: translate(-50%,-50%);
}
 .articleInfo .title{
    width: 100%;
    height: 100px;
    font-size: 20px;
    line-height: 50px;
    background-color: #eee;
 }
</style>