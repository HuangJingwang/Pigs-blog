<template>
  <!--  -->
  <Background />
  <div class="container">
    <div class="preview">
      <md-editor
        class="md-editor basic-box"
        v-model="articleData.articles_text"
        :editor-id="md_editor_config.id"
        :theme="md_editor_config.theme"
        :scrollElementOffsetTop="md_editor_config.distance"
        preview-only
      />
    </div>
    <div class="aside">
      <div class="theme basic-box"></div>

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
import { reactive, ref, onMounted, onUpdated } from 'vue'
import { useRoute } from 'vue-router'
import { getArticleDetail } from '@/api'
import MdEditor from 'md-editor-v3'
const MdCatalog = MdEditor.MdCatalog
const scrollElement = document.documentElement
const route = useRoute()
const id = route.query.id
let articleData = reactive({
  articles_text: '', //内容
  title: '', //标题
  tags: '', //签
  create_at: '', //创建时间
  img_url: '', //封面
  introduction: '', //简介
  page_view: '', //浏览量
  update_at: '', //最近更新
})
// 获取文章的具体数据
onMounted(async () => {
  let result = await getArticleDetail(id)
  articleData.articles_text = result.data.articles_text
  Object.keys(articleData).forEach((key) => {
    articleData[key] =
      result.data[key] == undefined ? articleData[key] : result.data[key]
  })
})
let md_editor_config = reactive({
  id: 'my-editor',
  distance: 250,
  offsetTop: 200,
  theme: 'dark',
})
// 文章数据
// const articleData = reactive({
//   theme: 'dark',
//   text: '# title1000\n ## title2\n## title2\n### title3\n## title2\n## title2\n\n\n\nn\n1\n1\n1\n1\n1\n1\nn\n\n\n ### title3\n #### title4 \nfontsize = 16px\n ##### title5 \n###### title6\n# title1\n ## title2\n ### title3\n #### title4 \nfontsize = 16px\n ##### title5 \n###### title6',
//   id: 'my-editor',
//   distance: 250,
//   offsetTop: 200,
// })

//#region

// let timer = null

// let catalogContainer = null
// let activeTitle = null

// 递归 调整目录样式
// function setElementHeight(container, activeElement, height) {
//   console.log(height)
//   if (activeElement == container) return height
//   console.log(activeElement.style)
//   // activeElement /link
//   // 获取wrapper项
//   let wrapper = activeElement.childNodes[1]
//   //  判断wrapper 中是否有元素 / 是否为最后一级标题
//   let nodeArr = Array.from(wrapper.childNodes)
//   if (nodeArr.length !== 1) {
//     nodeArr = nodeArr.filter((item) => {
//       // console.log(wrapper)
//       return item.nodeName !== '#text'
//     })
//   } else {
//     console.log('active' + '是最后一级标题')
//     nodeArr = []
//   }
//   height = height + 22 * nodeArr.length


// // activeElement.style.height = 100+'px'


//   // 为activeElement 的父级盒子添加高度
// // console.log(activeElement.parentNode.style.height = height+'px')

//   // console.log(height)
//   let element = activeElement.parentNode.parentNode
//  return setElementHeight(container, element, height)
// }
// 控制元素显示与隐藏
// window.addEventListener('scroll', () => {
//   // 防抖，减少dom操作
//   if (timer !== null) {
//     clearTimeout(timer)
//   }
//   timer = setTimeout(() => {
//     // 获取catalog组件最外层盒子 //根节点
//     catalogContainer = document.querySelector('.catalog')
//     activeTitle = catalogContainer.querySelector('.md-editor-catalog-active')

//     let height = 0
//   console.log(  setElementHeight(catalogContainer, activeTitle,height))

//     // 获取activeTitle子元素
//     // console.log(activeTitle.childNodes[1])
//     // console.log(activeTitle.parentNode)

//     // console.log(catalogContainer)
//     // 将元素子节点伪数组转化为真数组
//     // -- catalog-link
//     // const  arr = Array.from(catalogContainer.childNodes)
//     // console.log(arr)
//     // arr.forEach(node=> {
//     //   if (node == activeTitle) {

//     //   }
//     // })
//     // console.log(arr[1].childNodes[1])
//     //第一层标题
//   }, 500)
// })

//#endregion

// 目录浮动效果
let catalog = ref(null)

onMounted(() => {
  window.addEventListener('scroll', () => {
    if (catalog.value && window.scrollY > catalog.value.offsetTop - 30) {
      catalog.value.style.top = 90 + 'px'
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
/* .preview::after{
  content: '';
  display: block;
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
} */
.preview .md-editor {
  min-height: 600px;
  /* padding: 15px; */
  padding: 30px;
}

.aside {
  width: 270px;
}

.aside .theme {
  width: 100%;
  height: 100px;
  margin-bottom: 30px;
}
.aside .catalog {
  width: 100%;
  min-height: 100px;
  position: sticky;
  /* padding: 15px; */
  /* background-color: hotpink; */
  padding: 15px 0;
}

/* 样式穿透 */

/* 调整字体大小 */

/* .preview >>> .md-editor-catalog-link .title{
  background-color: pink;
} */
.catalog .md-editor-catalog >>> .md-editor-catalog-link {
  margin-left: 15px;
}
.catalog .md-editor-catalog >>> .md-editor-catalog-link span {
  /* background-color: #000;
   */
  height: 22px;
  font-size: 16px;
  line-height: 22px;
}
/* .catalog >>> .md-editor-catalog > .md-editor-catalog-wrapper {
  height: 22px;
  overflow: hidden;
} */

.catalog .md-editor-catalog >>> .md-editor-catalog-active > span {
  /* background-color: aqua; */
  color: rgb(16, 116, 83);
}

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
