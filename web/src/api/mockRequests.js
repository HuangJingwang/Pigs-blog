// import axios from 'axios'

// // 二次封装axios

// let requests = axios.create({
//   baseURL: 'http://localhost:3000/mock',
//   timeout: 10000,
// })
// // use(两个参数)
// // 请求拦截器
// requests.interceptors.request.use(
//   req => {
//     // 在发送请求前要做的事儿
//     return req
//   },
//   err => {
//     // 在请求错误时要做的事儿
//     // 该返回的数据则是axios.catch(err)中接收的数据
//     return Promise.reject(err)
//   }
// )
// // 响应拦截器
// requests.interceptors.response.use(
//   res => {
//     return res.data
//   },
//   err => {
//     return Promise.reject(err)
//   }
// )
// export default requests
