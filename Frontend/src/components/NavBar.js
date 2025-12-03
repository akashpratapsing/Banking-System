
import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';
import './NavBar.css';

export default function NavBar(){
  const { user, logout } = useAuth();
  return (
    <nav className="navbar">
      <Link to="/" className="navbar-link">Home</Link>
      {user && user.role==='ADMIN' && <Link to="/admin" className="navbar-link">Admin</Link>}
      {user && user.role==='CUSTOMER' && <Link to="/customer" className="navbar-link">Customer</Link>}
      {user ? (
        <span className="user-info">
          <strong className="username">{user.username}</strong>
          <button onClick={logout} className="logout-button">Logout</button>
        </span>
      ) : <Link to="/login" className="navbar-link user-info">Login</Link>}
    </nav>
  );
}
