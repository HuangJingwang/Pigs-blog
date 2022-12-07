<template>
  <div class="background">
    <div class="background-image"></div>
    <Header />
    <Footer />
  </div>
</template>

<script>
import Header from './components/Header.vue'
import Footer from './components/Footer.vue'
export default {
  name: 'App',
  components: {
    Header,
    Footer,
  },
}
</script>

<style>
/* 清除基础html样式 */

* {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
*:before,
*:after {
  -webkit-box-sizing: border-box;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
}
html,
body {
  margin: 0;
  height: 100%;
}

/* 修改鼠标样式为默认样式 */
span,
div {
  cursor: default;
}
#app {
  height: 100%;
}
body,
div,
dl,
dt,
dd,
ul,
ol,
li,
h1,
h2,
h3,
h4,
h5,
h6,
pre,
code,
form,
fieldset,
legend,
input,
button,
textarea,
p,
blockquote,
th,
td {
  margin: 0;
  padding: 0;
}
body {
  background: #fff;
  color: #555;
  font-size: 14px;
  font-family: 'Arial', 'Microsoft YaHei', '黑体', '宋体', sans-serif;
}
td,
th,
caption {
  font-size: 14px;
}
h1,
h2,
h3,
h4,
h5,
h6 {
  font-weight: normal;
  font-size: 100%;
}
address,
caption,
cite,
code,
dfn,
em,
strong,
th,
var {
  font-style: normal;
  font-weight: normal;
}
a {
  color: #555;
  text-decoration: none;
}
a:hover {
  text-decoration: underline;
}
img {
  border: none;
  vertical-align: middle;
}
ol,
ul,
li {
  list-style: none;
}
input,
textarea,
select,
button {
  font: 14px 'Arial', 'Microsoft YaHei', '黑体', '宋体', sans-serif;
}
table {
  border-collapse: collapse;
}
html {
  overflow-y: scroll;
}

/* 清除浮动 */
.clearfix:before,
.clearfix:after {
  content: ' ';
  display: inline-block;
  height: 0;
  clear: both;
  visibility: hidden;
}
.clearfix {
  *zoom: 1;
}

Header {
  position: fixed;
  top: 0;
}
/* 全局页面背景 */
.background {
  position: relative;
  background-color: #f3f6f7;
  height: 100%;
}

/* 背景图片样式 */
.background-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-image: url(@/assets/bgi1.jpg);
  background-position: center center;
  background-size: cover;
  position: relative;
}

/* 为背景添加半透明黑色遮罩 */
.background-image::after {
  content: '';
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.3);
  position: absolute;
  top: 0;
  left: 0;
}
</style>
