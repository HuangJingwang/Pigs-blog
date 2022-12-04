package main

import (
	"fmt"
	"github.com/gin-gonic/gin"
	"github.com/gin-gonic/gin/binding"
	"github.com/go-playground/validator/v10"
)

// 这个用于测试绑定json到对应结构体
/**
	标签中的含义：
		json:[json中的key]
		uri:[uri中的 :key]
		form:[url中的 key=... 中的key]
        binding：是否必传，自定义校验器 多种校验使用逗号分割
*/
type LoginParam struct {
	User string `json:"user" uri:"user" form:"user" binding:"required"`
	Pwd  string `json:"pwd" uri:"pwd" form:"pwd"`
	Age  int    `form:"age" binding:"mustbig"`
}

/** 如何自定义一个校验器
	1. 写一个对应的校验函数
    2. 注册路由
    3. 绑定到对应参数
 */
func mustBig(f1 validator.FieldLevel) bool {
	if (f1.Field().Interface().(int) < 18) {
		fmt.Println(f1.Field().Interface().(int))
		return false
	} else {
		return true
	}

}
func main() {
	r := gin.Default() //携带基础中间件启动
	r.GET("/ping/:user/:pwd", func(c *gin.Context) {
		var loginParam LoginParam
		err := c.ShouldBindUri(&loginParam)
		if err == nil {
			c.JSON(200, gin.H{
				"data": loginParam,
				"msg":  "成功了,这是使用的uri的方式",
			})
		} else {
			c.JSON(400, gin.H{
				"data": loginParam,
				"msg":  "失败了",
			})
		}
	})

	// 要注明是ShouldBindJson 而不能直接是ShouldBind,不然会默认绑定
	r.GET("/ping", func(c *gin.Context) {
		var loginParam LoginParam
		err := c.ShouldBindJSON(&loginParam)
		if err == nil {
			c.JSON(200, gin.H{
				"data": loginParam,
				"msg":  "成功了,这是使用的json方式",
			})
		} else {
			c.JSON(400, gin.H{
				"data": loginParam,
				"msg":  "失败了",
			})
		}
	})

	// 要注明是ShouldBindQuery 而不能直接是ShouldBind,不然会默认绑定
	// 对于路径 /ping?user=小明&pwd=123456 针对于标签form
	r.GET("/testBind", func(c *gin.Context) {
		var loginParam LoginParam
		err := c.ShouldBindQuery(&loginParam)
		if err == nil {
			c.JSON(200, gin.H{
				"data": loginParam,
				"msg":  "成功了,这是使用的resultful方式",
			})
		} else {
			c.JSON(400, gin.H{
				"data": loginParam,
				"msg":  "失败了",
			})
		}
	})

	/**
	如何默认绑定，如果是post请求会首先去判断 content-type 是application:json 还是application:xml来判定使用啥
	*/
	// 对于路径 /ping?user=小明&pwd=123456 针对于标签form
	r.GET("/testBind/form", func(c *gin.Context) {
		var loginParam LoginParam
		err := c.ShouldBindQuery(&loginParam)
		if err == nil {
			c.JSON(200, gin.H{
				"data": loginParam,
				"msg":  "成功了,这是使用的resultful方式",
			})
		} else {
			c.JSON(400, gin.H{
				"data": loginParam,
				"msg":  "失败了",
			})
		}
	})

	//这个用于测试如果必传项不传会咋样
	r.GET("/testBindRequired", func(c *gin.Context) {
		var loginParam LoginParam
		err := c.ShouldBindQuery(&loginParam)
		if err == nil {
			c.JSON(200, gin.H{
				"data": loginParam,
				"msg":  "成功了,这是使用的resultful方式",
			})
		} else {
			fmt.Println(err.Error())
			c.JSON(400, gin.H{
				"data": loginParam,
				"msg":  "失败了,tmd没绑定",
			})
		}
	})

	//这个用于测试如何使用自定义校验器
	// 如果验证怎么都不行，可以看一下字段是否绑定了对应的 json 还是form
	r.GET("/testBinding", func(c *gin.Context) {
		var loginParam LoginParam

		// 注意：绑定校验函数必须再绑定之前
		if v, ok := binding.Validator.Engine().(*validator.Validate); ok {
			v.RegisterValidation("mustbig", mustBig)
		}

		err := c.ShouldBindQuery(&loginParam)

		if err == nil {
			c.JSON(200, gin.H{
				"data": loginParam,
				"msg":  "成功了,这是使用的resultful方式",
			})
		} else {
			fmt.Println(err.Error())
			c.JSON(400, gin.H{
				"data": loginParam,
				"msg":  "失败了，",
			})
		}
	})
	r.Run() // listen and serve on 0.0.0.0:8080
}
