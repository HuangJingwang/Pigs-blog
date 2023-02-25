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
import { ref, watch,defineEmits } from "vue"
import axios from "axios"
import { deletePictures } from "@/api"
// 将数据传输至父组件
const emits = defineEmits(['sendUrl'])
// 定义传输数据事件

// 上传是否可用  若是已上传图片，则上传按钮不可用
let isDisabled = ref(false)
// 图片路径
let picture_url = ref("")

// 上传封面文件事件
// 处于上传状态时，上传案件不可用
const handleUpload = async (e) => {
  // 获取文件
  let picture = e.target.files[0]
  console.log(picture)
  let quality = 0.8

  // 若预览文件已有值，则先删除已有预览文件
  // isDisabled.value = true
  if (picture_url.value !== "") {
    let picturesData = {
      pictures_url: [picture_url.value],
    }
    let result = await deletePictures(picturesData)
    console.log('delete',result)
    if (result.code == 200) picture_url.value = ""

console.log(picturesData)

  }
  compressFile(picture, quality, picture_url,callback)
console.log(picture_url.value)
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

  console.log('watchChange', picture_url.value)

  // 当picture 有值，则向父组件传值
})

function callback(url_ref,url) {
        url_ref.value = url
        console.log(url)
}

// 定义压缩上传图片函数
function compressFile(file, quality, url_ref,callback) {
  let newFile = null
  let fileName = file.name
  // let fileType = file.type
  const reader = new FileReader()
  reader.readAsDataURL(file)
  let imgBase64 = ""
  reader.onload = () => {
    // console.log(reader.result)
    //获取base64格式数据
    imgBase64 = reader.result
    // 创建虚拟元素
    let canvas = document.createElement("canvas")
    let img = document.createElement("img")
    // 将图片写入img标签
    img.src = imgBase64
    img.onload = async () => {
      canvas.width = img.width
      canvas.height = img.height
      let ctx = canvas.getContext("2d")
      ctx.drawImage(img, 0, 0, canvas.width, canvas.height)
      // 获取压缩后base64格式数据
      let imgBase64_compressed = canvas.toDataURL("image/jpeg", quality)
      // 将base 64 转换为file
      let arr = imgBase64_compressed.split(",")
      let byteString = atob(arr[1])
      const options = {
        type: "image/jpeg",
      }
      const u8Arr = new Uint8Array(byteString.length)
      for (let i = 0; i < byteString.length; i++) {
        u8Arr[i] = byteString.charCodeAt(i)
      }
      // 创建file 实例
      newFile = new File([u8Arr.buffer], fileName, options)
      console.log(newFile)
      // 上传压缩后图片
      let form = new FormData()
      form.append("file", newFile)
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
        // 上传请求成功
        let url = result.data.data
        callback(url_ref,url)
      }
    }
  }
}
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
