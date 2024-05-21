import React from "react";
import { useNavigate } from "react-router-dom";
import { Tabs } from "antd";
import styles from "./styles.module.css";
import LoginComponent from "../../components/AuthComponents/LoginComponent";
import RegisterComponent from "../../components/AuthComponents/RegisterComponent";

const { TabPane } = Tabs;
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
      <Tabs defaultActiveKey="1">
        <TabPane tab="Login" key="1">
          <LoginComponent loginClick={handleLoginClick} />
        </TabPane>
        <TabPane tab="Register" key="2">
          <RegisterComponent registerClick={handleRegisterClick} />
        </TabPane>
      </Tabs>
    </div>
  );
}

export default LoginPage;
