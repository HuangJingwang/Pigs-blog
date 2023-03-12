<template>
  <div class="container" @click="hideConfig">
    <div class="pubConfig">
      <input
        type="text"
        class="title"
        placeholder="请输入标题..."
        v-model="articleTitle"
      />
      <div class="right">
        <div class="saveInfo">{{ saveInfo }}</div>
        <button class="draft" @click="showArticleModel">我的文章</button>
        <button class="public" @click="showConfigBox" ref="pubBtn">发布</button>
        <div class="user">头像</div>
      </div>
    </div>
    <MdEditor
      :preview-theme="theme"
      v-model="articleText"
      @on-upload-img="onUploadImg"
      @on-change="autosave"
      @onSave="handleSave"
      class="editor"
    ></MdEditor>
    <!-- 配置发布文章相关属性 -->
    <div class="configBox" v-show="isShowConfig" ref="configBox">
      <div class="header">发布文章</div>
      <div class="addConfig">
        <!-- 添加分类 -->
        <div class="addCategories">
          <div class="left categoryDirectory">
            <span class="isMust">*</span>
            分 类：
          </div>
          <div class="right categoryOptions">
            <div class="group_name">
              <el-cascader
                size="default"
                v-model="group_id"
                :show-all-levels="false"
                :options="groupOptions"
                :props="selectorOptions"
                popper-class="cascader"
                @change="addGroupId"
              />
            </div>
          </div>
        </div>

        <!-- 添加标签 -->
        <div class="addTags">
          <div class="left">添加标签：</div>
          <!-- 点击添加文章标签 -->
          <div class="right tag_box">
            <div class="tagOptions" @click="addTags" @dblclick="removeTags">
              <div class="tagNode" v-for="tag in tagList" :key="tag.id">
                <button
                  :disabled="tagDisabled"
                  class="tag"
                  data-selected="unselected"
                  :data-tag="tag.tag_name"
                >
                  {{ tag.tag_name }}
                </button>
                <span
                  :data-tag="tag.tag_name"
                  class="deleteTag"
                  @click="deleteTag(tag.id)"
                  v-show="editorTag"
                >
                  x
                </span>
              </div>
              <!-- 新建标签 -->
            </div>
            <div class="editorTags">
              <input
                v-show="editorTag"
                class="createTag"
                @keydown.enter="createTag"
                type="text"
                v-model="newTag.tag_name"
                placeholder="createTag"
              />
              <span class="editor iconfont icon-yongyan" @click="editorTags"></span>
            </div>
          </div>
        </div>
        <!-- 添加摘要 -->
        <div class="absrtact">
          <div class="left">摘 要:</div>
          <div class="right addAbsrtact">
            <el-input
              v-model="articleIntroduction"
              :rows="3"
              type="textarea"
              placeholder="Please input"
              maxlength="100"
            />
            <span class="number">{{ articleIntroduction.length }}/100</span>
          </div>
        </div>
        <!-- 添加封面 -->
        <div class="addCoverImg">
          <div class="left coverImgDirectory">文章封面：</div>
          <div class="right coverImgOptions">
            <UploadImg @acceptUrl="acceptUrl"></UploadImg>
          </div>
        </div>
      </div>
      <div class="sub">
        <div class="save" @click="handleSaveArticle">发布</div>
        <div class="cancel" @click="cancelPub">取消</div>
      </div>
    </div>
  </div>
</template>

<script setup>
// 引入消息提示框组件
import { ElMessage } from "element-plus"
import { useArticleThemeStore } from "@/store/articleTheme" //主题仓库
import { useArticleStore } from "@/store/article" //文章仓库
import { useUserStore } from "@/store/user" //用户信息仓库
import { reactive, onMounted, ref, computed, watch } from "vue"
import axios from "axios"
import {
  createNewTag,
  deleteTags,
  saveArticle,
  updateArticle,
  deletePictures,
  getArticleDetail,
} from "@/api" //请求方法
import MdEditor from "md-editor-v3"
import "md-editor-v3/lib/style.css"
import UploadImg from "@/pages/Write/UploadImage"
import { ElMessageBox } from "element-plus"
import { useRoute } from "vue-router"

const route = useRoute()
const articleThemeStore = useArticleThemeStore()
const articleStore = useArticleStore()
const userStore = useUserStore()

