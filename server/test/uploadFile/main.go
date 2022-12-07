package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default() //携带基础中间件启动
	r.GET("/file", func(c *gin.Context) {
		file, err := c.FormFile("exampleFile") // 这里使用的是body中的文件key名
		if err != nil {
			c.JSON(400, gin.H{
				"code": 400,
				"msg":  "there isn't a file name exampleFile",
			})
		}
		err1 := c.SaveUploadedFile(file, "./"+file.Filename)
		if err1 != nil {
			c.JSON(400, gin.H{
				"code": 400,
				"msg":  "the file cannot be upload",
			})
		}
		c.Writer.Header().Add("Content-Disposition", fmt.Sprintf("attachment; filename=%s", file.Filename))
		c.File("./" + file.Filename)
	})
	r.Run() // listen and serve on 0.0.0.0:8080
}
