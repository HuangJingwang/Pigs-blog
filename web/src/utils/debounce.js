function debounce(fn, delay) {
  let timer = null
  return function () {
    if (timer !== null) {
      clearTimeout(timer)
    }
    timer = setTimeout(() => {
      fn()
    }, delay)
  }
}

export default debounce

// function debounce(fn, delay) {
//   // 空定时器
//   let timeout = null
//   return function () {
//     let context = this
//     let args = arguments
//     clearTimeout(timeout)
//     timeout = setTimeout(() => {
//       fn.apply(context, args)
//     }, delay)
//   }
// }
// export default debounce
