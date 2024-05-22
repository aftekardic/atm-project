import React, { useState, useEffect } from "react";
import { Form, Input, Button, Card, Alert } from "antd";
import { DollarOutlined, MailOutlined } from "@ant-design/icons";
import styles from "./styles.module.css";

const WithdrawPage = () => {
  const [currentAmount, setCurrentAmount] = useState(0);

  useEffect(() => {
    // Fetch current amount from API or set a static value
    // Replace this with your actual API call
    const fetchCurrentAmount = async () => {
      // Simulating an API call with a static value
      setCurrentAmount(500); // Assume the current amount is 500
    };

    fetchCurrentAmount();
  }, []);

  const onFinish = (values) => {
    const withdrawAmount = parseFloat(values.amount);
    if (withdrawAmount > currentAmount) {
      alert("Insufficient balance!");
      return;
    }
    setCurrentAmount(currentAmount - withdrawAmount); // Update the current amount
  };

  return (
    <div className={styles.container}>
      <Card title="Withdraw Funds" className={styles.card}>
        <Alert
          message={`Current Amount: $${currentAmount}`}
          type="info"
          style={{ marginBottom: 12 }}
          showIcon
        />
        <Form
          name="withdraw"
          onFinish={onFinish}
          initialValues={{
            amount: "",
            email: "",
          }}
        >
          <Form.Item
            name="email"
            rules={[
              {
                required: true,
                message: "Please input your email!",
              },
              {
                type: "email",
                message: "Please enter a valid email address!",
              },
            ]}
          >
            <Input prefix={<MailOutlined />} placeholder="Email" />
          </Form.Item>
          <Form.Item
            name="amount"
            rules={[
              {
                required: true,
                message: "Please input the amount!",
              },

              {
                validator: (_, value) =>
                  parseInt(value) > 0
                    ? Promise.resolve()
                    : Promise.reject("Amount must be a positive number!"),
              },
            ]}
          >
            <Input prefix={<DollarOutlined />} placeholder="Amount" />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit">
              Withdraw
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
};

export default WithdrawPage;
