import React, { useState, useEffect } from "react";
import { Form, Input, Button, Card, Alert, message } from "antd";
import { DollarOutlined } from "@ant-design/icons";
import styles from "./styles.module.css";
import { getRequest, putRequest } from "../../utils/CustomFetcher";

const DepositPage = () => {
  const [currentAmount, setCurrentAmount] = useState(0);

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

  const onFinish = (values) => {
    const depositAmount = parseFloat(values.amount);

    putRequest(`/api/v1/user/deposit/${localStorage.getItem("user_id")}`, {
      deposit: depositAmount,
    }).then((result) => {
      if (result.status === 200) {
        setCurrentAmount(currentAmount + depositAmount);
        message.success(result.data.amount);
      } else {
        message.error(result.data.amount);
      }
    });
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
