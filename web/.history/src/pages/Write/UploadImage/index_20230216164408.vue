<template>
  <!--  -->
  <button class="uploadPic" :style="{ backgroundImage: `url(${picture_url})` }">
    <!-- 1.添加图片按钮 -->
    <input
      class="upload"
      id="upload"
      type="file"
      @input="handleUpload"
      :disabled="isDisabled"
      accept="image/jpeg,image/png,image/jpg"
    />
    <!-- 无预览图时，显示此上传组件 -->
    <label class="upload_proxy" for="upload" :style="{ display: `${showUploadBtn}` }">
      <span class="addPic iconfont icon-tupiantianjia"></span>
    </label>

    <!-- 2.预览缩略图 -->
    <div class="icons" :style="{ display: `${showIcons}` }">
      <span class="previewPic iconfont icon-chakan1" @click="previewPic"></span>
      <span class="deletePic iconfont icon-shanchu1" @click="deletePic"></span>
      <label for="upload" class="replacePic iconfont icon-yunshangchuan"></label>
    </div>
  </button>
  <!-- <el-upload
    action
    :http-request="handUpload"
    :on-success="onSuccess"
    :on-error="onError"
    :before-upload="beforeUpload"
    :file-list="fileList"
    :limit="2"
    list-type="picture-card"
    :on-change="handelChange"
    :show-file-list="true"
    :auto-upload="true">
    <el-icon><Plus /></el-icon>

    <template #file="{ file }">
      <div>
        <img class="el-upload-list__item-thumbnail" :src="file.url" alt="" />
        <span class="el-upload-list__item-actions">
          <span
            class="el-upload-list__item-preview"
            @click="handlePictureCardPreview(file)">
            <el-icon><zoom-in /></el-icon>
          </span>
          <span
            v-if="!disabled"
            class="el-upload-list__item-delete"
            @click="handleRemove(file,files)">
            <el-icon><Delete /></el-icon>
          </span>
        </span>
      </div>
    </template>
  </el-upload> -->

  <el-dialog v-model="dialogVisible">
    <img w-full :src="picture_url" class="preview" alt="Preview Image" />
  </el-dialog>
</template>
<script setup>















import { ref, watch } from "vue"
import axios from "axios"
import { deletePictures } from "@/api"
// import { collapseItemProps } from "element-plus"
// 上传是否可用  若是已上传图片，则上传按钮不可用
let isDisabled = ref(false)
// 图片路径
let picture_url = ref("")

// 上传封面文件事件
// 处于上传状态时，上传案件不可用
const handleUpload = async (e) => {
  // 若预览文件已有值，则先删除已有预览文件
  isDisabled.value = true
  if (picture_url.value !== "") {
    let picturesData = {
      pictures_url: [picture_url.value],
    }
    let result = await deletePictures(picturesData)
    console.log(result)
    // if (result.code == 200) picture_url.value = ""
  }
  // 获取文件
  let file = e.target.files[0]
  console.log(file)
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
  
  console.log(result.data.data)
  //上传成功，预览小图
  if (result.data.code == 200) {
    picture_url.value = result.data.data
    isDisabled = false
  } else {
    isDisabled = false
    
  }
}

// 是否可见预览图片
let dialogVisible = ref(false)
// 显示预览窗口
const previewPic = () => {
  dialogVisible.value = true
}
// 点击删除图片
const deletePic = async () => {
  let picturesData = {
    pictures_url: [picture_url.value],
  }
  let result = await deletePictures(picturesData)
  console.log(result)
  if (result.code == 200) picture_url.value = ""
}

// 控制上传按钮是否隐藏
let showUploadBtn = ref("flex")
let showIcons = ref("none")
// 监测是否有预览图片
watch(picture_url, () => {
  showUploadBtn = picture_url.value == "" ? "flex" : "none"
  showIcons = picture_url.value == "" ? "none" : "block"
})

// 定义压缩图片函数
function compressFile(file) {
  
}





//#region
// let fileList = ref([{url:'http://moon.starrysummer.com/858b93274cbb4e558ac4ebd78e44aeb8.jpg'}])
// const handUpload = async (params)=>{
//   // console.log(file.file)
//   let {file } = params
//   let form = new FormData()
//     form.append("file", file)
//     let result = await axios.post(
//       "https://www.starrysummer.com:6533/pigs-blog/picture/upload",
//       form,
//       {
//         headers: {
//           "Content-Type": "multipart/form-data",
//         },
//       }
//     )
//     console.log(result)
//     console.log(result.data.code)
// }

// // 初始文件列表
// // 预览窗口图片
// const dialogImageUrl = ref('')
// // 是否可见预览图片
// const dialogVisible = ref(false)
// const disabled = ref(false)
// // 删除已上传图片
// const handleRemove =( file,files )=> {

//   console.log(fileList)
// }
// // 预览图片
// const handlePictureCardPreview = file => {
//   dialogImageUrl.value = file.url
//   dialogVisible.value = true
// }
// // 成功上传
// const onSuccess=()=>{
//   console.log('success')
// }
// // 上传失败
// const onError=()=>{
//  console.log('error')
// }
// // 上传之前
// const beforeUpload = ()=>{
//   console.log('before')
// }
// // 改变状态回调
// const handelChange = file => {
// }

//#endregion
</script>
<style scope>
.preview {
  width: 100%;
  background-size: contain;
}
.uploadPic {
  width: 150px;
  height: 150px;
  background-color: rgb(240, 240, 240);
  border: 1px dashed rgb(159, 159, 159);
  border-radius: 5px;
  background-size: contain;
  background-repeat: no-repeat;
  background-position: center center;
}

.uploadPic:hover {
  background-color: rgb(225, 225, 225);
  border: 1px dashed rgb(0, 161, 179);
}

.uploadPic .upload {
  display: none;
}
.uploadPic .upload_proxy {
  width: 100%;
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.upload_proxy .addPic {
  font-size: 24px;
  color: rgb(126, 126, 126);
}

.icons {
  width: 150px;
  height: 150px;
  line-height: 150px;
}
.uploadPic .previewPic,
.uploadPic .deletePic,
.uploadPic .replacePic {
  font-size: 12px;
  color: rgb(223, 223, 223);
  margin: 10px;
  transition: all 0.2s;
}
.uploadPic .previewPic {
  opacity: 0;
  position: relative;
  left: 35px;
}
.uploadPic .deletePic {
  opacity: 0;
}
.uploadPic .replacePic {
  opacity: 0;
  position: relative;
  right: 35px;
}

.uploadPic:hover .previewPic {
  left: 0;
  opacity: 1;
  font-size: 20px;
}
.uploadPic:hover .deletePic {
  font-size: 20px;
  opacity: 1;
}
.uploadPic:hover .replacePic {
  right: 0;
  font-size: 20px;
  opacity: 1;
}
.uploadPic:hover .icons {
  background-color: rgba(0, 0, 0, 0.2);
}
</style>
