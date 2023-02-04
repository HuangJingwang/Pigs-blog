import requests from './requests'

// 获取首页文章列表数据
export const getArticleList = (pageNo = 0, author) => {
  return requests({
    method: 'GET',
    url: `/articles/getArticlesPageData?pageNo=${pageNo}`,
  })
}

// 归档数据
export const getArchives = (author) => {
  // if (author !=='') {
  // return requests({
  //     method: 'GET',
  //     url: `/articles/listArticles?author=${author}`
  // })
  // } else {
  //   return requests({
  //     method: 'GET',
  //     url: '/articles/listArticles'
  // })
  // }

  return requests({
    method: 'GET',
    url: '/articles/listArticles',
  })
}
// 获取分类列表数据
export const getGroupListData = () => {
  return requests({
    method: 'GET',
    url: '/articles-group/list',
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
