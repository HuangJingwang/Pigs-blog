<template>
  <Background />
  <div class="container">
    <div class="leftBox">
      <div class="preview">
        <md-editor
          class="md-editor basic-box"
          v-model="articleData.articles_text"
          :editor-id="md_editor_config.id"
          :theme="md_editor_config.theme"
          :scrollElementOffsetTop="md_editor_config.distance"
          :preview-theme="theme"
          preview-only
        />
      </div>
      <div class="adjacent basic-box">
        <Adjacent :id="id"></Adjacent>
      </div>
    </div>
    <div class="aside">
      <span class="theme basic-box" @click="changeTheme">
        <span class="arr_left iconfont icon-zuojiantou" data-method="reduce"></span>
        <div class="title">{{ theme }}</div>
        <span class="arr_right iconfont icon-youjiantou" data-method="plus"></span>
      </span>

      <div class="catalog basic-box" ref="catalog">
        <md-catalog
          class="md-catalog"
          :editor-id="md_editor_config.id"
          :scroll-element="scrollElement"
          :offsetTop="md_editor_config.distance"
          :scrollElementOffsetTop="md_editor_config.offsetTop"
        />
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, onMounted, computed, watch } from "vue"
import { useRoute } from "vue-router"
import { useStore } from "vuex"
import { getArticleDetail } from "@/api"
import MdEditor from "md-editor-v3"
import "md-editor-v3/lib/style.css"
import Adjacent from "./Adjacent"
const MdCatalog = MdEditor.MdCatalog
const scrollElement = document.documentElement
const route = useRoute()
const { state } = useStore()
// 切换文章预览主题
let theme = computed(() => {
  return state.preview_themeList[state.preview_themeIndex]
})

// 更改预览主题
const changeTheme = (e) => {
  console.log(e.target.dataset.method)
  if (e.target.dataset.method == "plus") {
    state.preview_themeIndex++
    console.log(state.preview_themeIndex)
    state.preview_themeIndex =
      state.preview_themeIndex == 5 ? 0 : state.preview_themeIndex

  } else if (e.target.dataset.method == "reduce") {
    state.preview_themeIndex--
    console.log(state.preview_themeIndex)
    state.preview_themeIndex =
      state.preview_themeIndex == 0 ? 5 : state.preview_themeIndex
  }
}

// let github = ref('github')
let articleData = reactive({
  articles_text: ``, //内容
  title: "", //标题
  tags: "", //签
  create_at: "", //创建时间
  img_url: "", //封面
  introduction: "", //简介
  page_view: "", //浏览量
  update_at: "", //最近更新
})
let id = ref(route.query.id)
watch(id, async () => {
  console.log(id.value)
  let result = await getArticleDetail(id)
  // articleData.articles_text = result.data.articles_text
  console.log(result)
  Object.keys(articleData).forEach((key) => {
    articleData[key] = result.data[key] == undefined ? articleData[key] : result.data[key]
  })
})

// 获取文章的具体数据
onMounted(async () => {
  let result = await getArticleDetail(id.value)
  // articleData.articles_text = result.data.articles_text
  Object.keys(articleData).forEach((key) => {
    articleData[key] = result.data[key] == undefined ? articleData[key] : result.data[key]
  })
})
let md_editor_config = reactive({
  id: "my-editor",
  distance: 250,
  offsetTop: 200,
  theme: "light",
})

let activeElement = ref(null)

window.addEventListener("scroll", () => {
  activeElement.value = document.querySelector(".md-editor-catalog-active")
})
watch(activeElement, () => {
  let container = document.querySelector(".catalog")
  setStyle(activeElement.value, container, 0)
})
function setStyle(element, container, height) {
  if (element == container) return

  // 获取父级及兄弟级
  let parent = element.parentNode //--wrapper
  // 处理兄弟级组件数组,移除虚拟元素
  let bros = Array.from(parent.childNodes).filter((node) => node.nodeName !== "#text") //--link
  // 先清除兄弟级别的样式 //排他思想
  bros.forEach((node) => {
    // 被排除的 wrapper 调整高度为0
    let childrenBox = node.childNodes[1]
    childrenBox.style.height = 0
  })

  // element 的容器的高度 = 子元素（link）高度之和
  // 获取link中第二个元素 --wrapper
  let childrenBox = element.childNodes[1]

  let childrenList = Array.from(childrenBox.childNodes).filter(
    (node) => node.nodeType == 1
  )
  // 计算height
  height = 38.24 * childrenList.length + height
  childrenBox.style.height = height + "px"
  // 由element 向上级递归，直到最后一层
  let wrapper = parent.parentNode //--link
  setStyle(wrapper, container, height)
}
// 目录浮动效果
let catalog = ref(null)

onMounted(() => {
  window.addEventListener("scroll", () => {
    if (catalog.value && window.scrollY > catalog.value.offsetTop - 30) {
      catalog.value.style.top = 90 + "px"
    }
  })
})
</script>

<style scoped>
.container {
  display: flex;
  justify-content: space-between;
  margin: auto;
  width: 1200px;
  /* height: 3000px; */
  margin-top: 30px;
  /* height: 100%; */
}
.preview {
  width: 900px;
  position: relative;
}
/*覆盖a标签使其失效 */

.preview .md-editor {
  min-height: 600px;
  /* padding: 15px; */
  padding: 30px;
}

.adjacent {
  margin-top: 30px;
  width: 100%;
  height: 180px;
  overflow: hidden;
  display: flex;
}
.aside {
  width: 270px;
}
/* 侧边盒子修改主题 */

.aside .theme {
  width: 100%;
  height: 100px;
  margin-bottom: 30px;
  padding: 0 15px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.aside .theme .title {
  font-size: 24px;
  font-weight: 900;
  font-family: Cambria, Cochin, Georgia, Times, "Times New Roman", serif;
}
.aside .theme .iconfont {
  font-size: 42px;
  color: rgb(4, 128, 210);
}

.aside .theme .iconfont:hover {
  color: rgb(0, 86, 167);
}
.aside .theme .iconfont:active {
  color: rgb(25, 130, 215);
}
.aside .catalog {
  width: 100%;
  min-height: 100px;
  position: sticky;
  /* padding: 15px; */
  /* background-color: hotpink; */
  padding: 15px 15px;
}

/* 样式穿透 */

/* 调整字体大小 */

/* .preview >>> .md-editor-catalog-link .title{
  background-color: pink;
} */
.catalog .md-editor-catalog >>> .md-editor-catalog-link {
  margin-left: 10px;
  /* height: 24px; */
}
.catalog >>> .md-editor-catalog .md-editor-catalog-link span {
  /* background-color: #000;
   */
  height: 24px;
  font-size: 16px;
  line-height: 24px;
}
.catalog >>> .md-editor-catalog .md-editor-catalog-wrapper {
  height: 0;
  overflow: hidden;
}

.catalog .md-editor-catalog >>> .md-editor-catalog-active > span {
  /* background-color: aqua; */
  color: rgb(121, 190, 216);
  transition: all 0.3s;
}
/* 修改标题字体大小 */
.preview >>> p {
  font-size: 16px;
}
.preview >>> h1 a {
  font-size: 32px;
}
.preview >>> h2 a {
  font-size: 24px;
}

.preview >>> h3 a {
  font-size: 18.72px;
}
.preview >>> h4 a {
  font-size: 16px;
}
.preview >>> h5 a {
  font-size: 13.28px;
}
.preview >>> h6 a {
  font-size: 12px;
}
.preview >>> a {
  pointer-events: none;
}
</style>
