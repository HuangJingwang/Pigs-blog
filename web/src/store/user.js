import { defineStore } from 'pinia'
export const useUserStore = defineStore('user', {
  state: () => {
    return {
      isLogin: false, //登錄狀態
      key: '', //三方登錄key
      showUserModal: false, //顯示登錄窗口
      userInfo: {}, //登錄信息
      token: '', //token
      showArticleModal: false, //显示文章管理模块
    }
  },
  getters: {
    role: (state) => {
      return state.userInfo.role
    },
  },
  actions: {},
  persist: {
    storage: sessionStorage,
    key: 'user',
    paths: ['token', 'userInfo', 'isLogin'],
  },
})
