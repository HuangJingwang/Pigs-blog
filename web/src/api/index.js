// import requests from './mockRequests'

// export const getArticleList = requests({
//   URL: 'http://localhost:3000/mock/articleList',
//   methods: 'GET',
// })

import requests from './requests'


export const getTestData = () => {
  requests({
    method: 'GET',
    url: 'http://localhost:3000/banner',
  })
}
