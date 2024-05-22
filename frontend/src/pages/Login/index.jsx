import React from "react";
import { useNavigate } from "react-router-dom";
import { Tabs, message } from "antd";
import styles from "./styles.module.css";
import LoginComponent from "../../components/AuthComponents/LoginComponent";
import RegisterComponent from "../../components/AuthComponents/RegisterComponent";
import { postRequest } from "../../utils/CustomFetcher";

const { TabPane } = Tabs;
function LoginPage() {
  const navigate = useNavigate();

  function handleLoginClick(values) {
    postRequest("/authenticate", values).then((result) => {
      if (result.status == 200) {
        navigate("/home");
      } else {
        message.error(result.data.message);
      }
    });
  }

  function handleRegisterClick(values) {
    const newUser = {
      email: values.email,
      name: values.name,
      password: values.password,
      surname: values.surname,
    };
    postRequest("/register", newUser).then((result) => {
      if (result.status == 200) {
        message.success(result.data.message);
      } else {
        message.error(result.data.message);
      }
    });
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