// 1,获取account
// 2,获取query参数,判断是编辑/新建文章
// 获取初始文章id
let articleId = ref(null)
if (route.query.id) {
  console.log("从query获取id")

  articleId.value = route.query.id
} else if (!route.query.id && sessionStorage.getItem("articleId")) {
  articleId.value = sessionStorage.getItem("articleId")
  console.log("从sessionstorage获取id", articleId.value)
}
// let articleId = computed(() => {
//   return route.query.id
// })
// onMounted(() => {
//   console.log(articleId,'id')
// })
// 在write页面中不需要为account 设置响应式
let userAccount = userStore.userInfo.account
// 创建初始文章及数据
let coverImg = "" //封面路径
let saveInfo = ref("文章将保存至草稿箱") //说明信息
let articleTitle = ref("") //文章标题
let articleIntroduction = ref("") //文章摘要
let articleText = ref("") //文章内容
let article_picture_url = [] //文章内图片
// 选择group_id
let group_id = ref([])
let tags = [] // 标签数组

let editorArticleImg = ref("")
//从upLoadImg组件获取封面图片。
const acceptUrl = (url) => {
  coverImg = url
}
// 更新文章数据
let updateParams = {
  account: userAccount,
  article_text: "Unspecified",
  group_id: 1,
  introduction: "Unspecified",
  status: "draft",
  tags: "no tag",
  title: "Unspecified",
}
// 定义创建草稿文章默认数据
let createParams = {
  account: userAccount, //账户
  article_text: "Unspecified", //文章内容
  group_id: 1, //分类，默认在"未分类"中1
  img_url: "", //封面图片
  introduction: "introduction", //文章摘要
  status: "draft", //创建草稿
  tags: "no tag", //空标签
  title: "title", //标题
}
// 监视query(articleId),判断创建/编辑文章
watch(
  articleId,
  async () => {
    // 1,article 存在，请求对应文章数据，修改data
    if (articleId.value) {
      let result = await getArticleDetail(articleId.value)
      if (result.code === 200) {
        // 编辑文章，获取文章详情
        coverImg = result.data.img_url //封面
        article_picture_url = result.data.article_picture_url //文章内图片
        articleTitle.value = result.data.title //标题
        articleText.value = result.data.article_text //内容
        group_id.value[0] = result.data.group_id
        articleIntroduction.value = result.data.introduction//摘要
        tags = result.data.tags.split(",") //标签
        console.log(result.data, "editor data")
      } else {
        // console.log("创建失败")
        if (sessionStorage.getItem('articleId')) {
          console.log("文章未更新,正常操作")
        } else {
          console.log("创建失败")
        }
      }
      // 将id存入sessionStorage,方便继续编辑   ，发布完成时，删除此id/创建新文章时重设id
    } else if (articleId.value == undefined) {
      //2,article 不存在，则创建文章，并将此文章存入sessionStorage，以便下次进入write时继续编辑
      let result = await saveArticle(createParams)
      console.log(result)

      if (result.code == 200) {
        articleId.value = result.data.id
        // 将文章存入缓存空间
        sessionStorage.setItem("articleId", result.data.id)
      } else if (result.code == 10006) {
        ElMessageBox.confirm("草稿箱已满,请及时清理", "Warning", {
          confirmButtonText: "OK",
          cancelButtonText: "Cancel",
          type: "warning",
        })
          .then(() => {
            userStore.showArticleModal = true
          })
          .catch(() => {
            console.log("close")
          })
      }

      // 創建新文章成功
    }
  },
  { immediate: true }
)
//#region
// 控制文章模块显示与隐藏
let configBox = ref(null)
let pubBtn = ref(null)
let isShowConfig = ref(false) //默认隐藏
const showConfigBox = () => {
  isShowConfig.value = true
}
// 点击configBox外时隐藏
const hideConfig = (e) => {
  if (!configBox.value.contains(e.target) && e.target != pubBtn.value) {
    isShowConfig.value = false
  }
}
// 点击取消按钮隐藏盒子
const cancelPub = () => {
  isShowConfig.value = false
}
// #endregion

//#region

// 配置联级选择器
const selectorOptions = {
  expandTrigger: "click",
}
// 分类数据
const groupOptions = computed(() => {
  return articleStore.groupList
})
// 添加分类
const addGroupId = () => {
  updateParams.group_id = group_id.value[group_id.value.length - 1]
}

