// HomePage.js
import React, { useState, useEffect } from "react";
import { Card, Button, Alert } from "antd";
import { useNavigate } from "react-router-dom";
import styles from "./styles.module.css";
import { getRequest } from "../../utils/CustomFetcher";

const HomePage = () => {
  const [currentAmount, setCurrentAmount] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchCurrentAmount = async () => {
      getRequest(`/api/v1/user/amount/${localStorage.getItem("user_id")}`).then(
        (result) => {
          setCurrentAmount(result.data.amount);
        }
      );
    };

    fetchCurrentAmount();
  }, []);

  return (
    <div className={styles.container}>
      <Card title="Account Summary" className={styles.card}>
        <Alert
          message={`Current Balance: $${currentAmount}`}
          type="info"
          showIcon
          style={{ marginBottom: 20 }}
        />
        <Button
          type="primary"
          onClick={() => navigate("/deposit")}
          style={{ marginBottom: 10 }}
          block
        >
          Deposit Funds
        </Button>
        <Button type="primary" onClick={() => navigate("/withdraw")} block>
          Withdraw Funds
        </Button>
      </Card>
    </div>
  );
};

export default HomePage;
