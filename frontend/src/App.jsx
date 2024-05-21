import React from "react";
import { Routes, Route } from "react-router-dom";
import LoginPage from "./pages/Login";
import HomePage from "./pages/Home";
import DepositPage from "./pages/Deposit";
import WithdrawPage from "./pages/Withdraw";

function App() {
  return (
    <Routes>
      <Route path="/" Component={LoginPage} />
      <Route path="/home" Component={HomePage} />
      <Route path="/deposit" Component={DepositPage} />
      <Route path="/withdraw" Component={WithdrawPage} />
    </Routes>
  );
}

export default App;
