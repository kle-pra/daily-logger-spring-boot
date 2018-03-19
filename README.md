Log your daily activity with Angular 5 & Spring Boot - Full stack application example. Uses h2/mysql with CRUD functionality. You can use this project to build on top of it and create something bigger. It also demonstrates token based auth with JWT.

When build, frontend files are copied to over to static folder.


Installation:
---

- cd frontend
- npm install
- npm run build
- cd ..
- mvn package
- java -jar target/dailylogger-0.0.1-SNAPSHOT.jar


Demo here: https://daily-logger-spring-boot.herokuapp.com/register
Just register with some throw-a-way username/password or use the combination "user/password".

For comparision, here is the same app I build with node backend: https://github.com/kle-pra/daily-logger-node