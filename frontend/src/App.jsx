import React, { useEffect } from "react";
import { Routes, Route, useNavigate } from "react-router-dom";
import LoginPage from "./pages/Login";
import HomePage from "./pages/Home";
import DepositPage from "./pages/Deposit";
import WithdrawPage from "./pages/Withdraw";
import ProfilePage from "./pages/Profile";
import LayoutComponent from "./components/LayoutComponents/LayoutComponent";
import { postRequest } from "./utils/CustomFetcher";

function App() {
  const navigate = useNavigate();
  useEffect(() => {
    if (
      localStorage.getItem("token") !== null &&
      localStorage.getItem("user_id") &&
      localStorage.getItem("tokenId")
    ) {
      const tokenObj = {
        id: localStorage.getItem("tokenId"),
        token: localStorage.getItem("token"),
        user_id: localStorage.getItem("user_id"),
      };

      postRequest("/validateToken", tokenObj).then((result) => {
        if (result.status === 200) {
          localStorage.setItem("validation", true);
        } else {
          localStorage.setItem("validation", false);
        }
      });
    } else {
      localStorage.clear();
      navigate("/");
    }
  }, [navigate]);

  return (
    <Routes>
      <Route path="/" Component={LoginPage} />
      <Route path="/" element={<LayoutComponent />}>
        <Route path="home" element={<HomePage />} />
        <Route path="deposit" element={<DepositPage />} />
        <Route path="withdraw" element={<WithdrawPage />} />
        <Route path="profile" element={<ProfilePage />} />
      </Route>
    </Routes>
  );
}

export default App;
