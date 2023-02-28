import { defineStore } from 'pinia'
export const useArticleThemeStore = defineStore('articleTheme', {
  state: () => {
    return {
      preview_themeList: [
        'default',
        'github',
        'vuepress',
        'mk-cute',
        'smart-blue',
        'cyanosis',
      ],
      preview_themeIndex: 0,
    }
  },
  getters: {
    theme(state) {
      return state.preview_themeList[state.preview_themeIndex]
    },
  },
})
