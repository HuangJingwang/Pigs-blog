import { useUserStore } from '@/store/user'
import axios from 'axios'
// 引入仓库，取出token
// import { useUserStore } from '@/store/user'
// const userStore = useUserStore
//引入进度条
import nprogress from 'nprogress'
//引入相关进度条的样式
import 'nprogress/nprogress.css'
let requests = axios.create({
  baseURL: 'https://www.starrysummer.com:6533/pigs-blog',
  //超时的设置
  timeout: 10000,
})

requests.interceptors.request.use((config) => {
  const userStore = useUserStore()
  nprogress.start()
  config.headers['Content-Type'] = 'application/json'
  // let token = sessionStorage.getItem('token')
  let token = userStore.token
  if (token !== null) {
    config.headers.token = token
  }
  return config
})
requests.interceptors.response.use(
  (res) => {
    //res:实质就是项目中发请求、服务器返回的数据
    //进度条结束
    nprogress.done()
    return res.data
  },
  (err) => {
    //温馨提示:某一天发请求,请求失败,请求失败的信息打印出来
    nprogress.done()
    return err.message
  },
)

export default requests
