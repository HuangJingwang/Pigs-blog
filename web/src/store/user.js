import { defineStore } from 'pinia'
import { login } from '@/api'
export const useUserStore = defineStore('user', {
  state: () => {
    return {
      isLogin: false, //登錄狀態
      key: '', //三方登錄key
      showUserModal: false, //顯示登錄窗口
      userInfo: {}, //登錄信息
      token: '', //token
      showArticleModal: false,
    }
  },
  getters: {},
  actions: {},
})
