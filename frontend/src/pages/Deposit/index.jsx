import React, { useState, useEffect } from "react";
import { Form, Input, Button, Card, Alert } from "antd";
import { DollarOutlined, MailOutlined } from "@ant-design/icons";
import styles from "./styles.module.css";

const DepositPage = () => {
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
    console.log("Received values of form: ", values);
    // Handle deposit logic here
    const depositAmount = parseFloat(values.amount);
    setCurrentAmount(currentAmount + depositAmount); // Update the current amount
  };

  return (
    <div className={styles.container}>
      <Card title="Deposit Funds" className={styles.card}>
        <Alert
          message={`Current Amount: $${currentAmount}`}
          type="info"
          style={{ marginBottom: 12 }}
          showIcon
        />
        <Form
          name="deposit"
          onFinish={onFinish}
          initialValues={{
            amount: "",
            account: "",
          }}
        >
          <Form.Item
            name="email"
            rules={[
              {
                required: true,
                message: "Please input your emaikl!",
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
              Deposit
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
};

export default DepositPage;