//#endregion

//#region
// 判断是否选中此tag
// 选择tags
let tagList = computed(() => {
  return articleStore.tagList
})
const addTags = (e) => {
  // 若未选中，则更改为选中状态
  if (e.target.dataset.selected == "unselected") {
    // 更改目标状态，将对应tag推入数组
    tags.push(e.target.dataset.tag)
    e.target.classList = ["selectedTag"]
    e.target.dataset.selected = "selected"
  }
}
// 移除已选中tag
const removeTags = (e) => {
  if (e.target.dataset.selected == "selected") {
    // 在tags中移除目标tag
    tags = tags.filter((item) => item != e.target.dataset.tag)
    e.target.classList = ["tag"]
    e.target.dataset.selected = "unselected"
  }
}

// 获取tag列表数据
onMounted(() => {
  // dispatch("reqTagList")
  articleStore.reqTagList()
})

// 编辑tag
// 新建tag
let tagDisabled = ref(false)
let newTag = reactive({
  tag_name: "",
  create_by: "test",
})
const createTag = async () => {
  if (newTag.tag_name) {
    let result = await createNewTag(newTag)
    newTag.tag_name = ""
    // dispatch("reqTagList")
    articleStore.reqTagList()
  }
}
// 删除tag
const deleteTag = async (id) => {
  // 若目标tag已被选中，则从tags中清除此tag
  tags = tags.filter((item) => item != event.currentTarget.dataset.tag)
  let result = await deleteTags(id)
  if (result.success) {
    // dispatch("reqTagList")
    articleStore.reqTagList()
  }
}
// 默认隐藏编辑tag输入框及delete
let editorTag = ref(false)
const editorTags = () => {
  editorTag.value = !editorTag.value
  tagDisabled.value = !tagDisabled.value
}

//#endregion

// 上传文章内图片
const onUploadImg = async (files) => {
  files.forEach(async (file) => {
    let form = new FormData()
    form.append("file", file)
    let result = await axios.post(
      "https://www.starrysummer.com:6533/pigs-blog/picture/upload",
      form,
      {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      }
    )
    if (result.data.code == 200) {
      let picUrl = result.data.data
      // 储存图片路径
      if (picUrl) article_picture_url.push(picUrl)
      articleText.value = `${articleText.value}\n![图片](${picUrl})`
    } else {
      console.log("error")
    }
  })
}
// 删除未使用的图片
async function delete_pictures(article_picture_url) {
  // 遍历图片路径 过滤未使用图片
  let deletePicturesUrl = article_picture_url.filter((url) => {
    return articleText.value.indexOf(url) == -1
  })
  article_picture_url = article_picture_url.filter((url) => {
    return articleText.value.indexOf(url) !== -1
  })
  // 整合已使用图片
  // 删除图片
  let picturesData = {
    article_picture_url: deletePicturesUrl,
  }
  let result = await deletePictures(picturesData)
}

