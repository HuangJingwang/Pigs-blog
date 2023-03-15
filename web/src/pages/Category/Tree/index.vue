<template>
  <div class="tree">
    <div class="group_item" v-for="(group, index) in data" :key="group.id">
      <div class="content">
        <div
          class="groupName"
          :style="{ margin: `0 ${30 * group.level}px` }"
          :data-id="group.children ? null : group.id"
          @click="handleClick(group)"
        >
          {{ group.group_name }}
        </div>
      </div>
      <!-- <div class="articleList"  :style="{ margin: `0 ${30 * group.level}px` }">123</div> -->
      <template v-if="group.children !== []">
        <Tree
          :style="{
            maxHeight: group.show ? `10000px` : '0',
            overflow: 'hidden',
          }"
          :data="group.children"
        />
      </template>
      <div
        :style="{
          maxHeight: group.show ? `10000px` : '0',
          overflow: 'hidden',
        }"
        v-if="!group.children"
      >
        <List :id="group.id" :level="group.level" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, ref, defineAsyncComponent } from 'vue'
import Tree from './index.vue'
const props = defineProps({ data: Array })
const List = defineAsyncComponent(() => import('../List'))
let showChildren = ref(false)

// 调整央视，获取文章
const handleClick = (group) => {
  group.show = !group.show
  showChildren.value = !showChildren.value
}
</script>

<style scoped>
.tree {
  width: 100%;
}

.groupName {
  cursor: pointer;
  height: 50px;
  line-height: 50px;
  font-size: 24px;
  font-weight: 900;
}
.content:hover {
  background-color: #eee;
}
</style>
