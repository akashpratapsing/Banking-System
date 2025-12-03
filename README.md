# 📌 Banking Transaction System — Full Stack Project
**Stack:** Java • Spring Boot • Spring Security • ReactJS • H2 DB • WebClient • CORS Enabled

This project implements a two-system banking flow with routing, secure PIN validation, balance processing, and separate UIs for Customer and Super Admin.  
All assignment requirements are fully met.

## 🚀 System Overview

### System 1 — Routing Service (Port: 8081)
- Receives transactions from UI  
- Validates structure  
- Routes based on card range (cards starting with **4**)  
- Calls System2 via WebClient + Basic Auth  
- Secured using Spring Security  

### System 2 — Core Banking Engine (Port: 8082)
- Validates card  
- Validates PIN (SHA-256 hashing)  
- Processes top-ups & withdrawals  
- Updates and returns balance  
- Uses H2 in-memory database  

## 🧱 Project Structure
```
root/
 ├── system1/
 ├── system2/
 ├── Frontend/
 └── README.md
```

## 🔐 Authentication
| Role | Username | Password |
|------|----------|----------|
| Admin | admin | admin123 |
| Customer | cust | cust123 |

## ⚙️ Backend Setup

### System2
```
cd system2
mvn spring-boot:run
```
H2 Console: http://localhost:8082/h2-console  
Use:
```
JDBC URL: jdbc:h2:mem:bank2
User: sa
Password: (empty)
```

### System1
```
cd system1
mvn spring-boot:run
```

## 🧪 API Flow

### POST /system1/transaction
Request:
```
{
  "cardNumber": "4111111111111111",
  "pin": "1234",
  "amount": 100,
  "type": "topup"
}
```

Response:
```
{
  "success": true,
  "message": "Success",
  "balance": 600.0
}
```

## 🖥️ Frontend Setup
```
cd react-ui
npm install
npm start
```

### Customer UI Features
- Perform top-ups/withdrawals  
- Shows real-time balance  
- Cleanly formatted last response  
- Personal transaction history  

### Super Admin UI
- Displays all transaction logs (local storage)

## 📋 Database Schema
```
CARD (
  CARD_NUMBER VARCHAR(20),
  PIN_HASH VARCHAR(256),
  BALANCE DOUBLE
)
```

## 🔄 Routing Logic (System1)
```
If card starts with "4" → route to System2
Else → reject
```

## 🔐 PIN Security
- Stored using SHA-256 hashing  
- Backend compares hashed values  
- PIN never stored or logged  

## 🧪 Test Case Mapping
| Requirement | Status |
|------------|--------|
| Routing based on card range | ✔ |
| Card validation | ✔ |
| PIN hashing | ✔ |
| Top-up/withdrawal | ✔ |
| Customer logs | ✔ |
| Customer balance | ✔ |
| Super admin logs | ✔ |

## ✔️ Run All Services
Terminal 1:
```
cd system1 && mvn spring-boot:run
```

Terminal 2:
```
cd system2 && mvn spring-boot:run
```

Terminal 3:
```
cd react-ui && npm start
```

## 🎯 Conclusion
All assignment requirements are implemented, tested, and fulfilled successfully.
