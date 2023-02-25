import { createStore } from 'vuex'
import {
  getGroupListData,
  getTagList,
  getArticleList,
  getArchives,
  login,
} from '@/api'
export default createStore({
  state: {
    article: '',
    // 用户登陆数据
    user: {
      isLogin: 'false',
      key: '', //三方登录key
      // 判断是否弹出登录/注册组件
      showUserModal: false,
    },
    // 首页文章列表数据
    articleData: {
      totalArticle: 0,
      hasNext: 'true',
      articleList: [],
    },
    // 当前页数
    currentPageNo: 1,
    // 归档页面数据
    //归档排序依据
    // basis: 'create_at',
    archivesData: {
      total: 0,
      archivesList: [],
    },
    // 分类列表
    groupList: [],
    // 标签列表
    tagList: [],

    // 预览主题
    preview_themeList: [
      'default',
      'github',
      'vuepress',
      'mk-cute',
      'smart-blue',
      'cyanosis',
    ],
    preview_themeIndex: 0,
  },

  getter: {},

  actions: {
    // 发起登录请求
    async reqLogin({ commit }, data) {
      let result = await login(data)
      // console.log(result)
      return result
    },
    // 发起请求获取首页文章列表数据
    async reqArticleData({ commit, state }) {
      let pageNo = state.currentPageNo
      if (state.articleData.hasNext) {
        let result = await getArticleList(pageNo)
        commit('HANDLEARTICLEDATA', result)
      }
    },
    // 发起获取归档列表数据
    async reqArchiveData({ commit, state }) {
      let result = await getArchives()
      // console.log(author)
      commit('HANDLEARCHIVESDATA', result)
    },
    // 请求分类列表数据
    async reqGroupList({ commit }) {
      let result = await getGroupListData()
      commit('HANDLEGROUPLIST', result)
    },
    // 请求标签列表数据
    async reqTagList({ commit }) {
      let result = await getTagList()
      commit('HANDLETAGLIST', result)
    },
  },
  mutations: {
    // 处理归档数据
    HANDLEARCHIVESDATA(state, result) {
      state.archivesData.total = result.data.length
      let obj = {}
      // 存储数据
      let archivesList = []
      result.data.forEach((item1, index) => {
        // const date = item1[state.basis]
        // 处理时间数据
        const date = item1.create_at
        item1.year = date.substring(0, 4)
        item1.month = date.substring(5, 7)
        item1.day = date.substring(8, 10)
        item1.year_month = item1.year + '-' + item1.month
        // 判断此元素第一次出现
        if (obj[item1.year_month] == undefined) {
          obj[item1.year_month] = 1
          // 将此数据存进list
          archivesList.push({
            date: item1.create_at,
            year_month: item1.year_month,
            year: item1.year,
            month: item1.month,
            data: [item1],
            id: index,
          })
        } else {
          // 若非第一次出现，则推入data中
          archivesList.forEach((item2, index) => {
            if (item1.year_month == item2.year_month) {
              archivesList[index].data.push(item1)
            }
          })
        }
      })
      state.archivesData.archivesList = archivesList
    },

    // 处理分类数据
    HANDLEGROUPLIST(state, result) {
      let groupListData = result.data
      // 第一次遍历，取得根节点
      function getTree(rootList, parent, list) {
        rootList.forEach((item) => {
          item.value = item.id
          item.label = item.group_name
          if (item.parent_id == parent) {
            list.push(item)
          }
        })
        list.forEach((item2) => {
          item2.children = [] //定义空数组，存储子元素
          getTree(rootList, item2.id, item2.children)
          if (item2.children.length == 0) {
            delete item2.children
          }
        })
        return list
      }
      state.groupList = getTree(groupListData, 0, [])
      // console.log(state.groupList)
    },
    // 处理标签数据
    HANDLETAGLIST(state, result) {
      state.tagList = result.data
    },
    // 处理文章列表数据
    HANDLEARTICLEDATA(state, result) {
      state.articleData.totalArticle = result.data.totalResult
      state.articleData.hasNext = result.data.hasNext
      result.data.resultList.forEach((article) => {
        state.articleData.articleList.push(article)
      })
      console.log(state.articleData.articleList)
      state.articleData.articleList.forEach((article) => {
        article.update_at = article.update_at.slice(0, 10)
      })
    },
  },
})
