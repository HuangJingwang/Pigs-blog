<template>
  <div>
    <h1>editor</h1>
  </div>
</template>

<script>
import { reactive, toRefs } from 'vue'

export default {
  name:'Editor',
  setup() {
    const state = reactive({})

    return {
      ...toRefs(state),
    }
  },
}
</script>

<style lang="scss" scoped></style>
