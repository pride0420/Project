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
-- Temporary view structure for view `commentview`
--

DROP TABLE IF EXISTS `commentview`;
/*!50001 DROP VIEW IF EXISTS `commentview`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `commentview` AS SELECT 
 1 AS `commentId`,
 1 AS `memberId`,
 1 AS `name`,
 1 AS `chatId`,
 1 AS `comment`,
 1 AS `firsttime`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `likechat`
--

DROP TABLE IF EXISTS `likechat`;
/*!50001 DROP VIEW IF EXISTS `likechat`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `likechat` AS SELECT 
 1 AS `chatId`,
 1 AS `memberId`,
 1 AS `name`,
 1 AS `content`,
 1 AS `firsttime`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `car`
--

DROP TABLE IF EXISTS `car`;
/*!50001 DROP VIEW IF EXISTS `car`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `car` AS SELECT 
 1 AS `id`,
 1 AS `memberId`,
 1 AS `porderId`,
 1 AS `username`,
 1 AS `name`,
 1 AS `product`,
 1 AS `amount`,
 1 AS `num`,
 1 AS `sum`,
 1 AS `mode`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `chatview`
--

DROP TABLE IF EXISTS `chatview`;
/*!50001 DROP VIEW IF EXISTS `chatview`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `chatview` AS SELECT 
 1 AS `chatId`,
 1 AS `memberId`,
 1 AS `name`,
 1 AS `content`,
 1 AS `firsttime`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `commentview`
--

/*!50001 DROP VIEW IF EXISTS `commentview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `commentview` AS select `comment`.`commentId` AS `commentId`,`member`.`memberId` AS `memberId`,`member`.`name` AS `name`,`comment`.`chatId` AS `chatId`,`comment`.`comment` AS `comment`,`comment`.`firsttime` AS `firsttime` from (`comment` join `member`) where (`member`.`memberId` = `comment`.`memberId`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `likechat`
--

/*!50001 DROP VIEW IF EXISTS `likechat`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `likechat` AS select `chatview`.`chatId` AS `chatId`,`goodchat`.`memberId` AS `memberId`,`chatview`.`name` AS `name`,`chatview`.`content` AS `content`,`chatview`.`firsttime` AS `firsttime` from (`chatview` join `goodchat`) where (`chatview`.`chatId` = `goodchat`.`chatId`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `car`
--

/*!50001 DROP VIEW IF EXISTS `car`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `car` AS select `shopcar`.`id` AS `id`,`member`.`memberId` AS `memberId`,`porder`.`porderId` AS `porderId`,`member`.`username` AS `username`,`member`.`name` AS `name`,`porder`.`product` AS `product`,`porder`.`amount` AS `amount`,`shopcar`.`num` AS `num`,`shopcar`.`sum` AS `sum`,`porder`.`mode` AS `mode` from ((`shopcar` join `member`) join `porder`) where ((`shopcar`.`memberId` = `member`.`memberId`) and (`shopcar`.`porderId` = `porder`.`porderId`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `chatview`
--

/*!50001 DROP VIEW IF EXISTS `chatview`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `chatview` AS select `chat`.`chatId` AS `chatId`,`chat`.`memberId` AS `memberId`,`member`.`name` AS `name`,`chat`.`content` AS `content`,`chat`.`firsttime` AS `firsttime` from (`member` join `chat`) where (`member`.`memberId` = `chat`.`memberId`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-17 22:16:03
