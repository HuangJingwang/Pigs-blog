<template>
  <!-- 查看一下传过来的data -->
  <div class="TreeBox">
    <!-- 点击打印data -->
    <ul :style="{ display: show ? 'block' : 'none' }">
      <!-- 遍历data目录 -->
      <!-- mlgb 这里的show不知道为甚改不了 -->
      <li
        v-for="item in categoryData"
        :key="item"
        @click.stop="item.show = !item.show ; arrowChange(item)"
      
      >
        <p>{{ item.group_name }} <span class="icon-arror iconfont"></span></p>
        
        <!-- 使用递归组件 -->
        <template v-if="item.children">
          <Tree :data="item.children" :show="item.show"></Tree>
        </template>
      </li>
    </ul>
  </div>
</template>

<script setup>
import {reactive} from 'vue'
import Tree from './index.vue'

const props = defineProps({
  data: {
    type: Array,
    default: () => [],
  },  
  show: {
    type: Boolean,
    default: () => true,
  },
})
let categoryData = reactive(props.data)

// function showData(item,show) {
//   console.log('show',item)
//   console.log(show)
//   console.log('这data',props.data,categoryData);
// }

function arrowChange(item){
    console.log(item.show);
    if (item.show) {
    }else{
      return 
    }
}

</script>

<style scoped>
.TreeBox {
}
li{
    max-height: 1000px;
    min-height: 40px;
    font-size: 1.5rem;
    font-weight: bold;
    transition: all 2s linear 0s;
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

}
</style>
