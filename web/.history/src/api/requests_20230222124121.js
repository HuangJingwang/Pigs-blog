// import axios from 'axios'
// //引入进度条
// import nprogress from 'nprogress'
// //引入相关进度条的样式
// import 'nprogress/nprogress.css'
// let requests = axios.create({
//   baseURL: 'http://49.233.45.84:6533/pigs-blog',
//   //超时的设置
//   timeout: 5000,
// })

// requests.interceptors.request.use((config) => {
//   nprogress.start()
//   console.log(config)
//   config.headers['Content-Type'] = 'application/json'
//   config.data = JSON.stringify(config.data)
//   // console.log(config.headers)
//   return config
// })
// requests.interceptors.response.use(
//   res => {
//     //res:实质就是项目中发请求、服务器返回的数据
//     //进度条结束
//     nprogress.done()
//     return res.data
//   },
//   err => {
//     //温馨提示:某一天发请求,请求失败,请求失败的信息打印出来
//     nprogress.done()
//     // console.log(err.message)
//     //终止Promise链
//     return new Promise()
//   }
// )

// export default requests

import axios from 'axios'
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
  nprogress.start()
  config.headers['Content-Type'] = 'application/json'
  config.data = JSON.stringify(config.data)
  // console.log(config.headers)
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
