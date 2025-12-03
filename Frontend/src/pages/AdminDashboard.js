
import React, { useState } from 'react';
import './Dashboard.css';

export default function AdminDashboard(){
  const [history] = useState(() => {
    try { return JSON.parse(localStorage.getItem('tx_history')||'[]'); } catch { return []; }
  });

  return (
    <div className="dashboard-container">
      <h2>Super Admin Dashboard</h2>
      <div>
        <h3>All Transactions</h3>
        <table className="dashboard-table">
          <thead><tr><th>Time</th><th>User</th><th>Type</th><th>Amount</th><th>Card</th><th>Result</th></tr></thead>
          <tbody>
            {history.map((h,idx)=> (
              <tr key={idx}>
                <td>{new Date(h.timestamp).toLocaleString()}</td>
                <td>{h.user}</td>
                <td>{h.type}</td>
                <td>{h.amount}</td>
                <td>{h.cardNumber}</td>
                <td>{h.response ? h.response.message : '-'}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
