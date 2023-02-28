<template>
<background :title="`About`"></background>
<div class="container basic-box">
    <div class="partner1">
        <img src="https://avatars.githubusercontent.com/u/106206840?v=4" alt="">
       <div class="text">
        <!-- <span >haishanliao</span> -->
        <a href="https://github.com/haishanliao" class="partnerName"> 海山了</a>
        <span class="partnerJob">前端</span>
        <span class="partnerWords">纸上得来终觉浅，绝知此事要躬行</span>
       </div>
    </div>
    <div class="partner2">
        <img src="https://avatars.githubusercontent.com/u/77671411?v=4" alt="" @click="hjw">
       <div class="text">
        <a href="https://github.com/HuangJingwang" class="partnerName">Komorebi</a>
        <span class="partnerJob">后端</span>
        <span class="partnerWords">给我来一个清纯的。</span>
       </div>
    </div>

    <div class="webIntroduce">
        <p>2023年寒假开始搭建部署的一个学习总结、经验分享博客</p>
        <p>经过自我驱动深入学习之后，希望可以做一些产出，同时也想用顺手的语言实现个人喜欢的效果</p>
        <p>除了交流经验，找到更多志同道合的朋友，希望这个网站可以成为我在语言学习深入过程中的纪念碑</p>
        <p>了解更多可以点击头像关注我们github账号</p>
    </div>
</div> 
</template>

<script setup>
    function qjl(){

    }
</script>

<style scoped>
    .container{
        width: 1200px;
        min-height: 400px;

        margin: 0 auto;
        padding: 1px;
        transform:translateY(-50px)

    }
    .partner1{
        width: 240px;
        height: 340px;
        /* background-color: rgb(133, 113, 87); */
        position: absolute;
        left: 200px;
        transform:translateY(-80px);
        
    }
    .partner2{
        width: 240px;
        height: 340px;
        /* background-color: rgb(133, 113, 87); */
        position: absolute;
        right: 200px;
        transform:translateY(-80px);
        
    }
    img{
        margin: 0 20px;
        width: 200px;
        height: 200px;

        border: 10px white solid;
        border-radius: 50%;
        box-shadow: 0 0 5px #000;
    }
    .text{
        /* border-top: 60px; */
        position: absolute;
        bottom: 20px;;
    } 
    span{
            inline-size: 240px;
            text-align: center;
            line-height: 30px;
            display: block;
    }
    a{
            inline-size: 240px;
            text-align: center;
            line-height: 30px;
            display: block;

            color: #263a4f;
            text-decoration: none;
    }
    .partnerName{
        
        font-size :2rem;
        font-weight: bold;
    }
    .partnerWords{
        font-size :1rem;

    }
    .partnerJob{
        font-size :1.3rem;

    }
    
    /* 文字介绍 */
    .webIntroduce{

        width: 1000px;
        min-height: 200px;
        /* position: absolute; */
        /* top: 300px; */
        /* left: 100px; */

        /* background-color: antiquewhite; */

        /* display: flex; */
        margin: 300px auto 0;
        padding: 20px 0 40px 70px;
    }
    p{
        font-size: 1.3rem;

        margin-top: 0;
        margin-bottom: 18px;
    }
</style>
