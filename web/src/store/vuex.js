import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
// requests
import {
  getGroupListData,
  getTagList,
  getArticleList,
  getArchives,
  login,
} from '@/api'
// store
export default createStore({
  state: {
    // 用户登陆数据
    // user: {
    //   isLogin: false,
    //   key: '', //三方登录key
    //   // 判断是否弹出登录/注册组件
    //   showUserModal: false,
    //   userInfo: {},
    // },
    // 首页文章列表数据
    articleData: {
      totalArticle: 0,
      hasNext: true,
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
  },

  getter: {},

  actions: {
    // 发起请求获取首页文章列表数据
    // async reqArticleData({ commit, state }) {
    //   let pageNo = state.currentPageNo
    //   if (state.articleData.hasNext) {
    //     let result = await getArticleList(pageNo)
    //     commit('HANDLEARTICLEDATA', result)
    //   }
    // },
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

    // 处理分类数据
    HANDLEGROUPLIST(state, result) {
      // 去除已删除元素
      let groupData = result.data
      function getTree(rootList, parent, list) {
        rootList.forEach((item) => {
          item.value = item.id
          item.label = item.group_name
          if (item.parent_id !== null && item.parent_id == parent) {
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
      state.groupList = getTree(groupData, 0, [])
    },
    // 处理标签数据
    HANDLETAGLIST(state, result) {
      state.tagList = result.data
    },
    // 处理文章列表数据

  },
})
