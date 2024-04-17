# 使用 Node.js 官方镜像作为基础
FROM node:latest AS builder

# 设置工作目录
WORKDIR /app

# 将 package.json 和 package-lock.json 复制到容器中
COPY package*.json ./

# 安装依赖
RUN npm install

# 将整个应用程序复制到容器中
COPY . .

# 构建应用程序
RUN npm run build

# 使用 Nginx 官方镜像作为最终镜像
FROM nginx:latest

# 将构建后的静态资源从上一个阶段复制到 Nginx 静态文件夹中
COPY --from=builder /app/build /usr/share/nginx/html

# Nginx 默认监听 80 端口
EXPOSE 80