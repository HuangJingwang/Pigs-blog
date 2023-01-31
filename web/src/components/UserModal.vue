<template>
  <div>
    <el-dialog
      class="dialog"
      v-model="state.showUserModal"
      :lock-scroll="false"
      :append-to-body="true"
      :show-close="false"
      width="650px"
      align-center
    >
      <template #header="{ titleId }">
        <div class="my-header" :id="titleId">
          <span class="register-title">
            <span class="coke"></span>
            <span>Register</span>
            <span class="hamburg"></span>
          </span>
          <span class="login-title">
            <span class="milkTea"></span>
            <span>Login</span>
            <span class="frenchFries"></span>
          </span>
        </div>
      </template>

      <div class="container">
        <!-- 注册 -->
        <div class="register-container"  >
          <!-- 昵称 -->
          <div class="nickname-panel">
            <div class="nickname-placeholder" :class="active0">Nickname</div>
            <input
              type="text"
              v-model="registerData.nick_name"
              class="account-input"
              placeholder="Nickname"
              data-class="active0"
              data-placeholder="Nickname"
              @focus="changePlaceholder_active"
              @blur="changePlaceholder($event, registerData.nick_name)"
            />
          </div>

          <!-- 账户 -->
          <div class="account-panel">
            <div class="account-placeholder" :class="active1">Account</div>
            <input
              type="text"
              v-model="registerData.account"
              class="account-input"
              placeholder="Account"
              data-class="active1"
              data-placeholder="Account"
              @focus="changePlaceholder_active"
              @blur="changePlaceholder($event, registerData.account)"
            />
          </div>
          <!-- 输入密码 -->
          <div class="password-panel">
            <div class="password-placeholder" :class="active2">Password</div>
            <input
              :type="status0"
              maxlength="16"
              v-model="registerData.password"
              class="password-input"
              placeholder="Password"
              data-class="active2"
              data-placeholder="Password"
              @focus="changePlaceholder_active"
              @blur="changePlaceholder($event, registerData.password)"
            />
            <span
              class="seePsw iconfont icon-yincangbukejian"
              @click="seePsw"
              data-type="status0"
            ></span>
          </div>

          <!-- confirm -->
          <div class="confirm-panel">
            <div class="confirmPassword-placeholder" :class="active3">
              ConfirmPassword
            </div>
            <input
              :type="status1"
              maxlength="16"
              v-model="registerData.confirmPassword"
              class="confirmPsw-input"
              placeholder="ConfirmPassword"
              data-class="active3"
              data-placeholder="ConfirmPassword"
              @focus="changePlaceholder_active"
              @blur="changePlaceholder($event, registerData.confirmPassword)"
            />
            <span
              class="seePsw iconfont icon-yincangbukejian"
              @click="seePsw"
              data-type="status1"
            ></span>
          </div>
          <el-button
            type="primary"
            width="200px"
            class="btn-register"
            @click="registerAccount"
            ref="buttonRef"
          >
            Register
          </el-button>
          <div class="link-panel" @click="toLogin">
            <el-link class="hadAccount">
              <div class="hadAccount">已有帐号?立即登录</div>
            </el-link>
          </div>
        </div>
        <!-- 登录 -->
        <div class="login-container" >
          <!-- 账户 -->
          <div class="account-panel">
            <div class="account-placeholder" :class="active4">Account</div>
            <input
              type="text"
              v-model="loginData.account"
              class="account-input"
              placeholder="Account"
              data-class="active4"
              data-placeholder="Account"
              @focus="changePlaceholder_active"
              @blur="changePlaceholder($event, loginData.account)"
            />
          </div>
          <!-- 密码 -->
          <div class="password-panel">
            <div class="password-placeholder" :class="active5">Password</div>
            <input
              :type="status2"
              v-model="loginData.password"
              maxlength="16"
              class="password-input"
              placeholder="Password"
              data-class="active5"
              data-placeholder="Password"
              @focus="changePlaceholder_active"
              @blur="changePlaceholder($event, loginData.password)"
            />
            <span
              class="seePsw iconfont icon-yincangbukejian"
              @click="seePsw"
              data-type="status2"
            ></span>
          </div>
          <el-button
            type="primary"
            width="200px"
            class="btn-login"
            @click="userLogin"
          >
            Login
          </el-button>
          <div class="link-panel">
            <el-link><div class="retrieve">忘记密码?</div></el-link>
            <el-link class="noAccount">
              <div class="noAccount" @click="toRegister">立即注册</div>
            </el-link>
          </div>
          <div class="thirdLogin">
            <el-divider><div class="link">第三方登录</div></el-divider>
            <a href="http://49.233.45.84:6533/github/login" class="github">
              <span class="githubLogin iconfont icon-GitHub"></span>
            </a>
          </div>
        </div>
      </div>
      <div class="mask" :class="maskStyle"></div>
    </el-dialog>
  </div>
