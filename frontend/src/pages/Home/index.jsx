// HomePage.js
import React, { useState, useEffect } from "react";
import { Card, Button, Alert } from "antd";
import { useNavigate } from "react-router-dom";
import styles from "./styles.module.css";

const HomePage = () => {
  const [currentAmount, setCurrentAmount] = useState(0);
  const navigate = useNavigate();

  useEffect(() => {
    // Fetch current amount from API or set a static value
    // Replace this with your actual API call
    const fetchCurrentAmount = async () => {
      // Simulating an API call with a static value
      setCurrentAmount(500); // Assume the current amount is 500
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
