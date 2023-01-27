function debounce(func, wait) {
    let timeout

    return function () {
      let context = this // 保存this指向
      let args = arguments // 拿到event对象
      console.log(context, args)
      clearTimeout(timeout)
      timeout = setTimeout(function () {
        func.apply(context, args)
      }, wait)
    }
}
  export default debounce