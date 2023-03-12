import { useUserStore } from '@/store/user'
import { getArticleDeleteCompletely } from '@/api'
import { ElMessageBox, ElMessage } from 'element-plus'
//需传入当前文章用户account
export async function useDeleteCompletely(currentAccount, articleId, callback) {
  // 获取登录用户account
  const userStore = useUserStore()
  if (userStore.role.indexOf('root') == -1) {
    ElMessageBox.alert('权限不足,请联系管理员获取权限', '权限不足', {
      // if you want to disable its autofocus
      // autofocus: false,
      confirmButtonText: 'OK',
      callback: (action) => {
        ElMessage({
          type: 'info',
          message: `action: ${action}`,
        })
      },
    })
    return
  }
  if (userStore.userInfo.account == currentAccount) {
    // 文章属于自己
    let result = await getArticleDeleteCompletely(articleId)
    if (result.code == 200) {
      // 删成功
      callback()
      ElMessage({
        type: 'success',
        message: 'Delete success',
      })
      return result
    } else if (result.code == 500) {
      // 移入草稿箱失败
      ElMessageBox.alert(`${result.msg}`, 'Error', {
        // if you want to disable its autofocus
        // autofocus: false,
        confirmButtonText: 'OK',
        callback: (action) => {},
      })
    }
    return result
  } else {
    // 文章不属于自己
    ElMessageBox.confirm('这篇文章不属于您,确定删除?', 'Warning', {
      confirmButtonText: 'OK',
      cancelButtonText: 'Cancel',
      type: 'warning',
    })
      .then(async () => {
        let result = await getArticleDeleteCompletely(articleId)
        if (result.code == 200) {
          callback()
          ElMessage({
            type: 'success',
            message: 'Delete success',
          })
          return result
        } else if (result.code == 500) {
          ElMessageBox.alert(`${result.msg}`, 'Error', {
            // if you want to disable its autofocus
            // autofocus: false,
            confirmButtonText: 'OK',
            callback: (action) => {},
          })
          return
        }
      })
      .catch(() => {
        ElMessage({
          type: 'info',
          message: 'Delete canceled',
        })
      })
  }
}
