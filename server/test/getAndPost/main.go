package main

import "github.com/gin-gonic/gin"

func main() {
	r := gin.Default() //携带基础中间件启动
	r.GET("/ping/:id", func(c *gin.Context) {
		id := c.Param("id")
		user := c.DefaultQuery("user", "jingwang")
		pwd := c.Query("pwd")
		c.JSON(200, gin.H{
			"id": id,
			"user":user,
			"pwd":pwd,
		})
	})

	// 此处post用的是表单提交，不是json传参
	r.POST("/ping", func(c *gin.Context) {
		id := c.PostForm("id")
		user := c.DefaultPostForm("user", "jingwang")
		pwd := c.PostForm("pwd")
		c.JSON(200, gin.H{
			"id": id,
			"user":user,
			"pwd":pwd,
		})
	})
	r.PUT("/ping", func(c *gin.Context) {
		id := c.PostForm("id")
		user := c.DefaultPostForm("user", "jingwang")
		pwd := c.PostForm("pwd")
		c.JSON(200, gin.H{
			"id": id,
			"user":user,
			"pwd":pwd,
		})
	})
	r.DELETE("/ping/123", func(c *gin.Context) {
		id := c.Param("id")
		user := c.DefaultQuery("user", "jingwang")
		pwd := c.Query("pwd")
		c.JSON(200, gin.H{
			"id": id,
			"user":user,
			"pwd":pwd,
		})
	})
	r.Run() // listen and serve on 0.0.0.0:8080
}