</template>
<script setup>
import { ElMessageBox ,ElMessage} from 'element-plus'
import { login, register } from '@/api'
import { ref, reactive, computed } from 'vue'
import { useStore } from 'vuex'
const { state, dispatch } = useStore()


// let registerVisibility = ref('hidden')
// let loginVisibility = ref('visible')
// 绑定注册相关数据
let registerData = reactive({
  nick_name: '',
  account: '',
  password: '',
  confirmPassword: '',
})

// 注册账号
const registerAccount = async () => {
  const data = {
    account: registerData.account,
    nick_name: registerData.nick_name,
    password: registerData.password,
  }

  if (data.account && data.nick_name && data.password&&registerData.confirmPassword == data.password) {
  //   if () {
      let result = await register(data)
      console.log(result)
      maskStyle.value = 'mask-login'

       
  // registerOpacity = 0
  //   } else {
  //     ElMessageBox.alert('两次输入密码不一致', 'ERROR', {
  //       confirmButtonText: '确定',
  //       lockScroll: false,
  //     })
  //   }
  } else if (registerData.confirmPassword !== data.password) {
         ElMessageBox.alert('两次输入密码不一致', 'ERROR', {
        confirmButtonText: '确定',
        lockScroll: false,
      })
    
  } else {
    ElMessageBox.alert('请补注册全信息', 'ERROR', {
      confirmButtonText: '确定',
      lockScroll: false,
    })
  }
}

// 绑定登录数据
let loginData = reactive({
  account: '',
  password: '',
})

// 登录
const userLogin = async () => {
  if (loginData.account && loginData.password) {
    let result = await dispatch('reqLogin', loginData)
    console.log(result)
    if (!result.success) {
      ElMessageBox.alert('账号或密码错误', 'ERROR', {
      confirmButtonText: '确定',
      lockScroll: false,
      })
    
    } else {
      ElMessage({
      message: '登录成功',
      type: 'success',
    })
      // 关闭登录窗口
      state.showUserModal = false
    }
  } else {
    ElMessageBox.alert('请填写账号和密码', 'ERROR', {
      confirmButtonText: '确定',
      lockScroll: false,
    })
  }
}

// 输入框聚焦/失焦更改样式
let active0 = ref('')
let active1 = ref('')
let active2 = ref('')
let active3 = ref('')
let active4 = ref('')
let active5 = ref('')

// 聚焦事件
const changePlaceholder_active = (e) => {
  e.target.placeholder = ''
  let active = e.target.dataset.class
  eval(active).value = 'active'
}
// 失焦事件
const changePlaceholder = (e, value) => {
  if (!value) {
    let active = e.target.dataset.class
    eval(active).value = ''
    let placeholder = e.target.dataset.placeholder
    e.target.placeholder = placeholder
  }
}

// 显示/隐藏密码
let status0 = ref('password')
let status1 = ref('password')
let status2 = ref('password')

const seePsw = (e) => {
  let status = e.target.dataset.type
  e.target.classList.toggle('icon-yincangbukejian')
  e.target.classList.toggle('icon-xianshikejian')
  if (eval(status).value == 'password') {
    eval(status).value = 'text'
  } else {
    eval(status).value = 'password'
  }
}

// 切换注册/登录页面


let maskStyle = ref('mask-login')

