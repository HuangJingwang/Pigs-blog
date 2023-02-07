<template>
  <!-- <Background></Background> -->
  <div class="container">

      <Tree :data="data"></Tree>
      <div class="articleTitle">
      </div>
  </div>
</template>

<script>
import { ref, reactive} from 'vue'
import { toRaw } from '@vue/reactivity'
import { useStore } from 'vuex'
import Tree from '../components/Tree.vue'

export default {

  name: 'Category',
  components:{
    Tree
  },
  setup() {
    const { state, dispatch } = useStore()


    // function showGroup(){
    //   console.log('aaaa',state.groupList);
    // }

    let data = toRaw(state.groupList)
    console.log('父亲的data',data);

    // 写一个addshow方法 把所有数据遍历一遍，都加上show属性
    const addShowFuc = (data) => {
      return data.map( (item) => {
          item.show = false;

          if (item.children){
            addShowFuc(item.children);
          }
          return item;
      })
    }

    data = addShowFuc(data)

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

    return { data  }
  },
}
</script>

<style scoped>
.container {
  width: 800px;
  height: 800px;
  background-color: #fff;
}
</style>
