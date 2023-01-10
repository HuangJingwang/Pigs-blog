<template>
  <div class="container">
    <div class="pubConfig">
      <input
        type="text"
        class="title"
        placeholder="请输入标题..."
        v-model="data.title" />
      <!-- <div class="route">{{ $route.meta.showComponent }}</div> -->
      <div class="right">
        <div class="saveInfo">文章将保存至草稿箱</div>
        <button class="draft">草稿箱</button>
        <button class="public" @click="config">发布</button>
        <div class="user">头像</div>
      </div>
    </div>

    <MdEditor v-model="data.articles_text" class="editor"></MdEditor>
    <!-- <MdCatalog></MdCatalog> -->
    <div class="configBox">
      <div class="header">发布文章</div>
      <div class="addConfig">
        <!-- 添加分类 -->
        <div class="addCategories">
          <div class="left categoryDirectory">
            <span class="isMust">*</span> 分类：
          </div>
          <div class="right categoryOptions">
            <div class="group_name">
              <el-cascader
                size="default"
                v-model="group_id"
                :options="groupOptions"
                :props="props"
                @change="addGroupId" />
              <!-- <button class="addNewGroup" @click="addGroupId">+</button> -->
            </div>
          </div>
        </div>
        <!-- 添加标签 -->
        <div class="addTags">
          <div class="left tagDirectory">添加标签：</div>
          <!-- 点击添加文章标签 -->
          <div class="right tagOptions" @click="addTags">
            <!-- <el-button type="info">Info</el-button>
               -->
            <div class="tagNode" v-for="(tag, index) in tagList" :key="index">
              <button class="tag" :data-tag="tag.tag_name">
                {{ tag.tag_name }}
              </button>
              <span class="deleteTag" @click.stop="deleteTag(tag.id)"> x </span>
            </div>
            <!-- 新建标签 -->

            <input
              class="createTag"
              @keydown.enter="createTag"
              type="text"
              v-model="newTag.tag_name" />
          </div>
        </div>

        <!-- 添加封面 -->
        <div class="addCoverImg">
          <div class="left coverImgDirectory">文章封面：</div>
          <div class="right coverImgOptions">
            <UploadImg></UploadImg>
          </div>
        </div>
      </div>
      <div class="sub">
        <div class="save" @click="handleSaveArticle">保存</div>
        <div class="cancel">取消</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { useStore } from 'vuex'
import { reactive, onMounted, ref, watch, computed } from 'vue'
import { createNewTag, deleteTags, saveArticle } from '@/api'
import MdEditor from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
import UploadImg from '@/components/UploadImg.vue'
const MdCatalog = MdEditor.MdCatalog
const scrollElement = document.documentElement
const { state, dispatch } = useStore()

// 文章数据
let data = reactive({
  articles_text: '',
  author: 'test',
  group_id: 0,
  status: 'published',
  tags: '',
  title: '',
})
// 选择group_id
const group_id = ref([])
// 配置联级选择器
const props = {
  expandTrigger: 'hover',
}
const groupOptions = computed(() => {
  return state.groupList
})

const addGroupId = () => {
  data.group_id = group_id.value[group_id.value.length - 1]
}
// 获取获取分类数据
onMounted(() => {
  dispatch('reqGroupList')
})

// 选择tags
let tagList = computed(() => {
  return state.tagList
})
let tags = []
const addTags = e => {
  tags.push(e.target.dataset.tag)
  data.tags = tags.join(',')
}
onMounted(() => {
  dispatch('reqTagList')
})

// 新建tag
let newTag = reactive({
  tag_name: '',
  create_by: 'test',
})
const createTag = async () => {
  if (newTag.tag_name) {
    let result = await createNewTag(newTag)
    newTag.tag_name = ''
    console.log(tags)
    dispatch('reqTagList')
  }
}

// 删除tag
const deleteTag = async id => {
  let result = await deleteTags(id)
  console.log(result)
  if (result.success) {
    dispatch('reqTagList')
  }
}
// 保存文章
const handleSaveArticle = async () => {
  console.log(data)
  if (data.articles_text) {
    let result = await saveArticle(data)
    console.log(result)
  }
}
</script>

<style scoped>
.container {
  width: 100%;
  min-height: 100%;
  display: flex;
  flex-direction: column;
}

.container .pubConfig {
  padding: 12px 50px;
  height: 80px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.container .title {
  background: #eee;
  height: 100%;
  width: 1000px;
  border: 0px solid #000;
  font-weight: 900;
  font-size: 32px;
}

.container .right {
  display: flex;
  align-items: center;
}
.container .user {
  width: 45px;
  height: 45px;
  line-height: 45px;
  text-align: center;
  border-radius: 50%;
  background-color: pink;
  margin-left: 20px;
}
.container .public {
  padding: 0 20px;
  height: 42px;
  color: #fff;
  font-size: 16px;
  background: rgb(38, 38, 188);
  border: 1px solid rgb(38, 38, 188);
  border-radius: 5px;
  margin-left: 20px;
}
.container .draft {
  padding: 0 20px;
  height: 42px;
  color: #fff;
  font-size: 16px;
  background: rgb(38, 38, 188);
  border: 1px solid rgb(38, 38, 188);
  border-radius: 5px;
  margin-left: 20px;
}
.container .editor {
  flex: 1;
}
/* 发布文章设置 */
.configBox {
  /* z-index: 1; */
  width: 550px;
  position: absolute;
  top: 95px;
  right: 80px;
  background-color: #fff;
  border: 1px solid rgb(221, 221, 221);
  border-radius: 2px;
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
.addConfig .left {
  line-height: 30px;
  text-align: right;
  width: 90px;
  height: 30px;
  background-color: pink;
}
.addConfig .right {
  padding-left: 20px;
  width: 400px;
}

.tagOptions {
  display: flex;
  flex-wrap: wrap;
}

.tagOptions .tag {
  font-size: 14px;
  height: 25px;
  padding: 0 15px;
  border: 0px solid #000;
  border-radius: 5px;
  margin-bottom: 15px;
  margin-right: 10px;
  color: #fff;
  background-color: rgb(144, 147, 153);
}

.tagOptions .tag:hover {
  background-color: rgb(107, 109, 113);
}
.tagOptions .tag:active {
  background-color: rgb(166, 169, 173);
}
.tagOptions .tagNode {
  position: relative;
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
  background-color: blue;
  float: right;
}
.isMust {
  color: rgb(224, 8, 8);
}
</style>
