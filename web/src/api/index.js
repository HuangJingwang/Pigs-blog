import requests from './requests'
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
export const createNewTag = data => {
  return requests({
    method: 'POST',
    url: '/articles-tags/create/',
    data: data,
  })
}

// 删除标签
export const deleteTags = id => {
  return requests({
    method: 'POST',
    url: `/articles-tags/delete/${id}`,
  })
}
// 保存文章
export const saveArticle = data => {
  return requests({
    method: 'POST',
    url: '/articles/save',
    data:data
  })
}
