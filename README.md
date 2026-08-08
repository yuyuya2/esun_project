# 社群媒體系統
使用Vue.js 做前端介面，Spring Boot 做後端，並使用 MySQL 儲存資料。

## 資料庫
資料庫名稱為 `social_media_db`
先執行 DB 資料夾中的 `ddl.sql` 與 `dml.sql`

## 資料庫連線設定
backend/backend/src/main/resources/application.properties

若 MySQL 有設定密碼，請修改成自己密碼：
spring.datasource.password=自己的MySQL密碼

## 後端打開

執行環境需求：JDK 17 以上

### 方法一：VS Code
backend/backend/src/main/java/com/esun/backend/BackendApplication.java，點選RUN執行

### 方法二：Terminal
進入後端：
cd backend/backend

啟動 Spring Boot：
.\mvnw.cmd spring-boot:run

後端預設使用：
http://localhost:8080

## 前端
進入前端資料夾：
cd frontend

如第一次執行請安裝套件：
npm install

開啟前端：
npm run dev
