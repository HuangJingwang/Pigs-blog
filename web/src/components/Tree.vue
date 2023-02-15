<template>
  <!-- 查看一下传过来的data -->
  <div class="TreeBox">
    <!-- 点击打印data -->
    <ul @click="showData" :style="{ display: show ? 'block' : 'none' }">
      <!-- 遍历data目录 -->
      <!-- mlgb 这里的show不知道为甚改不了 -->
      <li
        v-for="item in props.data"
        :key="item"
        @click.stop="item.show = !item.show"
        @click="showData(item.show)"
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

function showData(show) {
  console.log('Tree', props.data)
  // console.log(show)
  console.log(props.show)
  // 下面的show undifined 不管是 item.show 还是 data.item.show都不行
  // console.log(this.show);
}
</script>

<style scoped>
.TreeBox {
  background-color: rgb(240, 204, 157);
}
</style>
