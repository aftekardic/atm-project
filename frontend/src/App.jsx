import React from "react";
import { Routes, Route } from "react-router-dom";
import LoginPage from "./pages/Login";
import HomePage from "./pages/Home";
import DepositPage from "./pages/Deposit";
import WithdrawPage from "./pages/Withdraw";
import LayoutComponent from "./components/LayoutComponents/LayoutComponent";

function App() {
  return (
    <Routes>
      <Route path="/" Component={LoginPage} />
      <Route path="/" element={<LayoutComponent />}>
        <Route path="home" element={<HomePage />} />
        <Route path="deposit" element={<DepositPage />} />
        <Route path="withdraw" element={<WithdrawPage />} />
      </Route>
    </Routes>
  );
}

export default App;
