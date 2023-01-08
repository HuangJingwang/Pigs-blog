<template>
  <div class="container">
    <div class="pubConfig">
      <input
        type="text"
        class="title"
        placeholder="请输入标题..."
        v-model="state.title" />
      <!-- <div class="route">{{ $route.meta.showComponent }}</div> -->
      <div class="right">
        <div class="saveInfo">文章将保存至草稿箱</div>
        <button class="draft">草稿箱</button>
        <button class="public" @click="config">发布</button>
        <div class="user">头像</div>
      </div>
    </div>

    <MdEditor v-model="state.text" class="editor"></MdEditor>
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
                v-model="group_name"
                :options="groupOptions"
                :props="props"
                @change="addGroupName" />
                <button class="addNewGroup">+</button>
            </div>
          </div>
        </div>
        <!-- 添加标签 -->
        <div class="addTags">
          <div class="left tagDirectory">添加标签：</div>
          <div class="right tagOptions" @click="addTags">
            <!-- <el-button type="info">Info</el-button>
               -->
            <button
              class="tag"
              v-for="(tag, index) in tags"
              :key="index"
              :data-tag="tag">
              {{ tag }}
            </button>
          </div>
        </div>
        <!-- 添加封面 -->
        <div class="addCoverImg">
          <div class="left coverImgDirectory">文章封面：</div>
          <div class="right coverImgOptions">
            <UploadImg :msg="msg"></UploadImg>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, onMounted, ref } from 'vue'
import MdEditor from 'md-editor-v3'
import 'md-editor-v3/lib/style.css'
import UploadImg from '@/components/UploadImg.vue'
const MdCatalog = MdEditor.MdCatalog
const scrollElement = document.documentElement

let msg= ref('')
msg = '123'
const state = reactive({
  title: '',
  text: '# 标题\n## 123\n ### 123\n# 234',
  time: 123,
})

// 选择group_name
const group_name = ref([])
const props = {
  expandTrigger: 'hover',
}
const groupOptions = [
  {
    value: '前端',
    label: '前端',
    children: [
      {
        value: '123',
        label: 'Css',
      },
      {
        value: 'html',
        label: 'Html',
      },
      {
        value: 'javascript',
        label: 'Javascript',
      },
    ],
  },
]
const addGroupName = value => {
  console.log(value)
}

// 添加自定义分类

// 选择tags
const tags = [
  '日常',
  'bug',
  '随笔',
  '资源库',
  '学习笔记',
  'git',
  '原理',
  '转载',
  '原创',
]
const addTags = e => {
  let tag = e.target.dataset.tag
}
onMounted(() => {})
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
.addCategories .isMust {
  color: rgb(224, 8, 8);
}
</style>
