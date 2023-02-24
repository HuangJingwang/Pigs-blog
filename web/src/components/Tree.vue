<template>
  <!-- 查看一下传过来的data -->
  <div class="TreeBox">
    <!-- 点击打印data -->
    <ul :style="{ display: show ? 'block' : 'none' }">
      <!-- 遍历data目录 -->
      <!-- mlgb 这里的show不知道为甚改不了 -->
      <li
        v-for="item in props.data"
        :key="item"
        @click.stop="item.show = !item.show"
        @click="showData(item,item.show) ; showChange(item,item.show)" 
      >
        <p>{{ item.group_name }}</p>
        <!-- 使用递归组件 -->
        <template v-if="item.children">
          <Tree :data="item.children" :show="item.show"></Tree>
        </template>
      </li>
    </ul>
  </div>
</template>

<script setup>
import Tree from './Tree.vue'

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

function showData(item,show) {
  console.log('showData',item)
  console.log(show)
}
function showChange(item,show){
  console.log('showChange',item);
  console.log(show)

}
</script>

<style scoped>
.TreeBox {
}

li{
    height: 40px;
    font-size: 1.5rem;
    font-weight: bold;
}

p{
  padding: 12px 20px;

}
</style>
