# SpringBoot Community项目实战

## 资料
[Spring 文档](https://spring.io/guides)

[Spring Web 文档](https://spring.io/guides/gs/serving-web-content)

[es 社区](https://elasticsearch.cn/explore)

[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)

[Bootstrap](https://v3.bootcss.com/getting-started/)

[Github OAuth 文档](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)

[Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html)

## 工具
[Git](https://git-scm.com/download)

[Visual-Paradigm](https://www.visual-paradigm.com)

[FlyWay](https://flywaydb.org)

[Lombok](https://www.projectlombok.org)

[]()

## 脚本
```sql
CREATE TABLE USER
(
  ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
  ACCOUNT_ID VARCHAR(100),
  NAME VARCHAR (50),
  TOKEN CHAR(36),
  GMT_CREATE BIGINT,
  GMT_MODIFY BIGINT
);

```
```bash
mvn flyway:migrate

```

## 部署

- git
- JDK
- Maven
- MySQL

```bash
mvn clean compile package
#打包文件
//运行命令
java -jar -Dspring.profiles.active=production target/community-0.0.1-SNAPSHOT.jar
```

