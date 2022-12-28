<template>
  <div class="background" :style="{ backgroundImage: `url(${imgUrl})` }"></div>
</template>

<script>
export default {
  setup() {
    let imgUrl = require('@/static/img/bgi1.jpg')
    return {
      imgUrl,
    }
  },
}
</script>

<style scoped>
.background {
  position: relative;
  width: 100%;
  height: 1010px;
  background-position: center center;
  background-size: cover;

  /* background-color: #eee; */
  /* background: url('@/static/img/bgi1.jpg'); */
}

.background::after {
  content: '';
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3);
  position: absolute;
  top: 0;
  left: 0;
}
</style>
