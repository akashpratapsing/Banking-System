
import React from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import Login from './pages/Login';
import CustomerDashboard from './pages/CustomerDashboard';
import AdminDashboard from './pages/AdminDashboard';
import { useAuth } from './context/AuthContext';
import NavBar from './components/NavBar';

function App() {
  const { user } = useAuth();

  return (
    <div>
      <NavBar />
      <Routes>
        <Route path='/' element={ user ? <Navigate to={user.role==='ADMIN'?'/admin':'/customer'} /> : <Login /> } />
        <Route path='/login' element={<Login />} />
        <Route path='/customer' element={ user ? <CustomerDashboard /> : <Navigate to='/login' />} />
        <Route path='/admin' element={ user ? <AdminDashboard /> : <Navigate to='/login' />} />
      </Routes>
    </div>
  );
}

export default App;
