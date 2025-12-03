
Banking POC Frontend (React)
---------------------------

This is a minimal React frontend to interact with the backend POC (system1 & system2).
It supports:
 - Basic HTTP Basic login (credentials are sent with each request)
 - Customer dashboard: send top-up / withdraw transactions to System1
 - Admin dashboard: view the same client-side transaction log
 - Local transaction history (stored in localStorage) — backend logging endpoints were not provided in the backend zip, so frontend keeps a client-side log.

Run:
  npm install
  npm start

Notes:
 - The app expects System1 at http://localhost:8081/system1/transaction
 - Uses HTTP Basic auth credentials for the backend; default users in backend: admin/admin123, cust/cust123
