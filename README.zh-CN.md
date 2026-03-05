# 酒水饮料商城

[English](./README.md) | [简体中文](./README.zh-CN.md)

一个聚焦酒水饮料场景的前后端分离电商示例项目。

## 功能特性

- **用户模块**：用户注册/登录、收货地址管理、客户端 IP 获取、评价、订单展示。
- **商品模块**：商品详情展示、商品图片展示、SKU 列表、商品评论分页。
- **首页模块**：分类与分类推荐、新品推荐、轮播图、商品名模糊搜索、全局导航栏。
- **购物车模块**：丰富的列表显示与操作。
- **支付宝支付**：扫码支付、订单状态定时更新、商品销量与库存联动。
- **商家后台**：商品管理（含添加商品）。

## 技术架构

本项目采用前后端分离架构：

- **后端**：Spring Boot
- **前端**：Vue + Element UI（位于 `vue/`）
- **数据库**：MySQL
- **数据访问**：MyBatis-Plus + MyBatis（结合 MyBatis Generator 生成实体与 SQL）

## 项目结构

- `src/`：后端源码
- `vue/`：前端项目

## 使用说明

### 1）启动前端

```bash
cd vue
npm install
npm run serve
```

可在以下文件中配置后端请求基础地址：

- `vue/public/urlConfig.js`

### 2）构建并运行后端

在项目根目录（与 `src` 同级）执行：

```bash
mvn clean package -DskipTests
```

运行前请按需修改：

- `application.yml` 中的数据库连接配置与文件上传路径
- `AlipayClientConfig.java` 中的支付公私钥配置
- `OrdersController.java` 中的支付回调接口与返回页面

### 3）静态资源 / 图片路径说明

前端大部分图片链接由后端 `/Img` 路径提供。

- 可在 `WebConfig.java` 中调整后端映射
- 前端也可切换为本地图片加载，例如：

```vue
:src="require('../img' + 变量)"
```

（其中 `img` 位于 Vue 项目目录下）

## 参与贡献

1. Fork 本仓库
2. 新建功能分支（如 `feat_xxx`）
3. 提交代码
4. 发起 Pull Request
