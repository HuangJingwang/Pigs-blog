<template>
  <div class="cover" :style="{ backgroundImage: `url(${articleData.img_url})` }" @click="test">
    <div class="articleInfo">
      <div class="title">
        {{ articleData.title }}
      </div>
      <div class="breads">
        <div class="author">
          <span class="iconfont icon-gaojian-zuozhe"></span>
          <p class="text">作者: {{ articleData.nick_name }}</p>
        </div>
        <span
          :style="{
            height: '50px',
            lineHeight: '50px',
            fontSize: '24px',
          }"
          >|</span
        >
        <div class="date">
          <span class="iconfont icon-shijian1"></span>
          <span>更新于: {{ articleData.update_at }}</span>
        </div>
        <span :style="{ height: '50px', lineHeight: '50px', fontSize: '24px' }">|</span>
        <div class="category">
          <span class="iconfont icon-icon-folder"></span>
          <span> 文章分类: {{ articleData.group_name }}</span>
        </div>
      </div>
      <div class="footer">
        <div class="view">
          <span class="iconfont icon-liulan"></span>
          <span> 浏览量:{{ articleData.page_view }}</span>
        </div>
        <el-button v-show="showEditorBtn" type="primary" :icon="Edit" size="small" @click="editorArticle(articleData.id)">
      编辑
    </el-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref ,computed} from 'vue';
import { Edit } from '@element-plus/icons-vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/store/user';
const userStore = useUserStore()
const router = useRouter()
const props = defineProps(["articleData"])


// 登录用户与文章一致，显示编辑
const showEditorBtn = computed(()=>{
  return props.articleData.account === userStore.userInfo.account
})

// ref(props.articleData.account === userStore.userInfo.account)


// 编辑文章
const editorArticle = (id) => {
  const writePath = router.resolve({
    path: "/write",
    query: {
      id:id
    }
  })
  window.open(writePath.href, "_blank")
}
</script>

<style scoped>
.cover {
  width: 100%;
  height: 400px;
  position: relative;
  background-size: cover;
  background-position: center center;
}
.cover::before {
  content: "";
  position: absolute;
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;
  background-color: rgba(0, 0, 0, 0.5);
}
.cover::after{
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 150px;
/* background-color: rgba(20, 80, 110, 0.5); */
background: linear-gradient( rgba(20, 80, 110, 0), rgba(20, 80, 110, .6));
pointer-events: none;
}
.cover .articleInfo {
  width: 750px;
  height: 120px;
  position: absolute;
  color: #fff;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -20%);
}
.articleInfo .title {
  width: 100%;
  font-size: 32px;
  color: #fff;
  text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.5);
  line-height: 40px;
  text-align: center;
  align-items: center;
}
.articleInfo .breads {
  width: 100%;
  height: 50px;
  display: flex;
  align-items: center;
  justify-content: center;
}
.breads .iconfont {
  font-size: 16px;
  margin-right: 8px;
}
.breads .author,
.breads .date,
.breads .category {
  display: flex;
  align-items: center;
  font-size: 14px;
  margin: 15px;
}

.footer {
  display: flex;
  height: 20px;
  font-size: 14px;
  align-items: center;
  justify-content: center;
}
.footer .iconfont {
  font-size: 16px;
  margin-right: 8px;
}
.footer .view,.footer .editor{
  display: flex;
  align-items: center ;
  margin: 15px;
}
.footer .editor{
  border-bottom: 1px solid rgb(36, 156, 231);
}
</style>
