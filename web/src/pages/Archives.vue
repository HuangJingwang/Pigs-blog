<template>
  <div>
    <Background></Background>
    <div class="container basic-box">
      <!--  -->
      <div
        class="year_month"
        v-for="(item, index) in archivesList"
        :key="index">
        <div class="title">{{ item.year_month }}</div>
        <div class="article" v-for="(article, index) in item.data" :key="index">
          <span class="articleTitle">{{ article.title }}</span>
          <span class="date">{{ article.month }}-{{ article.date }}</span>
        </div>
      </div>

      <div class="pagenation">分页器</div>
    </div>
  </div>
</template>

<script>
import { ref, reactive, onMounted, toRefs } from 'vue'
import { useStore } from 'vuex'
import Background from '@/components/Background.vue'
export default {
  components: { Background },
  setup() {
    // 页面中展示归档数据
    const { state, dispatch } = useStore()
    dispatch('handleArchiveData')
    let archivesList = state.archivesList

    return { archivesList }
  },
}
</script>

<style scoped>
.container {
  padding: 50px;
  position: relative;
  top: -50px;
  width: 1200px;
  margin: auto;
}
.year_month .title {
  font-size: 32px;
  font-weight: 900;
  margin: 15px;
}
.year_month .article {
  font-size: 22px;
  margin-left: 35px;
  display: flex;
  height: 60px;
  line-height: 60px;
  justify-content: space-between;
}
</style>
