<template>
  <div class="articles" @click="toDetail">
    <div
      v-if="articleList.length !== 0"
      class="list"
      v-for="(article, index) in articleList"
      :key="article.id"
      
    >
      <div class="title" :style="{ margin: `0 ${30 * level}px` }" :data-id="article.id">
        {{ article.title }}
      </div>
    </div>
  </div>
  <div class="blank" v-if="articleList.length == 0">
    <div class="title" :style="{ margin: `0 ${30 * (level + 1)}px` }">
      noData
    </div>
  </div>
</template>
<script setup>
import { onMounted, ref } from 'vue'
import { getGroupArticles } from '@/api'
import { useRouter } from 'vue-router';
const router = useRouter()
const props = defineProps({ id: Number, level: Number })
let articleList = ref([])
onMounted(async () => {
  let result = await getGroupArticles(props.id)
  if (result.code !== 200) return
  else {
    if (result.data.length !== 0) {
      articleList.value = result.data
    }
  }
})
const toDetail = (e) => {
  let id = e.target.dataset.id
  if (id) {
    // state.user.key = '1234'
    router.push({
      path: '/article',
      query: {
        id: id,
      },
    })
  }
}
</script>

<style scoped>
.title {
  font-size: 16px;
  height: 34px;
  line-height: 34px;
  color: #555;
  cursor: pointer;
}
</style>
