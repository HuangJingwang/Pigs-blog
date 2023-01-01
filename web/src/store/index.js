import { createStore } from 'vuex'
export default createStore({
  state: {
    count: 0,

    // 归档页面数据
    archivesList: [],
    archiveData: {
      total:50,
      page: 1,
      data: [
        { time: 1667089448000, title: 'css学习笔记', id: 564 },
        { time: 1667078648000, title: 'css学习笔记', id: 564 },
        { time: 1667071448000, title: 'css学习笔记', id: 685 },
        { time: 1603999448000, title: 'css学习笔记', id: 358 },
        { time: 1603913048000, title: 'css学习笔记', id: 358 },
        { time: 1603826648000, title: 'css学习笔记', id: 358 },
        { time: 1601234648000, title: 'css学习笔记', id: 358 },
        { time: 1538076248000, title: 'css学习笔记', id: 358 },
        { time: 1537989848000, title: 'css学习笔记', id: 358 },
        { time: 1535311448000, title: 'css学习笔记', id: 358 },
      ],
    },
  },
  getter: {},
  actions: {
    handleArchiveData({ commit, state }) {
      // 暂时没有获取数据的函数
      let result = []
      commit('GETARCHIVESLIST', result)
    },
  },
  mutations: {
    GETARCHIVESLIST(state, result) {
      console.log(123)
      state.archiveData.data.forEach(item => {
        // 处理日期数据
        const date = new Date(item.time)
        item.year = date.getFullYear()
        item.month =
          date.getMonth() + 1 < 10
            ? '0' + (date.getMonth() + 1)
            : date.getMonth() + 1
        item.date = date.getDate() + ''
        item.year_month = item.year + '年' + item.month + '月'
      
      })
      // 数组升维
      let obj = {}
      let newArr = []
      state.archiveData.data.forEach(item => {
        if (obj[item.year_month] == undefined) {
          //第 一次出现这个产品类型
          obj[item.year_month] = 1
          //把当前遍历出来的这个数据存进archivesList中
          newArr.push({
            year_month: item.year_month,
            data: [item],
          })
        } else {
          //说明这个产品的类型不是第一次出现.
          //判断当前这个item是属于newArr中的哪一类产品。然后加进这-类产品的da
          newArr.forEach((item2, index) => {
            if (item.year_month == item2.year_month) {
              newArr[index].data.push(item)
            }
          })
        }
      })
      state.archivesList = newArr
    },
  },
})
