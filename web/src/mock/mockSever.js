import Mock from 'mockjs'
import articleList from './articleList.json'
Mock.mock('http://localhost:8080/mock/articleList', {
  code: '200',
  data: articleList,
})