// 保存至草稿箱
async function saveDraft() {
  saveInfo.value = "保存中..."
  updateParams.article_text = articleText.value ? articleText.value : "no data"
  updateParams.introduction = articleIntroduction.value
    ? articleIntroduction.value
    : "no data"
  updateParams.title = articleTitle.value ? articleTitle.value : "untitled"
  updateParams.tags = tags.join(",") ? tags.join(",") : "no tag"
  updateParams.article_picture_url = article_picture_url
  updateParams.img_url = coverImg
  let result = await updateArticle(updateParams, articleId.value)
console.log(result)
  if (result.success) {
    saveInfo.value = "文章已保存"
  }
  let picResult = await delete_pictures(article_picture_url)
}
// 更新草稿数据
let t = null
const updateDraft = () => {
  saveInfo.value = "文章将保存至草稿箱"
  if (t !== null) {
    clearTimeout(t)
  }
  t = setTimeout(async () => {
    saveDraft()
  }, 8000)
}
//快捷键保存
const handleSave = () => {
  t = setTimeout(() => {
    t = null
  }, 8000)
  saveDraft()
}
// 自动保存//延迟8s
const autosave = () => {
  updateDraft()
}
// 保存文章
const handleSaveArticle = async () => {
  sessionStorage.removeItem("articleId")

  // 更新数据
  updateParams.status = "published"
  updateParams.article_text = articleText.value
  updateParams.introduction = articleIntroduction.value
  updateParams.title = articleTitle.value
  updateParams.img_url = coverImg
  updateParams.tags = tags.join(",")
  updateParams.article_picture_url = article_picture_url
  if (
    updateParams.article_text &&
    updateParams.group_id &&
    updateParams.title &&
    updateParams.introduction
  ) {
    console.log(updateParams.group_id, "groupId")
    let result = await updateArticle(updateParams, articleId.value)
    console.log(result)
    if (result.success) {
      // sessionStorage.removeItem('articleId')
      ElMessageBox.confirm("文章已发布,您将离开此页面", "发布成功", {
        confirmButtonText: "确定",
        type: "success",
        center: true,
        showClose: false,
        lockScroll: false,
        showCancelButton: false,
      }).then(() => {
        // dispatch('reqArticleData')
        window.close()
      })
      // 初始化页面数据
      updateParams.article_text = ""
      updateParams.group_id = ""
      updateParams.title = ""
      updateParams.tags = ""
      updateParams.introduction = ""
      // 删除未使用到的图片
    } else {
      ElMessage({
        message: "error",
        type: "error",
      })
    }
  } else if (!updateParams.article_text) {
    ElMessage({
      message: "请输入内容.",
      type: "warning",
    })
  } else if (!updateParams.title) {
    ElMessage({
      message: "请输入标题.",
      type: "warning",
    })
  } else if (!updateParams.group_id) {
    ElMessage({
      message: "请添加分类.",
      type: "warning",
    })
  } else if (!updateParams.introduction) {
    ElMessage({
      message: "请添输入摘要.",
      type: "warning",
    })
  }
}

// 主题
let theme = computed(() => {
  return articleThemeStore.preview_themeList[articleThemeStore.preview_themeIndex]
})

// 显示文章管理模块
const showArticleModel = () => {
  userStore.showArticleModal = true
}
</script>

