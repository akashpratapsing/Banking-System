
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './Login.css';

export default function Login(){
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const { login } = useAuth();
  const nav = useNavigate();
  const [err, setErr] = useState(null);

  const submit = (e) => {
    e.preventDefault();
    if (!username || !password) { setErr('Enter username+password'); return; }
    const u = login(username.trim(), password);
    nav(u.role === 'ADMIN' ? '/admin' : '/customer');
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={submit} className="login-form">
        <div><input placeholder="username" value={username} onChange={e=>setUsername(e.target.value)} /></div>
        <div><input placeholder="password" type="password" value={password} onChange={e=>setPassword(e.target.value)} /></div>
        <div><button type="submit">Login</button></div>
      </form>
      {err && <div className="error-message">{err}</div>}
      <div className="creds-info">Default creds: admin/admin123 and cust/cust123</div>
    </div>
  );
}
