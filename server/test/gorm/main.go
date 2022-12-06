package main
import (
	"fmt"
	"github.com/jinzhu/gorm"
	_ "github.com/jinzhu/gorm/dialects/mysql"
)

// Article 这是一个表的model
type Article struct {
	gorm.Model // 这个必须要放在其他的字段最前面，不然此前的字段不会生效
	Name string
	UpdateBy string
	CreateBy string
}

func main() {
	db, err := gorm.Open("mysql", "root:123456@/pigs_blog?charset=utf8&parseTime=True&loc=Local")
	if err != nil{
		fmt.Println("没连接上")
	}
	fmt.Println("连接上了")

	db.AutoMigrate() // 自动迁移，如果表不存在就会创建

	db.Create(&Article{
		Name: "java,从入门到入坟",
		CreateBy: "hjw",
		UpdateBy: "zzy",
	})
	defer db.Close()
}
