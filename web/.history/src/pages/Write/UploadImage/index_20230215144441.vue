<template>
  <el-upload
    action="#"
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

const dialogImageUrl = ref('')
const dialogVisible = ref(false)
const disabled = ref(false)

const handleRemove = file => {
  console.log(file)
}

const handlePictureCardPreview = file => {
  dialogImageUrl.value = file.url
  dialogVisible.value = true
}

const handleDownload = file => {
  console.log(file)
}
// const handelSuccess = (response, uploadFile, uploadFiles) => {
//   console.log(124)
// }
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
