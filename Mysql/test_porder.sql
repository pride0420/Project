-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `porder`
--

DROP TABLE IF EXISTS `porder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `porder` (
  `porderId` int NOT NULL AUTO_INCREMENT,
  `product` varchar(45) NOT NULL,
  `amount` int NOT NULL,
  `imgNo` varchar(45) NOT NULL,
  `mode` varchar(45) NOT NULL,
  `explanation` varchar(100) NOT NULL,
  `items` varchar(45) NOT NULL,
  PRIMARY KEY (`porderId`),
  UNIQUE KEY `imgNo_UNIQUE` (`imgNo`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `porder`
--

LOCK TABLES `porder` WRITE;
/*!40000 ALTER TABLE `porder` DISABLE KEYS */;
INSERT INTO `porder` VALUES (1,'Iphone',3000,'1','15pro 64G','6.7 吋或 6.1 吋 超 Retina XDR 顯示器 註腳 鈦金屬搭配霧面質感玻璃機背 鈴聲/靜音開關 動態島功能 靈活多變的 iPhone 新玩法 ','手機'),(2,'Iphone',3000,'2','15 64G','6.7 吋或 6.1 吋 超 Retina XDR 顯示器 註腳 鋁金屬搭配玻璃機背 鈴聲/靜音開關','手機'),(3,'Iphone',3000,'3','14 64G','6.7 吋或 6.1 吋 超 Retina XDR 顯示器 註腳 鋁金屬搭配玻璃機背 鈴聲/靜音開關 動態島功能 靈活多變的 iPhone 新玩法','手機'),(4,'Iphone',3000,'4','SE','4.7 吋 Retina HD 顯示器 鋁金屬與玻璃 鈴聲/靜音開關','手機'),(5,'Iphone',3000,'5','13pro','6.1 吋 超 Retina XDR 顯示器1 ProMotion自動適應更新頻率技術 不鏽鋼搭配霧面質感玻璃機背 鈴聲/靜音開關','手機'),(6,'Samsung',2500,'6','Galaxy S24','6.2吋 主螢幕解析度2340 x 1080 (FHD+) 主相機解析度 (複合)5000萬畫素 + 1000萬畫素 + 1200萬畫素','手機'),(7,'Samsung',2500,'7','Galaxy S24 Ultra','6.8吋 主螢幕解析度3120 x 1440 (Quad HD+) 主相機解析度 (複合)2億畫素 + 5000萬畫素 + 1200萬畫素 + 1000萬畫素','手機'),(8,'Samsung',2500,'8','Galaxy S24+','6.7吋 主螢幕解析度3120 x 1440 (Quad HD+) 主相機解析度 (複合)5000萬畫素 + 1000萬畫素 + 1200萬畫素','手機'),(9,'Oppo',2000,'9','Find N2 Flip','6.8吋 面板材質/螢幕類型AMOLED 處理器MediaTek Dimensity 9000+','手機'),(10,'Oppo',2000,'10','Find X5 Pro','6.7吋 面板材質/螢幕類型AMOLED 處理器最高3.0Hz，八核心處理器','手機'),(11,'Oppo',2000,'11','Find X3 Pro','6.7吋 面板材質/螢幕類型AMOLED (Soft/LTPO) 處理器Qualcomm Snapdragon 888處理器','手機'),(12,'小米',2000,'12','Xiaomi 13T Pro','6.67 吋 處理器 ：MediaTek Dimensity 9200+ 防潑水、防水、防塵 ：IP 68','手機'),(13,'小米',2000,'13','Xiaomi 13T','6.67 吋 處理器 ：MediaTek Dimensity 8200-Ultra 防潑水、防水、防塵 ：IP 68','手機'),(14,'小米',2000,'14','Note 13','6.67 吋 處理器 ：Snapdragon 685  防潑水、防水、防塵 ：IP 54','手機'),(15,'MSI',4000,'15','GE78 HX 13V','GE78 HX 13V','電腦'),(16,'asus',3000,'16','Vivobook 15 (X1502)','Vivobook 15 (X1502)','電腦'),(17,'acer',2000,'17','Aspire Vero','Aspire Vero','電腦'),(18,'PS5',3000,'18','超讚','超讚','電動'),(19,'Xbox',3500,'19','Serice X','Serice X','電動'),(20,'switch',2500,'20','Nintendo Switch™（OLED款式）主機［HEG-001］','Nintendo Switch™（OLED款式）主機［HEG-001］','電動'),(21,'滑鼠',500,'21','G502 HERO','G502 HERO','其他'),(22,'鍵盤',250,'22','V65 巨浪 65% RGB 英文機械式鍵盤','V65 巨浪 65% RGB 英文機械式鍵盤','其他'),(23,'耳機',100,'23','無線藍牙頭戴式耳機 ( BTE-3860)','無線藍牙頭戴式耳機 ( BTE-3860)','其他');
/*!40000 ALTER TABLE `porder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-17 22:16:02
