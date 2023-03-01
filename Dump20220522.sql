-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 124.220.48.2    Database: retail
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '主键 分类id主键',
  `category_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称 分类名称',
  `category_level` int NOT NULL COMMENT '分类层级 分类得类型，\n1:一级大分类\n2:二级分类\n3:三级小分类',
  `parent_id` int NOT NULL COMMENT '父层级id 父id 上一级依赖的id，1级分类则为0，二级三级分别依赖上一级',
  `category_icon` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '图标 logo',
  `category_slogan` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '口号',
  `category_pic` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '分类图',
  `category_bg_color` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '背景颜色',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='商品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'女装男装',1,0,'cake.png','靓出你的世界','act1.png','black'),(2,'饼干/膨化',1,0,'cookies.png','健康饮食远离我','act2.png','black'),(3,'熟食/肉类',1,0,'meat.png','nice！nice！nice！','act3.png','black'),(4,'素食/卤味',1,0,'bamboo.png','香气四溢','act1.png','black'),(5,'坚果/炒货',1,0,'nut.png','酥酥脆脆，回味无穷','act2.png','black'),(6,'糖果',1,0,'candy.png',NULL,'act3.png','black'),(7,'巧克力',1,0,'chocolate.png',NULL,'act1.png','black'),(8,'海味/河鲜',1,0,'fish.png',NULL,'act2.png','black'),(9,'花茶/果茶',1,0,'tea.png','健康快乐每一天','act3.png','black'),(10,'手机',1,0,'package.png','每日一个小惊喜','act1.png','black'),(11,'女装',2,1,NULL,NULL,NULL,NULL),(12,'男装',2,1,NULL,NULL,NULL,NULL),(13,'饼干',2,2,NULL,NULL,NULL,NULL),(14,'薯片',2,2,NULL,NULL,NULL,NULL),(15,'虾条',2,2,NULL,NULL,NULL,NULL),(16,'猪肉脯',2,3,NULL,NULL,NULL,NULL),(17,'牛肉丝',2,3,NULL,NULL,NULL,NULL),(18,'小香肠',2,3,NULL,NULL,NULL,NULL),(19,'豆干',2,4,NULL,NULL,NULL,NULL),(20,'干笋',2,4,NULL,NULL,NULL,NULL),(21,'鸭脖',2,4,NULL,NULL,NULL,NULL),(22,'坚果',2,5,NULL,NULL,NULL,NULL),(23,'锅巴',2,5,NULL,NULL,NULL,NULL),(24,'糖果',2,6,NULL,NULL,NULL,NULL),(25,'蜜饯',2,6,NULL,NULL,NULL,NULL),(26,'巧克力',2,7,NULL,NULL,NULL,NULL),(27,'果冻',2,7,NULL,NULL,NULL,NULL),(28,'海带丝',2,8,NULL,NULL,NULL,NULL),(29,'小鱼干',2,8,NULL,NULL,NULL,NULL),(30,'鱿鱼丝',2,8,NULL,NULL,NULL,NULL),(31,'花茶',2,9,NULL,NULL,NULL,NULL),(32,'果茶',2,9,NULL,NULL,NULL,NULL),(33,'二级分类1',2,10,NULL,NULL,NULL,NULL),(34,'二级分类2',2,10,NULL,NULL,NULL,NULL),(35,'蒸蛋糕',3,11,NULL,NULL,NULL,NULL),(36,'脱水蛋糕',3,12,NULL,NULL,NULL,NULL),(37,'瑞士卷',3,13,NULL,NULL,NULL,NULL),(38,'软面包',3,14,NULL,NULL,NULL,NULL),(39,'马卡龙',3,15,NULL,NULL,NULL,NULL),(40,'千层饼',3,16,NULL,NULL,NULL,NULL),(41,'甜甜圈',3,17,NULL,NULL,NULL,NULL),(42,'蒸三明治',3,18,NULL,NULL,NULL,NULL),(43,'铜锣烧',3,19,NULL,NULL,NULL,NULL),(44,'华夫饼',3,20,NULL,NULL,NULL,NULL),(45,'老婆饼',3,21,NULL,NULL,NULL,NULL),(46,'奥利奥',3,22,NULL,NULL,NULL,NULL),(47,'粤利粤',3,23,NULL,NULL,NULL,NULL),(49,'膨化虾条',3,24,NULL,NULL,NULL,NULL),(50,'油炸虾条',3,25,NULL,NULL,NULL,NULL),(51,'海鲜虾条',3,26,NULL,NULL,NULL,NULL),(52,'三级分类1',3,27,NULL,NULL,NULL,NULL),(53,'三级分类2',3,28,NULL,NULL,NULL,NULL),(54,'三级分类3',3,29,NULL,NULL,NULL,NULL),(55,'三级分类4',3,30,NULL,NULL,NULL,NULL),(56,'三级分类5',3,31,NULL,NULL,NULL,NULL),(57,'三级分类6',3,32,NULL,NULL,NULL,NULL),(58,'三级分类7',3,33,NULL,NULL,NULL,NULL),(59,'三级分类8',3,34,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `product_id` varchar(64) DEFAULT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (1,25,'20','2022-05-08 15:01:30'),(2,25,'19','2022-05-08 15:03:32'),(3,25,'3','2022-05-08 16:08:32'),(4,25,'3','2022-05-08 16:08:32'),(5,29,'19','2022-05-08 21:04:16'),(6,29,'11','2022-05-08 21:21:40'),(7,29,'21','2022-05-08 21:21:46'),(8,29,'1','2022-05-08 22:08:18'),(9,29,'20','2022-05-09 10:17:07'),(10,29,'3','2022-05-09 10:17:28'),(11,29,'2','2022-05-09 20:03:03'),(12,30,'19','2022-05-09 22:51:33'),(13,29,'22','2022-05-10 12:36:09'),(14,31,'19','2022-05-10 13:07:07'),(15,NULL,'10','2022-05-10 16:42:41'),(16,NULL,'19','2022-05-10 16:52:08'),(17,NULL,'20','2022-05-10 16:52:11'),(18,NULL,'19','2022-05-10 16:53:48'),(19,29,'4','2022-05-10 17:14:45'),(20,29,'1525388656787906600','2022-05-14 16:17:53'),(21,29,'10','2022-05-14 17:14:55'),(22,29,'9','2022-05-14 17:15:00'),(23,29,'8','2022-05-14 17:15:02'),(24,NULL,'3','2022-05-15 15:28:23'),(25,NULL,'1525388656787906600','2022-05-15 15:28:39'),(26,30,'3','2022-05-15 15:29:06'),(27,NULL,'3','2022-05-15 15:29:43'),(28,32,'3','2022-05-15 15:35:06'),(29,NULL,'1525388656787906600','2022-05-15 16:16:53'),(30,NULL,'1525388656787906600','2022-05-15 17:35:10'),(31,NULL,'3','2022-05-15 17:35:29'),(32,NULL,'19','2022-05-15 17:35:31'),(33,30,'1525388656787906600','2022-05-16 10:31:13'),(34,30,'1526033918191509500','2022-05-16 11:05:22'),(35,33,'1526033918191509500','2022-05-16 13:00:18'),(36,NULL,'19','2022-05-16 13:36:58'),(37,NULL,'1526033918191509500','2022-05-19 09:42:46');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `index_img`
--

DROP TABLE IF EXISTS `index_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `index_img` (
  `img_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `img_url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片 图片地址',
  `img_bg_color` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '背景色 背景颜色',
  `prod_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品id 商品id',
  `category_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品分类id 商品分类id',
  `index_type` int NOT NULL COMMENT '轮播图类型 轮播图类型，用于判断，可以根据商品id或者分类进行页面跳转，1：商品 2：分类',
  `seq` int NOT NULL COMMENT '轮播图展示顺序 轮播图展示顺序，从小到大',
  `status` int NOT NULL COMMENT '是否展示:1表示正常显示，0表示下线 是否展示，1：展示    0：不展示',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间 创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 更新',
  PRIMARY KEY (`img_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='轮播图 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `index_img`
--

LOCK TABLES `index_img` WRITE;
/*!40000 ALTER TABLE `index_img` DISABLE KEYS */;
INSERT INTO `index_img` VALUES ('1','ad1.jpg','green','101',NULL,1,1,1,'2021-04-14 17:21:13','2021-04-14 17:21:18'),('2','ad2.jpg','pink',NULL,'1',2,2,1,'2021-04-14 17:21:49','2021-04-14 17:21:52'),('3','ad3.jpg','black','103',NULL,1,3,1,'2021-04-14 17:22:23','2021-04-14 17:22:27'),('4','ad4.jpg','orange',NULL,'2',2,4,1,'2021-04-14 17:23:06','2021-04-14 17:23:08'),('5','ad5.jpg','yellow','101',NULL,1,2,0,'2021-04-24 09:34:01','2021-04-25 09:34:05');
/*!40000 ALTER TABLE `index_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_item`
--

DROP TABLE IF EXISTS `order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_item` (
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单项ID',
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID',
  `product_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID',
  `product_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称',
  `product_img` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品图片',
  `sku_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'skuID',
  `sku_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'sku名称',
  `product_price` decimal(32,8) NOT NULL COMMENT '商品价格',
  `buy_counts` int NOT NULL COMMENT '购买数量',
  `total_amount` decimal(32,8) DEFAULT NULL COMMENT '商品总金额',
  `basket_date` datetime DEFAULT NULL COMMENT '加入购物车时间',
  `buy_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '购买时间',
  `is_comment` int DEFAULT NULL COMMENT '评论状态： 0 未评价  1 已评价',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='订单项/快照 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
INSERT INTO `order_item` VALUES ('1516391907830394881','7bff8a4a675a2834a4c63216d2f1e73a','19','加力加小虾条丰富叫我呢端午节读取温度','jlj_1.png','5','加力加 100g',2.00000000,2,4.00000000,'2022-04-19 11:34:14','2022-04-19 20:22:58',0),('1516391907863949313','7bff8a4a675a2834a4c63216d2f1e73a','19','加力加小虾条丰富叫我呢端午节读取温度','jlj_1.png','5','加力加 100g',2.00000000,2,4.00000000,'2022-04-19 11:34:13','2022-04-19 20:22:58',0),('1516771798924935170','2b5675a39d6e51264a90104d4a07570f','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,3,6.00000000,'2022-04-17 21:31:43','2022-04-20 21:32:31',1),('1516771798924935171','2b5675a39d6e51264a90104d4a07570f','19','加力加小虾条丰富叫我呢端午节读取温度','jlj_1.png','5','加力加 100g',2.00000000,1,2.00000000,'2022-04-19 11:34:10','2022-04-20 21:32:31',1),('1518858852466970626','85c706235856d6ef722e9f245885309b','19','加力加小虾条丰富叫我呢端午节读取温度','jlj_1.png','5','加力加 100g',2.00000000,1,2.00000000,'2022-04-19 11:34:09','2022-04-26 15:45:44',1),('1522417463449677825','14b8c2b94f2bb0986294e360a622d453','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-05 22:24:24','2022-05-06 11:26:23',1),('1522494152443457537','23bfec67340b8af166ecf0c56792af68','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,4,8.00000000,'2022-05-05 22:24:24','2022-05-06 16:31:07',0),('1522590202030256129','16315cc0e0f01782d8d66b6e6ce842c4','3','咪咪小虾条','mmxt_1.png','2','咪咪虾条 6连包',5.00000000,27,135.00000000,'2022-04-12 19:19:40','2022-05-06 22:52:47',0),('1522769207148765186','9311205e57d745167689a52ec93a0e5f','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 10:43:57','2022-05-07 10:44:05',0),('1522769363411755009','92135b400b533ef09c74407a73da691b','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 10:43:57','2022-05-07 10:44:42',0),('1522770394543644673','d13e9a9149844c962ded9a50a1885fd3','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 10:43:57','2022-05-07 10:48:48',0),('1522771350480007170','fec2d6ed2b4e8b22a79f0d738f2cb03d','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 10:43:58','2022-05-07 10:52:36',0),('1522772027516170242','bfcc718f89e2d0d33bef5c52d8e6c287','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 10:43:58','2022-05-07 10:55:17',0),('1522772150983897089','79973836145e97773d0b1deeba68d5e0','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,6,12.00000000,'2022-04-19 10:55:40','2022-05-07 10:55:47',0),('1522772354432806913','a6e6152dba69a77476b4dc2136ae620e','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 10:43:57','2022-05-07 10:56:35',0),('1522775029933260801','2ba6fb02bda8df370ab612190605db26','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 10:43:58','2022-05-07 11:07:13',0),('1522780024187494402','fb6c2cbe6b2636cf69d5ab5e047a5bb6','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 11:26:55','2022-05-07 11:27:04',0),('1522787670542073857','228559261a7f6b7c79d83f6b80d7b6da','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 11:26:54','2022-05-07 11:57:27',0),('1522790581086982146','9400758947422d1f54c13048df00c474','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 11:26:55','2022-05-07 12:09:01',0),('1522865698437595138','18508fcdef6afef38b8c3efcbfe70956','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,2,4.00000000,'2022-05-07 11:26:55','2022-05-07 17:07:30',0),('1522865698437595139','18508fcdef6afef38b8c3efcbfe70956','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 11:26:55','2022-05-07 17:07:30',0),('1522943732062638081','3c3832eaedadedcb8168f74ff0ae3aaf','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,4,8.00000000,'2022-05-07 21:50:04','2022-05-07 22:17:35',0),('1522943732062638082','3c3832eaedadedcb8168f74ff0ae3aaf','20','加力加虾条2','wwxb_1.png','6','加力加 100g',2.00000000,1,2.00000000,'2022-05-07 17:07:05','2022-05-07 22:17:35',0),('1525738569669320706','48115e88b42d5fd197fa4099d744651d','1525388656787906600','测试','3272b4b4d29e43a8ac3bfdd64529228d.webp','1525388853886640129','测试',1.00000000,1,1.00000000,'2022-05-15 14:09:58','2022-05-15 15:23:16',0),('1525743081461161985','3147bdce92e221c64b42ad93ac6fc7bb','3','咪咪小虾条','hfb_1.png','2','咪咪虾条 6连包',5.00000000,1,5.00000000,'2022-05-15 15:35:11','2022-05-15 15:41:12',0),('1525745244795084802','e498861ef71c09d2aca63d73d0899869','3','咪咪小虾条','hfb_1.png','2','咪咪虾条 6连包',5.00000000,1,5.00000000,'2022-05-15 15:49:45','2022-05-15 15:49:47',0);
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `order_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单ID 同时也是订单编号',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `untitled` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '产品名称（多个产品用,隔开）',
  `receiver_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人快照',
  `receiver_mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货人手机号快照',
  `receiver_address` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收货地址快照',
  `total_amount` decimal(32,8) DEFAULT NULL COMMENT '订单总价格',
  `actual_amount` int DEFAULT NULL COMMENT '实际支付总价格',
  `pay_type` int DEFAULT NULL COMMENT '支付方式 1:微信 2:支付宝',
  `order_remark` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单备注',
  `status` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:已完成 6:已关闭',
  `delivery_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配送方式',
  `delivery_flow_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '物流单号',
  `order_freight` decimal(32,8) DEFAULT '0.00000000' COMMENT '订单运费 默认可以为零，代表包邮',
  `delete_status` int DEFAULT '0' COMMENT '逻辑删除状态 1: 删除 0:未删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间（成交时间）',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `pay_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '付款时间',
  `delivery_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发货时间',
  `flish_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '完成时间',
  `cancel_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '取消时间',
  `close_type` int DEFAULT NULL COMMENT '订单关闭类型1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='订单 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES ('3147bdce92e221c64b42ad93ac6fc7bb','32','咪咪小虾条,','李','18379576373','江西省 南昌市 青山湖区 ',5.00000000,5,NULL,NULL,'2',NULL,NULL,0.00000000,0,'2022-05-15 15:41:12','2022-05-15 15:45:00','2022-05-15 15:41:12','2022-05-15 15:41:12','2022-05-15 15:41:12','2022-05-15 15:41:12',NULL),('3b5675cd9f5066d6fb6240e626d2ba69','30','加力加小虾条丰富叫我呢端午节读取温度,','张三','12312312312','河北省 秦皇岛市 海港区 祁连山北路路达汽车文化园1A-103',2.00000000,2,NULL,NULL,'4',NULL,NULL,0.00000000,0,'2022-05-09 22:53:00','2022-05-09 22:55:00','2022-05-09 22:53:00','2022-05-09 22:53:00','2022-05-09 22:53:00','2022-05-09 22:53:00',NULL),('48115e88b42d5fd197fa4099d744651d','29','测试,','123','123','湖南省 长沙市 天心区 学府大道999号',1.00000000,1,NULL,NULL,'1',NULL,NULL,0.00000000,0,'2022-05-15 15:23:16',NULL,'2022-05-15 15:23:16','2022-05-15 15:23:16','2022-05-15 15:23:16','2022-05-15 15:23:16',NULL),('8bcc211baebf66721e6af7eb7babe1e8','29','加力加小虾条丰富叫我呢端午节读取温度,','123','123','湖南省 长沙市 天心区 学府大道999号',2.00000000,2,NULL,NULL,'4',NULL,NULL,0.00000000,0,'2022-05-10 17:09:01','2022-05-10 17:14:48','2022-05-10 17:09:01','2022-05-10 17:09:01','2022-05-10 17:09:01','2022-05-10 17:09:01',NULL),('b91bff09b57fefb6daffea65a1404af9','29','加力加小虾条丰富叫我呢端午节读取温度,','123','123','江西省 南昌市 红谷滩区 学府大道999号',4.00000000,4,NULL,NULL,'1',NULL,NULL,0.00000000,0,'2022-05-10 17:02:28',NULL,'2022-05-10 17:02:28','2022-05-10 17:02:28','2022-05-10 17:02:28','2022-05-10 17:02:28',NULL),('bfcc718f89e2d0d33bef5c52d8e6c287','25','加力加虾条2,','零售','123','江苏省 南京市 鼓楼区 虎锯路87号(近虎踞南路)',2.00000000,2,NULL,NULL,'1',NULL,NULL,0.00000000,0,'2022-05-07 10:55:17','2022-05-07 20:57:33','2022-05-07 10:55:17','2022-05-07 10:55:17','2022-05-07 10:55:17','2022-05-07 10:55:17',NULL),('c3423cf7e2100696776c33b1d244202e','29','加力加小虾条丰富叫我呢端午节读取温度,','李四','1234567789','河南省 洛阳市 洛龙区 古城路辅路与市府西街交叉口东北300米',6.00000000,6,NULL,NULL,'4',NULL,NULL,0.00000000,0,'2022-05-07 21:32:02','2022-05-08 22:32:31','2022-05-08 21:32:02','2022-05-08 21:32:02','2022-05-08 21:32:02','2022-05-07 21:32:02',NULL),('e498861ef71c09d2aca63d73d0899869','32','咪咪小虾条,','李','18379576373','江西省 南昌市 青山湖区 ',5.00000000,5,NULL,NULL,'4',NULL,NULL,0.00000000,0,'2022-05-15 15:49:47','2022-05-15 15:55:01','2022-05-15 15:49:47','2022-05-15 15:49:47','2022-05-15 15:49:47','2022-05-15 15:49:47',NULL),('f42c29241d54d733059f13ba48be59d1','31','加力加小虾条丰富叫我呢端午节读取温度,','零售','11111','广东省 佛山市 顺德区 诚德路1号悦然广场3层3015号',2.00000000,2,NULL,NULL,'1',NULL,NULL,0.00000000,0,'2022-05-10 15:40:54',NULL,'2022-05-10 15:40:54','2022-05-10 15:40:54','2022-05-10 15:40:54','2022-05-10 15:40:54',NULL),('fec2d6ed2b4e8b22a79f0d738f2cb03d','25','加力加虾条2,','零售','123','江苏省 南京市 鼓楼区 虎锯路87号(近虎踞南路)',2.00000000,2,NULL,NULL,'1',NULL,NULL,0.00000000,0,'2022-05-09 10:19:36','2022-05-09 10:16:31','2022-05-07 10:52:36','2022-05-07 10:52:36','2022-05-07 10:52:36','2022-05-07 10:52:36',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品主键id',
  `product_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品名称 商品名称',
  `category_id` int NOT NULL COMMENT '分类外键id 分类id',
  `root_category_id` int NOT NULL COMMENT '一级分类外键id 一级分类id，用于优化查询',
  `sold_num` int NOT NULL COMMENT '销量 累计销售',
  `product_status` int NOT NULL COMMENT '默认是1，表示正常状态, -1表示删除, 0下架 默认是1，表示正常状态, -1表示删除, 0下架',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品内容 商品内容',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='商品 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES ('1525388656787906600','测试',51,7,0,1,'测试专用','2022-05-14 16:12:50','2022-05-16 10:55:07'),('1526033918191509500','测试商品2',58,10,0,1,'不发货可下单','2022-05-16 10:56:53','2022-05-16 11:05:08'),('19','虚拟商品，测试使用，勿拍',35,1,111,1,'商品说明，不发货','2022-03-04 09:53:14','2022-05-16 10:55:07'),('3','测试商品',36,1,199,1,'商品说明','2022-04-26 15:58:09','2022-05-16 10:55:07');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_comments`
--

DROP TABLE IF EXISTS `product_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_comments` (
  `comm_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `product_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `product_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `order_item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '订单项(商品快照)ID 可为空',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论用户id 用户名须脱敏',
  `is_anonymous` int DEFAULT NULL COMMENT '是否匿名（1:是，0:否）',
  `comm_type` int DEFAULT NULL COMMENT '评价类型（1好评，0中评，-1差评）',
  `comm_level` int NOT NULL COMMENT '评分（1-5）',
  `comm_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价内容',
  `comm_imgs` varchar(511) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评价晒图(JSON {img1:url1,img2:url2}  )',
  `sepc_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间 可为空',
  `reply_status` int DEFAULT NULL COMMENT '追评（0:未回复，1:已回复）',
  `reply_content` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '回复内容',
  `reply_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  `is_show` int DEFAULT NULL COMMENT '是否显示（1:是，0:否）',
  PRIMARY KEY (`comm_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='商品评价 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_comments`
--

LOCK TABLES `product_comments` WRITE;
/*!40000 ALTER TABLE `product_comments` DISABLE KEYS */;
INSERT INTO `product_comments` VALUES ('004abc6ddc7aadde754653704012a9c9','19','加力加小虾条丰富叫我呢端午节读取温度',NULL,'25',1,NULL,4,'a','{\"img\":[\"04d4c2d9178442cba5fb4e372bba1190.png\",\"c49f9282f05c4a8aa52fb46e2e892f4f.png\"]}','2022-05-05 22:45:36',NULL,NULL,'2022-05-05 22:45:36',NULL),('09ff076988eddd399bfd4d7c898193d3','19','加力加小虾条丰富叫我呢端午节读取温度',NULL,'25',1,NULL,4,'很好哼哼','{\"img\":[\"45a33fae50944cc2a290d8ec63514f1b.jpeg\"]}','2022-04-26 16:57:36',NULL,NULL,'2022-04-26 16:57:36',NULL),('1','3','咪咪虾条','10000001','4',1,1,1,'我买这不是来吃的，就是玩儿','{}','2021-04-27 14:51:10',0,NULL,'2021-04-27 14:51:32',1),('17cb72a9163dd9ce59ead17705e6a257','19','加力加小虾条丰富叫我呢端午节读取温度',NULL,'25',0,NULL,3,'啊','{\"img\":[\"4110771b1b7845d2b1f45d85759c03e7.png\",\"3e8d8b19efe64ab8a6f9964f889e3019.png\"]}','2022-04-21 16:07:32',NULL,NULL,'2022-04-21 16:07:32',NULL),('2','3','咪咪虾条','10000002','5',0,1,1,'nice','{img1:“mmxt2.png”}','2021-04-27 14:53:20',1,'你好我也好','2021-04-27 14:53:37',1),('23c8735c5134099585ac00ebb14a1e3c','20','加力加虾条2',NULL,'25',1,NULL,5,'很好','{\"img\":[\"9217a7dbb2a34cc6ae2e4ae09f186e2a.jpeg\"]}','2022-04-26 16:55:58',NULL,NULL,'2022-04-26 16:55:58',NULL),('2fafa5b03c6dfce88ea2ed600b094e4e','19','加力加小虾条丰富叫我呢端午节读取温度',NULL,'25',0,NULL,3,'啊','{\"img\":[\"4110771b1b7845d2b1f45d85759c03e7.png\",\"3e8d8b19efe64ab8a6f9964f889e3019.png\"]}','2022-04-21 16:07:32',NULL,NULL,'2022-04-21 16:07:32',NULL),('3','3','咪咪虾条','10000003','1',1,1,1,'评价内容3','{}','2021-04-27 09:36:36',0,NULL,NULL,1),('4','3','咪咪虾条','10000004','2',1,1,1,'评价内容3','{}','2021-04-28 09:37:28',0,NULL,NULL,1),('5','3','咪咪虾条','10000005','2',1,0,1,'评价内容5','{}','2021-04-28 09:38:13',0,NULL,NULL,1),('5dee6d02432e401cc1583e2e7a673a28','19','加力加小虾条丰富叫我呢端午节读取温度',NULL,'29',1,NULL,1,'很不错','{\"img\":[\"3f75648d23f74bf891be214f62b20a81.jpg\",\"6b38657f2fad4944a7919f64adeae9ca.jpg\",\"1cbc2a7029ad4ba9b28164c434108e0a.jpeg\"]}','2022-05-10 17:13:29',NULL,NULL,'2022-05-10 17:13:29',NULL),('6','3','咪咪虾条','10000006','3',1,0,1,'评价内容6','{}','2021-04-21 09:39:16',0,NULL,NULL,1),('69f24f0e0417cd2e92fbfb5007fb0e59','1','奥利奥小饼干666',NULL,'29',1,NULL,5,'very good!!!!','{\"img\":[\"4b1bef46f9464a0bb8900732c5cb20ac.svg\",\"bd59df64bc5740fe984e849c75c86ed2.jpg\"]}','2022-05-08 22:27:24',NULL,NULL,'2022-05-08 22:27:24',NULL),('7','3','咪咪虾条','10000007','4',0,-1,1,'评价内容7','{}','2021-04-06 09:40:01',0,NULL,NULL,1),('a233af194df47380c09887e613e714cb','20','加力加虾条2',NULL,'25',0,NULL,1,'好的','{\"img\":[\"9575de0d7f4d4fbb809c724c66a3bb9c.png\",\"c78dbecded9541ea9c3d2c6c9f218fea.png\"]}','2022-05-06 16:32:16',NULL,NULL,'2022-05-06 16:32:16',NULL),('b56a5d6adcbd1b3659d4bfe875a3d516','19','加力加小虾条丰富叫我呢端午节读取温度',NULL,'29',1,NULL,5,'很好','{\"img\":[\"3cd9f91794724b0ab1e8f7b9211f662c.png\",\"8668e0a50c7647f5b47e33a77cdd9266.jpg\"]}','2022-05-08 21:53:42',NULL,NULL,'2022-05-08 21:53:42',NULL),('b984e9b0fa31e9d524897cfe198e7189','20','加力加虾条2',NULL,'25',1,NULL,4,'哼哼还不错','{\"img\":[\"fa7c95038714487b81a4ed7ee1101af8.svg\"]}','2022-04-26 16:59:11',NULL,NULL,'2022-04-26 16:59:11',NULL),('c850ea324465e1c231b03221e8df37e3','20','加力加虾条2',NULL,'25',1,NULL,2,'一般般。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。','{\"img\":[\"97fb2a5960e74d5093a8f554360acff3.jpeg\"]}','2022-04-26 15:12:05',NULL,NULL,'2022-04-26 15:12:05',NULL);
/*!40000 ALTER TABLE `product_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_img`
--

DROP TABLE IF EXISTS `product_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_img` (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片主键',
  `item_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品外键id 商品外键id',
  `url` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片地址 图片地址',
  `sort` int NOT NULL COMMENT '顺序 图片顺序，从小到大',
  `is_main` int NOT NULL DEFAULT '0' COMMENT '是否主图 是否主图，1：是，0：否',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='商品图片 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_img`
--

LOCK TABLES `product_img` WRITE;
/*!40000 ALTER TABLE `product_img` DISABLE KEYS */;
INSERT INTO `product_img` VALUES ('1525388743748411393','1525388656787906600','3272b4b4d29e43a8ac3bfdd64529228d.webp',1,1,'2022-05-14 16:13:11',NULL),('1525388743786160129','1525388656787906600','5eda005282394ad2ae4102e9aa428ea2.webp',2,0,'2022-05-14 16:13:11',NULL),('1526034117802631170','1526033918191509500','d4589d099e334bc08cf877afe09b47a8.webp',1,1,'2022-05-16 10:57:40',NULL),('1526034117823602689','1526033918191509500','27dcc332de5d4f57bf4a7c57a19d26f4.webp',2,0,'2022-05-16 10:57:40',NULL),('19','19','wwxb_1.png',1,1,'2021-04-26 11:11:11','2022-05-15 15:14:22'),('3','3','hfb_1.png',1,1,'2021-04-24 15:30:09','2022-05-15 15:14:22');
/*!40000 ALTER TABLE `product_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_params`
--

DROP TABLE IF EXISTS `product_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_params` (
  `param_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品参数id',
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `product_place` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '产地 产地，例：中国江苏',
  `foot_period` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '保质期 保质期，例：180天',
  `brand` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '品牌名 品牌名，例：三只大灰狼',
  `factory_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生产厂名 生产厂名，例：大灰狼工厂',
  `factory_address` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '生产厂址 生产厂址，例：大灰狼生产基地',
  `packaging_method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '包装方式 包装方式，例：袋装',
  `weight` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规格重量 规格重量，例：35g',
  `storage_method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '存储方法 存储方法，例：常温5~25°',
  `eat_method` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '食用方式 食用方式，例：开袋即食',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='商品参数 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_params`
--

LOCK TABLES `product_params` WRITE;
/*!40000 ALTER TABLE `product_params` DISABLE KEYS */;
INSERT INTO `product_params` VALUES ('1','3','中国湖北武汉','12个月','咪咪','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35'),('1525388743492558850','1525388656787906600','测试','测试','测试','测试','测试','测试','测试','测试','测试','2022-05-14 16:13:11',NULL),('1526034117701967874','1526033918191509500','测','试','专','用','可','下单','不','发','货','2022-05-16 10:57:40',NULL),('2','19','中国湖北武汉','12个月','卫龙','贤磊咪咪虾条有限责任公司','湖北武汉江夏区光谷大道77号','袋装','50g','常温保存','开袋即食','2021-04-27 11:53:20','2021-04-27 11:53:35');
/*!40000 ALTER TABLE `product_params` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_sku`
--

DROP TABLE IF EXISTS `product_sku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_sku` (
  `sku_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'sku编号',
  `product_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品id',
  `sku_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'sku名称',
  `sku_img` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'sku图片',
  `untitled` varchar(400) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '属性组合（格式是p1:v1;p2:v2）',
  `original_price` int NOT NULL COMMENT '原价',
  `sell_price` int NOT NULL COMMENT '销售价格',
  `discounts` decimal(4,2) NOT NULL COMMENT '折扣力度',
  `stock` int NOT NULL COMMENT '库存',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int DEFAULT NULL COMMENT 'sku状态(1启用，0禁用，-1删除)',
  PRIMARY KEY (`sku_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_sku`
--

LOCK TABLES `product_sku` WRITE;
/*!40000 ALTER TABLE `product_sku` DISABLE KEYS */;
INSERT INTO `product_sku` VALUES ('1525388853886640129','1525388656787906600','测试',NULL,'{口味:[\"原味\",\"烧烤\"],包装:[\"新包装\",\"老包装\"]}',2,1,1.00,9,'2022-05-14 16:13:37','2022-05-15 15:23:16',NULL),('1526035034706845698','1526033918191509500','测试套餐1',NULL,'{skuname1:[\"sku1\",\"sku2\"],skuname2:[\"sku3\",\"sku4\"]}',2,1,1.00,999,'2022-05-16 11:01:19',NULL,NULL),('1526035273685704706','1526033918191509500','测试套餐2',NULL,'{skuname3:[\"sku1\",\"sku2\"],skuname4:[\"sku3\",\"sku4\"],skuname5:[\"sku5\",\"sku6\"]}',3,2,1.00,999,'2022-05-16 11:02:16',NULL,NULL),('2','3','咪咪虾条 6连包','mmxt_1.png','{口味:[\"原味\",\"烧烤\"],包装:[\"新包装\",\"老包装\"]}',6,5,0.60,142,'2021-04-26 14:48:21','2022-05-15 15:49:47',1),('3','3','咪咪虾条 整箱50包','mmxt_1.png','{口味:[\"原味\",\"烧烤\",\"孜然\"]}',50,40,0.50,37,'2021-04-26 14:49:20','2021-04-26 14:49:23',1),('5','19','加力加 100g','jlj_1.png','{口味:[\"原味\",\"微辣\",\"BT\"]}',3,2,0.50,979,'2021-05-10 09:56:09','2022-05-10 17:09:01',1);
/*!40000 ALTER TABLE `product_sku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shopping_cart`
--

DROP TABLE IF EXISTS `shopping_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shopping_cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `product_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品ID',
  `sku_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'skuID',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `cart_num` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '购物车商品数量',
  `cart_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加购物车时间',
  `product_price` decimal(32,8) DEFAULT NULL COMMENT '添加购物车时商品价格',
  `sku_props` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '选择的套餐的属性',
  PRIMARY KEY (`cart_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='购物车 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shopping_cart`
--

LOCK TABLES `shopping_cart` WRITE;
/*!40000 ALTER TABLE `shopping_cart` DISABLE KEYS */;
INSERT INTO `shopping_cart` VALUES (88,'3','2','30','1','2022-05-15 15:37:27',5.00000000,'口味:烧烤;包装:老包装;'),(89,'1525388656787906600','1525388853886640129','29','5','2022-05-15 15:37:48',1.00000000,'口味:烧烤;包装:老包装;'),(90,'19','5','29','1','2022-05-15 15:37:54',2.00000000,'口味:原味;包装:老包装;'),(91,'3','2','30','1','2022-05-15 15:41:18',5.00000000,'口味:烧烤;包装:老包装;'),(92,'3','2','30','1','2022-05-15 15:43:55',5.00000000,''),(94,'1526033918191509500','1526035034706845698','33','1','2022-05-16 13:00:41',1.00000000,'skuname1:sku2;skuname2:sku4;'),(95,'1525388656787906600','1525388853886640129','29','1','2022-05-19 10:51:18',1.00000000,'口味:烧烤;包装:老包装;'),(96,'1525388656787906600','1525388853886640129','29','1','2022-05-19 10:51:19',1.00000000,'口味:原味;包装:老包装;'),(97,'19','5','29','1','2022-05-19 10:51:24',2.00000000,'口味:原味;包装:老包装;');
/*!40000 ALTER TABLE `shopping_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_addr`
--

DROP TABLE IF EXISTS `user_addr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_addr` (
  `addr_id` int NOT NULL AUTO_INCREMENT COMMENT '地址主键id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户ID',
  `receiver_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人姓名',
  `receiver_mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收件人手机号',
  `province` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '省份',
  `city` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '城市',
  `area` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '区县',
  `addr` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '详细地址',
  `post_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮编',
  `status` int DEFAULT NULL COMMENT '状态,1正常，0无效',
  `common_addr` int DEFAULT NULL COMMENT '是否默认地址 1是 1:是  0:否',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`addr_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='用户地址 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_addr`
--

LOCK TABLES `user_addr` WRITE;
/*!40000 ALTER TABLE `user_addr` DISABLE KEYS */;
INSERT INTO `user_addr` VALUES (1,'1','张三','13030303300','湖北','武汉','江夏区','光谷大道77号','421000',1,0,'2021-04-30 09:33:30','2022-05-05 21:16:30'),(2,'1','小丽','13131313311','湖北','宜昌','夷陵区','巴拉巴拉','410000',1,0,'2021-04-30 09:34:41','2021-04-30 09:34:44'),(3,'6','韩梅梅','13232323322','湖南','长沙','岳麓区','淅沥淅沥','430000',1,0,'2021-04-30 09:35:49','2022-05-05 21:16:30'),(26,'31','零售','11111','广东省','佛山市','顺德区','诚德路1号悦然广场3层3015号','1111',1,0,'2022-05-10 15:40:34',NULL),(27,'29','123','123','江西省','南昌市','红谷滩区','学府大道999号','123',1,0,'2022-05-10 17:00:31','2022-05-10 17:01:00'),(28,'29','123','123','湖南省','长沙市','天心区','学府大道999号','123',1,0,'2022-05-10 17:00:50','2022-05-15 15:40:22'),(29,'32','李','18379576373','江西省','南昌市','青山湖区','','',1,1,'2022-05-15 15:40:20','2022-05-15 15:40:22');
/*!40000 ALTER TABLE `user_addr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_login_history`
--

DROP TABLE IF EXISTS `user_login_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_login_history` (
  `ID` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `AREA` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地区',
  `COUNTRY` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '国家',
  `USER_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户id',
  `IP` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'IP',
  `LOGIN_TIME` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='登录历史表 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_login_history`
--

LOCK TABLES `user_login_history` WRITE;
/*!40000 ALTER TABLE `user_login_history` DISABLE KEYS */;
INSERT INTO `user_login_history` VALUES (16,'南昌市','中国',NULL,'','2022-05-08 22:36:35'),(17,'南昌市','中国','29','','2022-05-09 20:02:51'),(18,'南昌市','江西省','29','39.161.242.35','2022-05-10 12:35:12'),(19,'南昌市','江西省','29','39.161.242.35','2022-05-10 16:54:14'),(20,'南昌市','江西省','29','39.161.242.35','2022-05-10 16:57:23'),(21,'南昌市','江西省','29','39.161.242.35','2022-05-10 16:58:04'),(22,'南昌市','江西省','29','39.161.242.35','2022-05-10 17:08:19'),(23,'南昌市','江西省','29','39.161.242.35','2022-05-10 17:20:44'),(24,'南昌市','江西省','29','39.161.242.35','2022-05-10 17:48:23'),(25,'南昌市','江西省','29','39.161.242.62','2022-05-12 22:51:53'),(26,'南昌市','江西省','29','39.161.242.62','2022-05-14 16:18:08'),(27,'南昌市','江西省','32','223.104.10.224','2022-05-15 15:34:57'),(28,'南昌市','江西省','30','39.161.242.49','2022-05-15 15:37:24'),(29,'广州市','广东省','33','116.21.3.104','2022-05-16 12:59:34'),(30,'南昌市','江西省','30','39.161.242.53','2022-05-19 10:51:03'),(31,'南昌市','江西省','29','39.161.242.53','2022-05-19 10:51:10'),(32,'南昌市','江西省','29','39.161.242.53','2022-05-19 15:23:43'),(33,'南昌市','江西省','29','39.161.242.42','2022-05-20 15:26:58'),(34,'南昌市','江西省','29','39.161.242.42','2022-05-20 15:29:18'),(35,'南昌市','江西省','31','39.161.242.42','2022-05-20 16:33:52'),(36,'南昌市','江西省','29','39.161.242.62','2022-05-21 11:00:33');
/*!40000 ALTER TABLE `user_login_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '主键id 用户id',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名 用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码 密码',
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称 昵称',
  `realname` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '真实姓名 真实姓名',
  `user_img` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像 头像',
  `user_mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号 手机号',
  `user_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱地址 邮箱地址',
  `user_sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '性别 M(男) or F(女)',
  `user_birth` date DEFAULT NULL COMMENT '生日 生日',
  `user_regtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间 创建时间',
  `user_modtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间 更新时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=COMPACT COMMENT='用户 ';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (23,'王五','202cb962ac59075b964b07152d234b70',NULL,NULL,'default.png',NULL,NULL,NULL,NULL,'2022-04-07 21:43:41','2022-05-09 22:39:48'),(24,'1234','202cb962ac59075b964b07152d234b70',NULL,NULL,'default.png',NULL,NULL,NULL,NULL,'2022-04-07 21:43:55','2022-05-09 22:39:47'),(25,'12345','202cb962ac59075b964b07152d234b70','小李',NULL,'default.png',NULL,NULL,'男',NULL,'2022-04-08 14:56:30','2022-05-09 22:39:47'),(26,'xyg','202cb962ac59075b964b07152d234b70',NULL,NULL,'default.png',NULL,NULL,NULL,NULL,'2022-04-10 10:04:44','2022-05-09 22:39:47'),(27,'123456','202cb962ac59075b964b07152d234b70',NULL,NULL,'default.png',NULL,NULL,NULL,NULL,'2022-04-10 19:11:09','2022-05-09 22:39:47'),(29,'李四','202cb962ac59075b964b07152d234b70',NULL,NULL,'d3677f272eac41e3956150ec4af8e902.jpg',NULL,NULL,'男',NULL,'2022-05-08 21:04:06','2022-05-09 22:39:47'),(30,'张三','202cb962ac59075b964b07152d234b70',NULL,NULL,'default.png',NULL,NULL,NULL,NULL,'2022-05-09 22:51:05',NULL),(31,'小李','202cb962ac59075b964b07152d234b70',NULL,NULL,'default.png',NULL,NULL,NULL,NULL,'2022-05-10 13:05:52',NULL),(32,'yaoyaoolzy','edfff83c0cdd4caab246e61f830b6456',NULL,NULL,'default.png',NULL,NULL,NULL,NULL,'2022-05-15 15:34:41',NULL),(33,'lzp','f68ac95bafbb929143cfb3406f94fa54',NULL,NULL,'default.png',NULL,NULL,NULL,NULL,'2022-05-16 12:59:22',NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-22 14:01:53
