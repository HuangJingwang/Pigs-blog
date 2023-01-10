const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,
  devServer: {
    proxy: {
      '/pigs-blog': {
        target: 'http://49.233.45.84:6533',
        changeOrigin: true,
      },
    },
  },
})
