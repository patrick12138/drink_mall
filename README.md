# 零售系统

#### 介绍
主要实现的功能有：用户模块：用户注册、登录、添加收货地址，获取客户端IP和评价、订单显示。商品模块：商品详情显示、商品图片显示、商品SKU列表展示、商品评论分页显示。首页：分类及分类推荐、新品推荐、轮播图、商品名模糊搜索和全局导航栏。购物车：丰富的列表操作和显示。支付宝支付：扫码支付、订单状态定时改变和商品销量库存相应变化。商家后台：添加商品。



#### 软件架构
本项目采用前后端分离架构，主要应用了SpringBoot+Vue框架来实现，前端页面主要使用了ElementUI，数据库使用了MySQL数据库，通过MyBatisPlus提供的逆向工程（Mybatis Generator）动态生成实体类以及SQL语句。MyBatisPlus+MyBatis完成对数据库操作。


#### 使用说明

1.  vue目录下为前端项目，与src同级目录。进入vue目录执行npm i下载所需依赖。在vue/public/urlConfig.js文件中设置了axios请求基于的地址，可以修改成自己的后端地址。执行npm run serve运行项目
2.  在项目的根路径（与src同级）执行mvn clean package -DskipTests跳过测试文件打包。修改application.yml文件中数据库连接配置和文件上传路径，AlipayClientConfig.java中修改支付公私钥，OrdersController.java修改支付回调接口和返回页面。
3.  前端大部分图片显示的链接为后端的/Img，在WebConfig.java中可修改。前端可修改为:src="require('../img'+变量)"，此路径为vue项目下的img文件夹

#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  Gitee 官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解 Gitee 上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是 Gitee 最有价值开源项目，是综合评定出的优秀开源项目
5.  Gitee 官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  Gitee 封面人物是一档用来展示 Gitee 会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
