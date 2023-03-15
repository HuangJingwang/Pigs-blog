import { defineStore } from 'pinia'
import { getCoverImg } from '@/api'
export const useUserStore = defineStore('user', {
  state: () => {
    return {
      isLogin: false, //登錄狀態
      key: '', //三方登錄key
      showUserModal: false, //顯示登錄窗口
      userInfo: {}, //登錄信息
      token: '', //token
      showArticleModal: false, //显示文章管理模块
      coverImg: '', //封面图片路径
    }
  },
  getters: {
    role: (state) => {
      return state.userInfo.role
    },
  },
  actions: {
    async reqCoverImg() {
      let result = await getCoverImg()
      console.log(result, 'coverImg')
      if (result.code == 200) {
        this.coverImg = result.data
      }
    },
  },
  persist: {
    storage: localStorage,
    key: 'user',
    paths: ['token', 'userInfo', 'isLogin'],
  },
})