<style scoped>
.container {
  /* position: relative; */
  position: fixed;
  top: 0;
  bottom: 0;
  min-height: 100%;
  height: auto;
  width: 100%;
  background-color: #eee;
  display: flex;
  flex-direction: column;
}
/* 头部信息及标题输入 */
.container .pubConfig {
  padding: 12px 50px;
  height: 80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.container .title {
  outline: none;
  background: #eee;
  height: 100%;
  /* flex: 50%; */
  width:600px;
  border: 0px solid #000;
  font-weight: 900;
  font-size: 32px;
}

.container .right {
  display: flex;
  align-items: center;
}
/* 用户头像 */
.container .user {
  width: 45px;
  height: 45px;
  line-height: 45px;
  text-align: center;
  border-radius: 50%;
  background-color: #fff;
  margin-left: 20px;
}
/* 发布按钮 */
.container .public {
  padding: 0 20px;
  height: 42px;
  color: #fff;
  font-size: 16px;
  background: rgb(70, 70, 171);
  border: 1px solid rgb(70, 70, 171);
  border-radius: 5px;
  margin-left: 20px;
}
/* 草稿箱 */
.container .draft {
  padding: 0 20px;
  height: 42px;
  color: #fff;
  font-size: 16px;
  background: rgb(70, 70, 171);
  border: 1px solid rgb(70, 70, 171);
  border-radius: 5px;
  margin-left: 20px;
}
.container .editor {
  flex: 1;
}
/* 发布文章设置 */

.configBox {
  /* display: none; */
  z-index: 1000;
  width: 550px;
  position: absolute;
  top: 90px;
  right: 50px;
  background-color: #fff;
  border: 1px solid rgb(221, 221, 221);
  border-radius: 3px;
}
.configBox::before {
  content: "";
  /* width: 25px;
  height: 25px; */
  border-bottom: 16px solid rgb(221, 221, 221);
  border-right: 14px solid transparent;
  border-left: 14px solid transparent;
  border-top: 14px solid transparent;
  position: absolute;
  top: -29px;
  right: 85px;
}
.configBox::after {
  content: "";
  border-bottom: 14px solid #fff;
  border-right: 12px solid transparent;
  border-left: 12px solid transparent;
  border-top: 12px solid transparent;
  position: absolute;
  top: -25px;
  right: 86px;
}
.configBox .header {
  padding: 18px;
  font-size: 24px;
  border-bottom: 1px solid rgb(221, 221, 221);
}
.addConfig {
  padding: 30px;
}
.addConfig .addCategories,
.addTags,
.addCoverImg {
  display: flex;
  margin-bottom: 20px;
}
.absrtact {
  display: flex;
  margin-bottom: 20px;
  margin-top: 20px;
}
.absrtact .left {
  padding-right: 10px;
}

.addConfig .left {
  line-height: 30px;
  text-align: right;
  width: 90px;
  height: 30px;
}
.addConfig .right {
  padding-left: 15px;
  width: 400px;
}

.addTags .tag_box {
  display: flex;
  flex-direction: column;
  /* justify-content: start; */
  /* background-color: pink; */
}
.addTags {
  position: relative;
}
.addTags .editor {
  position: absolute;
  right: -30px;
  width: 30px;
  height: 30px;
  line-height: 30px;
}
.tagOptions {
  display: flex;
  flex-wrap: wrap;
  width: 100%;
  height: 100%;
  /* height: 110px;
  overflow: scroll; */
}

.tagOptions .tag,
.tagOptions .selectedTag {
  font-size: 14px;
  height: 25px;
  padding: 0 15px;
  border: 0px solid #000;
  border-radius: 5px;
  margin-bottom: 15px;
  margin-right: 10px;
  color: #fff;
}
.tagOptions .tag {
  background-color: rgb(144, 147, 153);
}
.tagOptions .selectedTag {
  background-color: rgb(21, 152, 188);
}
.tagOptions .tag:hover {
  background-color: rgb(44, 68, 115);
}
.tagOptions .tag:active {
  background-color: rgb(166, 169, 173);
}

.tagOptions .tagNode {
  position: relative;
}

.editorTags .createTag {
  outline: rgb(64, 158, 255);
  padding: 0 10px;
  color: rgb(96, 98, 102);
  font-size: 12px;
  width: 215px;
  height: 30px;
  border-radius: 3px;
  border: 1px solid rgb(220, 223, 230);
}
.editorTags .createTag::placeholder {
  color: rgb(176, 171, 185);
}
.tagNode .deleteTag {
  background-color: red;
  border-radius: 50%;
  width: 16px;
  height: 16px;
  position: absolute;
  line-height: 16px;
  text-align: center;
  top: -5px;
  right: 4px;
}
/* 底部栏 */
.configBox .sub {
  border-top: 1px solid rgb(221, 221, 221);
  /* padding: 25px; */
  width: 100%;
  height: 60px;
  padding: 15px 20px;
}
.sub .cancel,
.save {
  color: #fff;
  border-radius: 5px;
  text-align: center;
  margin-left: 40px;
  width: 50px;
  height: 30px;
  line-height: 30px;
  background-color: rgb(70, 70, 171);
  float: right;
  font-size: 12px;
}
.isMust {
  color: rgb(224, 8, 8);
}
.addAbsrtact {
  position: relative;
}
.number {
  font-size: 8px;
  color: rgb(179, 179, 179);
  position: absolute;
  right: 0;
  bottom: -15px;
}

/* 更改样式 */

.md-editor >>> p {
  font-size: 16px;
}
.md-editor >>> h1 a {
  font-size: 32px;
}
.md-editor >>> h2 a {
  font-size: 24px;
}

.md-editor >>> h3 a {
  font-size: 18.72px;
}
.md-editor >>> h4 a {
  font-size: 16px;
}
.md-editor >>> h5 a {
  font-size: 13.28px;
}
.md-editor >>> h6 a {
  font-size: 12px;
}

.md-editor:deep(h1 > a),
.md-editor:deep(h2 > a),
.md-editor:deep(h3 > a),
.md-editor:deep(h4 > a),
.md-editor:deep(h5 > a),
.md-editor:deep(h6 > a) {
  pointer-events: none;
}
.md-editor:deep(p > strong) {
  font-weight: 900;
}
.md-editor:deep(em) {
  font-style: italic;
}

.group_name{
  position: relative;
}


</style>
<style>
.cascader{
  inset: 230px 280px auto auto !important;
}
</style>