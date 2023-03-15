import requests from './requests'

// 获取首页封面
export const getCoverImg = () => {
  return requests({
    method: 'GET',
    url: '/picture/getRandomPicture?position=main',
  })
}
// 获取首页文章列表数据
export const getArticleList = (data) => {
  let { pageNo, byViews, account, byAuthor } = data
  let url = `/articles/getArticlesPageData?pageNo=${pageNo}`
  byViews == true ? (url += `&orderByPV=true`) : (url += '&orderByPV=false')
  byAuthor == 'self' ? (url += `&account=${account}`) : null
  return requests({
    method: 'GET',
    url: url,
  })
}
// 获取首页用户数据
export const getHomeUserInfo = (account) => {
  let url =
    account == undefined
      ? '/user-info/getHomepageUserInfo'
      : `/user-info/getHomepageUserInfo?account=${account}`
  return requests({
    method: 'GET',
    url: url,
  })
}
// 获取文章详情数据
export const getArticleDetail = (id) => {
  return requests({
    method: 'GET',
    url: `/articles/detail/${id}`,
  })
}
//上一篇文章
export const getPreArticle = (id) => {
  return requests({
    methods: 'GET',
    url: `/articles/getPreArticle/${id}`,
  })
}
//下一篇文章
export const getNextArticle = (id) => {
  return requests({
    methods: 'GET',
    url: `/articles/getNextArticle/${id}`,
  })
}

// 归档数据
export const getArchives = () => {
  return requests({
    method: 'GET',
    url: '/articles/listArticles?status=published',
  })
}
// 获取分类列表数据
export const getGroupListData = () => {
  return requests({
    method: 'GET',
    url: '/articles-group/list',
  })
}
// 文章管理模块
// 获取已发布文章列表
export const getArticleHandleList = (params, byAuthor) => {
  let { account, pageNo, status } = params
  let url = byAuthor
    ? `/articles/getArticlesPageData?account=${account}&pageNo=${pageNo}&pageSize=5&status=${status}`
    : `/articles/getArticlesPageData?pageNo=${pageNo}&pageSize=5&status=${status}`
  return requests({
    method: 'GET',
    url: url,
  })
}
// 删除文章
export const getArticleDelete = (id) => {
  return requests({
    method: 'POST',
    url: `/articles/delete/${id}`,
  })
}

// 彻底删除文章
export const getArticleDeleteCompletely = (id) => {
  return requests({
    method: 'POST',
    url: `/articles/delete-forever/${id}`,
  })
}
// 获取标签数据
export const getTagList = () => {
  return requests({
    method: 'GET',
    url: '/articles-tags/list',
  })
}

// 创建新标签
export const createNewTag = (data) => {
  return requests({
    method: 'POST',
    url: '/articles-tags/create',
    data: data,
  })
}

// 删除标签
export const deleteTags = (id) => {
  return requests({
    method: 'POST',
    url: `/articles-tags/delete/${id}`,
  })
}
// 文章上传图片图库
export const uploadPictureStore = (data) => {
  return requests({
    method: 'POST',
    url: '/picture/addToPictureWareHouse',
    data: data,
  })
}

// 删除云上图片
export const deletePictures = (data) => {
  return requests({
    method: 'POST',
    url: '/picture/delete',
    data: data,
  })
}

// 保存文章
export const saveArticle = (data) => {
  return requests({
    method: 'POST',
    url: '/articles/save',
    data: data,
  })
}

// 更新文章
export const updateArticle = (data, id) => {
  return requests({
    method: 'POST',
    url: `/articles/update/${id}`,
    data: data,
  })
}

// 登录
export const login = (data) => {
  return requests({
    method: 'POST',
    url: '/user/login',
    data: data,
  })
}
// 注册
export const register = (data) => {
  return requests({
    method: 'POST',
    url: '/user/registry',
    data: data,
  })
}

// 登出
export const logout = () => {
  return requests({
    method: 'POST',
    url: '/user/logout',
  })
}

// 三方登录获取信息
export const getThree_partInfo = (key) => {
  return requests({
    method: 'GET',
    url: `/user/getGithubUserInfo/?key=${key}`,
  })
}
// 獲取箴言
export const getSayings = () => {
  return requests({
    method: 'GET',
    url: '/logion/random',
  })
}
// 分类获取当前分类文章
export const getGroupArticles = (query) => {
  return requests({
    method: 'GET',
    url: `/articles/listArticles?groupId=${query}`,
  })
}
