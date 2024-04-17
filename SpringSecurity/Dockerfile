# 使用 Maven 的 official image 作为基础镜像
FROM maven:3.8.4-openjdk-17-slim AS build

# 设置工作目录
WORKDIR /app

# 复制 Maven 配置文件
COPY pom.xml .

# 下载依赖并预先下载源代码以进行缓存
RUN mvn dependency:go-offline

# 复制整个项目源代码到容器中
COPY src ./src

# 编译项目并打包成可执行的 JAR 文件
RUN mvn package -X

# 使用 OpenJDK 作为基础镜像
FROM openjdk:17

# 设置工作目录
WORKDIR /app

# 从之前构建阶段的镜像中复制编译好的 JAR 文件到当前镜像
COPY --from=build /app/target/SpringSecurity-0.0.1-SNAPSHOT.jar ./SpringSecurity-0.0.1-SNAPSHOT.jar

# 暴露应用程序运行的端口
EXPOSE 8090


# 定义启动命令
CMD ["java", "-jar", "SpringSecurity-0.0.1-SNAPSHOT.jar"]