// to注册页面
const toRegister = () => {
  maskStyle.value = 'mask-register'
  // 隐藏登录面板,显示注册面板
  // showRegister.value = true
  // setTimeout(() => {
  //   showLogin.value = false
  // }, 500)
}
// to登陆页面
const toLogin = () => {
  maskStyle.value = 'mask-login'
  // 隐藏注册面板,显示登录面板
  // showLogin.value = true
  // setTimeout(() => {
  //   showRegister.value = false
  // }, 500)
}
</script>
<style scoped>
.container {
  display: flex;
  width: 100%;
  height: 300px;
  padding-bottom: 15px;
}
.register-container {
  margin-right: 20px;
}

.register-container,
.login-container {
  width: 300px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  text-align: center;
  padding: 0 20px;
}
/* 头部 */
.my-header {
  display: flex;
}
.register-title {
  /* padding: 0 50px; */
  margin-left: 16px;
}
.login-title {
  margin-left: 40px;
}
.register-title,
.login-title {
  display: block;
  width: 50%;
  text-align: center;
  font-size: 24px;
  font-weight: 900;
  font-family: Arial, Helvetica, sans-serif;
}
/* 标题图标 */

.coke,
.hamburg,
.milkTea,
.frenchFries {
  display: inline-block;
  height: 24px;
  width: 24px;
  /* background-color: pink; */
  background-size: contain;
}
.coke {
  background-image: url(@/assets/icons/kele.png);
}
.hamburg {
  background-image: url(@/assets/icons/hanbao.png);
}
.milkTea {
  background-image: url(@/assets/icons/naicha.png);
}
.frenchFries {
  background-image: url(@/assets/icons/shutiao.png);
}
.password-panel {
  position: relative;
}

.seePsw {
  position: absolute;
  display: block;
  width: 24px;
  height: 24px;
  /* top: 18px; */
  top: 0;
  right: 0;
}

.login-container input,
.register-container input {
  z-index: 100;
  width: 100%;
  border: none;
  outline: none;
  letter-spacing: 1px;
  caret-color: rgb(96, 96, 96);
  padding-left: 8px;
  font-size: 14px;
  height: 24px;
  border-bottom: 2px solid rgb(135, 206, 235);
}
.login-container input::placeholder,
.register-container input::placeholder {
  font-size: 14px;
  height: 24px;
  line-height: 24px;
  letter-spacing: 0.05em;
  padding-left: 7px;
}
.login-container .link-panel {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.link-panel .retrieve,
.link-panel .noAccount,
.link-panel .hadAccount {
  cursor: pointer;
}

.nickname-panel,
.confirm-panel,
.account-panel,
.password-panel {
  position: relative;
}

.nickname-placeholder,
.account-placeholder,
.password-placeholder,
.confirmPassword-placeholder {
  position: absolute;
  /* text-align: left; */
  /* font-size: 14px; */
  /* height: 24px; */
  /* line-height: 24px; */
  /* letter-spacing: .05em; */
  /* padding-left: 15px; */
  display: none;
  /* visibility: hidden; */
  /* transition: visibility 0s; */
}
.active {
  display: block;
  padding-left: 10px;
  top: -20px;
  left: -5px;
  font-size: 10px;
  /* visibility: visible; */
  line-height: 24px;
  color: rgb(156, 156, 156);
}
/* 三方登录按钮 */
.link {
  font-size: 10px;
  color: rgb(190, 190, 190);
}
.githubLogin {
  cursor: pointer;
  font-size: 24px;
}

/* 遮罩样式 */
.mask {
  border-radius: 5px;
  position: absolute;
  top: -20px;
  width: 300px;
  height: 464px;
  overflow: hidden;
  transition: all 0.5s ease-in-out;
}
.mask-login {
  left: 20px;
  background-image: url(@/assets/img/qbg-login.jpg);
  background-size: cover;
  background-position: -25px 0;
}
.mask-register {
  right: 25px;
  left: 335px;
  background-image: url(@/assets/img/Qbg-register.jpg);
  background-size: cover;
}
.background1,
.background2 {
  background-color: #fff;
  position: relative;
  /* width: 800px; */
  width: 100%;
  /* height: 455px; */
  height: 100%;

  background-size: cover;
}
</style>
