<template>
  <div class="footer">
    <div class="webRecord">
      <span><img src="@/assets/icons/computer.png" alt="" /></span
      ><a href="https://beian.miit.gov.cn/" target="_blank" class="ipc">鄂ICP备2023001057号</a>
    </div>

    <div style="width: 300px; margin: 0 auto; padding: 20px 0; text-align: center">
      <a
        target="_blank"
        href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=42011202002176"
        style="
          display: inline-block;
          text-decoration: none;
          height: 20px;
          line-height: 20px;
        "
        ><img src="@/assets/icons/record.png" style="float: left" />
        <p
          style="
            float: left;
            height: 20px;
            line-height: 20px;
            margin: 0px 0px 0px 5px;
            color: #939393;
          "
        class="plc">
          鄂公网安备 42011202002176号
        </p></a
      >
    </div>
  </div>
</template>

<script>
export default {}
</script>

<style scoped>
.footer {
  bottom: 0;
  width: 100%;
  /* height: 200px; */
  padding: 50px 0;
}
.footer .webRecord {
  width: 300px;
  margin: 0 auto;
  text-align: center;
  /* margin-top: 50px; */
}
.footer .webRecord span {
  margin-right: 5px;
}
.footer span img {
  width: 16px;
  height: 16px;
}
.footer a {
  text-decoration: none;
  color: #939393;
}
.ipc ,.plc{
  font-size: 12px;
}
</style>
