package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
)
func middle1() gin.HandlerFunc{
	return func(context *gin.Context) {
		fmt.Println("我在路由前开始了，我是中间件1")
		context.Next()
		fmt.Println("我在路由后开始了，我是中间件1")
	}
}
func middle2() gin.HandlerFunc{
	return func(context *gin.Context) {
		fmt.Println("我在路由前开始了，我是中间件2")
		context.Next()
		fmt.Println("我在路由后开始了，我是中间件2")
	}
}
func main() {
	r := gin.Default() //携带基础中间件启动
	v1 := r.Group("v1").Use(middle1(), middle2())
	v1.GET("test1", func(c *gin.Context) {
		fmt.Println("这个的路径是 /route/test1")
	})
	v1.GET("test2", func(c *gin.Context) {
		fmt.Println("这个的路径是 /route/test2")
	})
	r.Run() // listen and serve on 0.0.0.0:8080
}

