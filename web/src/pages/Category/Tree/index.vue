<template>
  <!-- 查看一下传过来的data -->
  <div class="TreeBox">
    <!-- 点击打印data -->
    <ul :style="{ maxHeight: show ? '1000px' : '0'}">
      <!-- 遍历data目录 -->
      <!-- mlgb 这里的show不知道为甚改不了 -->
      <li
        v-for="item in data"
        :key="item"
        @click.stop="item.show = !item.show ;" 
      >
        <p>
        {{ item.group_name }} <span class="icon-arror iconfont" :class=" item.show ? 'trunDown':'trunLeft'"></span>
        </p>
        
        <!-- 使用递归组件 -->
        <template v-if="item.children">
          <Tree :data="item.children" :show="item.show" :style="{ 'paddingLeft' : `${listPadding*15}px`}" ></Tree>
        </template>

      </li>
    </ul>
  </div>
</template>

<script setup>
import {ref,reactive,onMounted,toRaw} from 'vue'
import Tree from './index.vue'
import {getGroupArticles} from '@/api'

const props = defineProps({
  data: {
    type: Array,
    default: () => [],
  },  
  show: {
    type: Boolean,
    default: () => true,
  },
  articleList:{
    type:Array,
    default:() => []
  },
  listPadding:{
    type:Number,
    default:() => 1
  },
  // k控制箭头
  // arrowFace:{
  //   type:Boolean,
  //   default:() => false
  // }
})

// 列表padding数值
const listPadding = ref(1)
const arrowFace = ref(false)
onMounted(() => {
  // console.log(props.data ,'propschuancan','data id',props.data.id)
  // let {id} = props.data
  // console.log(id);

  let data = JSON.parse(JSON.stringify(props.data))
  console.log('props传过来json处理的data',data);
  data.forEach(async(item) =>{
    if(!item.children){
    let articleList = await getGroupArticles(item.id)
    console.log('itemid',item.id,'articleList.data',articleList);
  }
  })
})

</script>

<style scoped>
.TreeBox {
}
li{
    max-height: 1000px;
    min-height: 40px;
    font-size: 1.5rem;
    font-weight: bold;

}
p:hover{
    background-color: rgb(138, 157, 159);
}
p{
  padding: 12px 20px;
  display: flex;
  align-items: center;
}
.iconfont{
  display: block;
  line-height: 40px;
  margin-left :10px;

  translate: all 2s linear;
}
ul{
  overflow: hidden;
  transition: all 0.5s ease-in;
}

 /* 箭头转动 */
 .trunDown{
   transform: rotate(90deg);
 }

</style>
