
Backend POC (System1 + System2)
-----------------------------

Two Spring Boot apps included:
 - system1 (port 8081): validation + routing
 - system2 (port 8082): card validation, PIN hashing, balance update

Quick run (requires Java 17+ and Maven):

1) Start System2 (core banking)
   cd system2
   mvn spring-boot:run

   System2 pre-populated cards (data.sql):
     - 4111111111111111 PIN=1234 balance=500.0
     - 4111222233334444 PIN=1234 balance=100.0

2) Start System1 (router)
   cd system1
   mvn spring-boot:run

APIs:
 - POST http://localhost:8081/system1/transaction
   body: { "cardNumber":"4111111111111111", "pin":"1234", "amount":100, "type":"topup" }

Notes:
 - PINs are hashed using SHA-256 in system2 (data.sql stores the hash).
 - Basic auth users: admin/admin123 and cust/cust123 (http basic).
