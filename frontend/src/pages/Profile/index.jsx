import React, { useEffect, useState } from "react";
import { Form, Input, Button, Card, message } from "antd";
import styles from "./styles.module.css";
import { getRequest, postRequest, putRequest } from "../../utils/CustomFetcher";
import { useNavigate } from "react-router-dom";

const ProfilePage = () => {
  const navigate = useNavigate();
  const [form] = Form.useForm();
  const [isEditing, setIsEditing] = useState(false);
  const [profileInfo, setProfileInfo] = useState({
    name: null,
    surname: null,
    email: null,
    role: null,
  });

  useEffect(() => {
    const user_id = localStorage.getItem("user_id");
    getRequest(`/api/v1/user/info/${user_id}`).then((result) => {
      if (result.status === 200) {
        setProfileInfo(result.data.info);
      } else {
        message.error("Network Error!");
      }
    });
  }, []);

  const handleEdit = () => {
    setIsEditing(true);
  };

  const handleCancel = () => {
    setIsEditing(false);
    form.resetFields();
  };

  const handleSave = (values) => {
    setProfileInfo(values);
    setIsEditing(false);

    const userDto = {
      name: values.name,
      surname: values.surname,
      email: values.email,
      role_id: values.role_id,
    };
    putRequest(
      `/api/v1/user/update/${localStorage.getItem("user_id")}`,
      userDto
    ).then((result) => {
      message.info(result.data.message);
      const token = localStorage.getItem("token");
      const tokenId = localStorage.getItem("tokenId");
      const user_id = localStorage.getItem("user_id");

      localStorage.removeItem("token");
      localStorage.removeItem("tokenId");
      localStorage.removeItem("user_id");
      localStorage.removeItem("validation");
      const tokenDto = {
        id: tokenId,
        token: token,
        user_id: user_id,
      };
      postRequest("/logOut", {
        tokenDto: tokenDto,
      }).then((result) => {
        if (result.status === 200) {
          navigate("/");
        } else {
          navigate(0);
        }
      });
    });
  };

  return (
    <div className={styles.container}>
      <Card title="Profile Information" className={styles.card}>
        {!isEditing ? (
          <div>
            <p>
              <strong>Name:</strong> {profileInfo?.name}
            </p>
            <p>
              <strong>Surname:</strong> {profileInfo?.surname}
            </p>
            <p>
              <strong>Email:</strong> {profileInfo?.email}
            </p>
            <p>
              <strong>Role:</strong> {profileInfo?.role}
            </p>

            <Button type="primary" onClick={handleEdit}>
              Edit Profile
            </Button>
          </div>
        ) : (
          <Form
            form={form}
            layout="vertical"
            initialValues={profileInfo}
            onFinish={handleSave}
          >
            <Form.Item
              name="name"
              label="Name"
              rules={[{ required: true, message: "Please input your name!" }]}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="surname"
              label="Surname"
              rules={[
                { required: true, message: "Please input your surname!" },
              ]}
            >
              <Input />
            </Form.Item>
            <Form.Item
              name="email"
              label="Email"
              rules={[
                { required: true, message: "Please input your email!" },
                { type: "email", message: "The input is not valid E-mail!" },
              ]}
            >
              <Input />
            </Form.Item>
            <Form.Item name="role" label="Role" rules={[{ required: false }]}>
              <Input value={profileInfo.role} disabled />
            </Form.Item>
            <Form.Item>
              <Button type="primary" htmlType="submit">
                Save
              </Button>
              <Button
                type="default"
                onClick={handleCancel}
                style={{ marginLeft: "10px" }}
              >
                Cancel
              </Button>
            </Form.Item>
          </Form>
        )}
      </Card>
    </div>
  );
};

export default ProfilePage;
