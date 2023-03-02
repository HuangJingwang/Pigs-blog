import { defineStore } from 'pinia'
import { getArticleList, getGroupListData, getTagList } from '@/api'
export const useArticleStore = defineStore('article', {
  state: () => {
    return {
      homeArticles: {
        //首頁文章列表
        totalArticles: 0, //總數
        hasNext: true, //是否有下一頁
        currentPage: 1, //當前頁碼
        articlesList: [], //文章列表
      },
      groupList: [], //分類列表
      tagList: [], //標簽列表
    }
  },
  getters: {},
  actions: {
    // 请求获取首页文章列表
    async reqArticleList() {
      try {
        let pageNo = this.homeArticles.currentPage
        // 有下一页时，请求数据
        if (this.homeArticles.hasNext) {
          let result = await getArticleList(pageNo)
          if (result.code == 200) {
            let { totalResult, hasNext, resultList } = result.data
            // 修改仓库
            this.$patch((state) => {
              state.homeArticles.hasNext = hasNext
              state.homeArticles.totalArticles = totalResult
              resultList.forEach((article) => {
                // 处理tag 和时间
                article.update_at = article.update_at.slice(0, 10)
                article.tags = article.tags.split(',')
                // 将文章推入到文章列表
                state.homeArticles.articlesList.push(article)
              })
            })
          }
        }
      } catch (error) {
        // 让表单组件显示错误
        // console.log(error)
        return error
      }
      console.log('homeArticles', this.homeArticles)
    },
    // 请求获取分类列表
    async reqGroupList() {
      let result = await getGroupListData()
      if (result.code == 200) {
        // console.log(result.data, '分类列表')
        // 过滤已删除元素
        let groupList = result.data.filter((group) => {
          return group.is_delete !== 1
        })
        // 定义递归，生成树形结构
        function getTree(rootList, parent, list) {
          rootList.forEach((group) => {
            group.value = group.id
            group.label = group.group_name
            if (group.parent_id !== null && group.parent_id == parent) {
              list.push(group) //推入数组作为父级元素
            }
          })
          list.forEach((group) => {
            group.children = [] //c=储存子元素
            getTree(rootList, group.id, group.children)
            if (group.children.length == 0) delete group.children
          })
          return list
        }
        this.groupList = getTree(groupList, 0, [])
        // console.log('article grouplist',this.groupList)
      }
    },

    async reqTagList() {
      let result = await getTagList()
      if (result.code == 200) {
        this.tagList = result.data
      }
      }
  },
})
