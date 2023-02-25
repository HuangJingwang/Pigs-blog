<template>
  <!--  -->
  <button class="uploadPic">
    <!-- 1.添加图片按钮 -->
    <input
      class="upload"
      id="upload"
      type="file"
      @change="handleUpload"
      :disabled="isDisabled"
      accept="image/jpeg,image/png,image/jpg"
    />
    <label class="upload_proxy" for="upload">
      <span class="addPic iconfont icon-tupiantianjia"></span>
    </label>
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
    <img w-full :src="dialogImageUrl" class="preview" alt="Preview Image" />
  </el-dialog>
</template>
<script setup>
import { ref } from "vue"
import axios from "axios"
// 上传是否可用  若是已上传图片，则上传按钮不可用
let isDisabled = ref(false)
// 图片路径
let picture_url = ref("")
// 预览窗口图片
let dialogImageUrl = ref("")
// 是否可见预览图片
let dialogVisible = ref(false)

const handleUpload = (e) => {
  // 获取文件
  let file = e.target.files[0]
  console.log(file)
}

// 文件列表--初始封面

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
  width: 120px;
  height: 120px;
  background-color: rgb(240, 240, 240);
  border: 1px dashed rgb(159, 159, 159);
  border-radius: 5px;
}

.uploadPic:hover {
  background-color: rgb(225, 225, 225);
  border: 1px dashed rgb(0, 161, 179);
}

.uploadPic .upload {
  display: none;
}
.uploadPic .upload_proxy {
  display: block;
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.upload_proxy .addPic {
  font-size: 24px;
  color: rgb(126, 126, 126);
}
</style>
