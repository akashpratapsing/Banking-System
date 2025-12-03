import React, { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthContext';
import { sendTransaction } from '../services/api';
import './Dashboard.css';

export default function CustomerDashboard() {
  const { user } = useAuth();

  const [cardNumber, setCardNumber] = useState('');
  const [pin, setPin] = useState('');
  const [amount, setAmount] = useState('');
  const [type, setType] = useState('topup');

  const [balance, setBalance] = useState(null);               // ✅ NEW
  const [history, setHistory] = useState(() => {
    try { return JSON.parse(localStorage.getItem('tx_history') || '[]'); }
    catch { return []; }
  });

  const [lastResp, setLastResp] = useState(null);

  useEffect(() => {
    localStorage.setItem('tx_history', JSON.stringify(history));
  }, [history]);

  const submit = async (e) => {
    e.preventDefault();

    if (!cardNumber || !pin || !amount) {
      alert("Please fill all fields");
      return;
    }

    const tx = {
      cardNumber: cardNumber.trim(),
      pin: pin.trim(),
      amount: parseFloat(amount),
      type
    };

    const res = await sendTransaction(user, tx);
    setLastResp(res);

    // ✅ update balance if backend returns one
    if (res && typeof res.balance === "number") {
      setBalance(res.balance);
    }

    setHistory([
      { ...tx, response: res, timestamp: Date.now(), user: user.username },
      ...history
    ]);
  };

  return (
    <div className="dashboard-container">
      <h2>Customer Dashboard</h2>

      {/* ✅ Balance display */}
      {balance !== null && (
        <div className="balance-box">
          <strong>Current Balance: </strong> ₹{balance}
        </div>
      )}

      <form onSubmit={submit} className="transaction-form">
        <div>
          <label>Card Number</label>
          <input
            value={cardNumber}
            onChange={(e) => setCardNumber(e.target.value)}
            placeholder="Enter your card number"
          />
        </div>

        <div>
          <label>PIN</label>
          <input
            type="password"
            value={pin}
            onChange={(e) => setPin(e.target.value)}
            placeholder="Enter PIN"
          />
        </div>

        <div>
          <label>Amount</label>
          <input
            type="number"
            min="1"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            placeholder="Enter amount"
          />
        </div>

        <div>
          <label>Type</label>
          <select value={type} onChange={(e) => setType(e.target.value)}>
            <option value="topup">Top-up</option>
            <option value="withdraw">Withdraw</option>
          </select>
        </div>

        <button type="submit">Send Transaction</button>
      </form>


      {lastResp && (
  <div className="last-response-box">
    <h3>Last Response</h3>

    <div className={`resp-status ${lastResp.success ? "resp-ok" : "resp-error"}`}>
      {lastResp.success ? "✔ Transaction Successful" : "✖ Transaction Failed"}
    </div>

    <div className="resp-message">
      <strong>Message:</strong> {lastResp.message}
    </div>

    {typeof lastResp.balance === "number" && (
      <div className="resp-balance">
        <strong>Updated Balance:</strong> ₹{lastResp.balance}
      </div>
    )}
  </div>
)}


      <div>
        <h3>Your Local Transaction History</h3>
        <table className="dashboard-table">
          <thead>
            <tr>
              <th>Time</th>
              <th>Type</th>
              <th>Amount</th>
              <th>Response</th>
            </tr>
          </thead>
          <tbody>
            {history
              .filter((h) => h.user === user.username)
              .map((h, idx) => (
                <tr key={idx}>
                  <td>{new Date(h.timestamp).toLocaleString()}</td>
                  <td>{h.type}</td>
                  <td>{h.amount}</td>
                  <td>{h.response ? h.response.message : '-'}</td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
