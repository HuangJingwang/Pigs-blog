<template>
  <Background :title="title"/>
  <div class="container basic-box" >
    <Tree :data="groupList"/>
  </div>
</template>

<script setup>
import {ref,computed} from 'vue'
import { useArticleStore } from '@/store/article';
import Tree from './Tree'
const title = 'Category'
const articleStore = useArticleStore()
// 获取分类列表数据
// let groupList = computed(()=>{
//   return articleStore.groupList
// })

const arrayTreeAddLevel = (array, levelName = 'level', childrenName = 'children') => {
    if (!Array.isArray(array)) return []
    const recursive = (array, level = 0) => {
        level++
        return array.map(v => {
            v[levelName] = level
            const child = v[childrenName]
            if (child && child.length) recursive(child, level)
            return v
        })
    }
    return recursive(array)
}
let groupList = computed(()=>{
  return arrayTreeAddLevel(articleStore.groupList)
})
// 确保正确获取有文章的group

</script>

<style scoped>
.container{
  overflow:hidden ;
  width: 1200px;
  margin: 0 auto ;
  position: relative;
  top: -50px;
  padding: 50px 20px;
}
</style>