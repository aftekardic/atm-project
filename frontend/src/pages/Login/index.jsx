import React from "react";
import { useNavigate } from "react-router-dom";
import styles from "./styles.module.css";
import LoginComponent from "../../components/AuthComponents/LoginComponent";
import RegisterComponent from "../../components/AuthComponents/RegisterComponent";
function LoginPage() {
  const navigate = useNavigate();

  function handleLoginClick() {
    //chec user is authenticate or not after navigate
    navigate(0);
  }

  function handleRegisterClick() {
    //create a popup and inform user like registiration is completed
    navigate(0);
  }

  return (
    <div className={styles.container}>
      <LoginComponent loginClick={handleLoginClick} />
      <RegisterComponent registerClick={handleRegisterClick} />
    </div>
  );
}

export default LoginPage;
