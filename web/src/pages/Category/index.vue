<template>
  <Background :title="`Category`"></Background>
  <div class="container basic-box" @click="test">
    <div class="articleTitle">
      <Tree :data="data"></Tree>
    </div>
  </div>
  <Waifu/>
</template>

<script setup>
import { ref, reactive, computed } from "vue"
import { useArticleStore } from "@/store/article"
import { toRaw } from "@vue/reactivity"
import Tree from "./Tree"
const articleStore = useArticleStore()

const test = () => {
  console.log(groupList.value)
}
let data = computed(() => {
  return addShowFuc(articleStore.groupList)
})
// // 写一个addshow方法 把所有数据遍历一遍，都加上show属性

const addShowFuc = (data) => {
  return data.map((item) => {
    item.show = false
    if (item.children) {
      addShowFuc(item.children)
    }
    return item
  })
}
//#region
// 做一个映射 id:item
// foreach 成功了 reduce不知道为甚不行
// AllItem.reduce((memo,current)=>{
// },{})

// let Alldata = {}
// AllItem.forEach(element => {
//     Alldata[element["id"]] = element;
// });
// let Lastlist = []
// AllItem.forEach((item) =>{
//     let pid = item.pid
//     let parant = Alldata[pid]
//     if (pid == 0){
//       Lastlist.push(item)
//     } else if(parant){
//       parant.children? parant.push(item) : parant.children = [item]
//     }
// })
//#endregion
</script>

<style scoped>
.container {
  width: 1200px;
  min-height: 400px;
  background-color: #fff;

  margin: 0 auto;
  padding: 1px;
  transform: translateY(-50px);
}
.articleTitle {
  margin: 48px 80px;
}
</style>
