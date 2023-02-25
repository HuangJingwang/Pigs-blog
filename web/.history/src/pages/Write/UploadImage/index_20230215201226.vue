<template>

  
  <el-upload
    action="https://www.starrysummer.com:6533/pigs-blog/picture/upload"
    :headers="headers"
    :on-success="onSuccess"
    :on-error="onError"
    :before-upload="beforeUpload"
    :file-list="fileList"
    :limit="1"
    list-type="picture-card"
    :on-change="handelChange"
    :auto-upload="false">
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
            @click="handleRemove(file)">
            <el-icon><Delete /></el-icon>
          </span>
        </span>
      </div>
    </template>
  </el-upload>

  <el-dialog v-model="dialogVisible">
    <img w-full :src="dialogImageUrl" class="preview" alt="Preview Image" />
  </el-dialog>
</template>
<script setup>
import { ref } from 'vue'
import { Delete, Download, Plus, ZoomIn } from '@element-plus/icons-vue'
// import type { UploadFile } from 'element-plus'
const headers = {
  "Content-Type": "multipart/form-data",
}


// 初始文件列表
let fileList = ref([{}])
// 预览窗口图片
const dialogImageUrl = ref('')
// 是否可见预览图片
const dialogVisible = ref(false)
const disabled = ref(false)
// 删除已上传图片
const handleRemove =( file,files )=> {
  console.log(file)
  console.log(files)
}
// 预览图片
const handlePictureCardPreview = file => {
  dialogImageUrl.value = file.url
  dialogVisible.value = true
}
// 成功上传
const onSuccess=()=>{
  console.log('success')
}
// 上传失败
const onError=()=>{
 console.log('error') 
}
// 上传之前
const beforeUpload = ()=>{
  console.log('before')
}
// 改变状态回调 
const handelChange = file => {
  console.log(file)
}
</script>
<style scope>

.preview {
  width: 100%;
  background-size: contain;
}
</style>
