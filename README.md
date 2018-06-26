Example full-stack application to log your daily activity, made with **Angular(6) & Spring Boot** You can use this project to build on top of it and create something bigger.

 - demonstrates how to bundle Angular with Spring Boot backend and build everything
 -  Uses h2/mysql with CRUD functionality. 
 - demonstrates token based auth with JWT.


Demo here: https://daily-logger-spring-boot.herokuapp.com/register
Just register with some throw-a-way username/password or use the combination "user/password".

Installation:
---
- cd frontend
- npm install
- npm run build
- cd ..
- mvn package
- java -jar target/dailylogger-0.0.1-SNAPSHOT.jar

---
If you are interested in **Node + Angular**, here is the same app I build with **node** backend: https://github.com/kle-pra/daily-logger-node