<template>
<div class="container basic-box">
    <div id="clock">
        <p class="date">{{ date }}</p>
        <p class="time">{{ time }}</p>
    </div></div>
</template>

<script setup>
import { ref } from 'vue';
        let date = ref('')
        let time = ref('')
        var week = ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'];
        var timerID = setInterval(updateTime, 1000);
        updateTime();

        function updateTime() {
            var cd = new Date();
            time.value = zeroPadding(cd.getHours(), 2) + ':' + zeroPadding(cd.getMinutes(), 2) + ':' + zeroPadding(cd.getSeconds(), 2);
            date.value = zeroPadding(cd.getFullYear(), 4) + '-' + zeroPadding(cd.getMonth() + 1, 2) + '-' + zeroPadding(cd.getDate(), 2) + ' ' + week[cd.getDay()];
        };

        function zeroPadding(num, digit) {
            var zero = '';
            for (var i = 0; i < digit; i++) {
                zero += '0';
            }
            return (zero + num).slice(-digit);
        }

</script>

<style  scoped>
.container{
    margin-top: 30px;
    height: 120px;
    background-color: #fff;
}
#clock {
    width: 100%;
    height: 100px
    ;
    margin-top: 30px;
    font-family: "Share Tech Mono", monospace;
    color: #ffffff;
    text-align: center;
    position: absolute;
    color: #135cca;
    text-shadow: 0 0 20px #1a79b9, 0 0 20px rgba(10, 175, 230, 0);
}

#clock .time {
    letter-spacing: 0.05em;
    font-size: 40px;
    padding: 5px 0;
}

#clock .date {
    letter-spacing: 0.1em;
    font-size: 16px;
}

#clock .text {
    letter-spacing: 0.1em;
    font-size: 12px;
    padding: 20px 0 0;
}

</style>