import React, { useEffect, useState } from "react";
import {
  Table,
  Button,
  Modal,
  Form,
  Input,
  message,
  Popconfirm,
  Select,
} from "antd";
import {
  getRequest,
  deleteRequest,
  putRequest,
} from "../../../utils/CustomFetcher";

const { Option } = Select;

function UserListPage() {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [isModalVisible, setIsModalVisible] = useState(false);
  const [selectedUser, setSelectedUser] = useState(null);
  const [roles, setRoles] = useState([]);
  const [form] = Form.useForm();
  const dataSource = users.map((user) => {
    return {
      ...user,
      role: user.role.name,
      role_id: user.role.id,
    };
  });
  useEffect(() => {
    fetchUsers();
    fetchRoles();
  }, []);

  const fetchUsers = async () => {
    setLoading(true);
    const result = await getRequest("/api/v1/admin/all-users");
    if (result.status === 200) {
      setUsers(result.data.users);
    } else {
      message.error("Failed to fetch users");
    }
    setLoading(false);
  };

  const fetchRoles = async () => {
    const result = await getRequest("/api/v1/role/all");

    if (result.status === 200) {
      setRoles(result.data);
    } else {
      message.error("Failed to fetch roles");
    }
  };

  const handleDelete = async (id) => {
    const result = await deleteRequest(`/api/v1/admin/delete/${id}`);
    if (result.status === 200) {
      setUsers(users.filter((user) => user.id !== id));
      message.success("User deleted successfully");
    } else {
      message.error("Failed to delete user");
    }
  };

  const showEditModal = (user) => {
    setSelectedUser(user);
    form.setFieldsValue(user);
    setIsModalVisible(true);
  };

  const handleCancel = () => {
    setIsModalVisible(false);
    form.resetFields();
  };

  const handleOk = () => {
    form.submit();
  };

  const handleFinish = async (values) => {
    setLoading(true);

    const result = await putRequest(`/api/v1/admin/update/${selectedUser.id}`, {
      name: values.name,
      surname: values.surname,
      email: values.email,
      role_id: selectedUser.role_id,
    });
    setLoading(false);
    if (result.status === 200) {
      message.success("User updated successfully");
      setIsModalVisible(false);
      form.resetFields();
      fetchUsers();
    } else {
      message.error("Failed to update user");
    }
  };

  const columns = [
    { title: "Name", dataIndex: "name", key: "name" },
    { title: "Surname", dataIndex: "surname", key: "surname" },
    { title: "Email", dataIndex: "email", key: "email" },
    { title: "Role", dataIndex: "role", key: "role" },
    {
      title: "Actions",
      key: "actions",
      render: (text, record) => (
        <span>
          <Button
            type="primary"
            onClick={() => showEditModal(record)}
            style={{ marginRight: 8 }}
          >
            Edit
          </Button>
          <Popconfirm
            title="Are you sure to delete this user?"
            onConfirm={() => handleDelete(record.id)}
            okText="Yes"
            cancelText="No"
          >
            <Button type="danger">Delete</Button>
          </Popconfirm>
        </span>
      ),
    },
  ];

  return (
    <div>
      <Table
        dataSource={dataSource}
        columns={columns}
        rowKey="id"
        loading={loading}
      />
      <Modal
        title="Edit User"
        visible={isModalVisible}
        onCancel={handleCancel}
        onOk={handleOk}
        okText="Save"
        cancelText="Cancel"
      >
        <Form form={form} layout="vertical" onFinish={handleFinish}>
          <Form.Item name="name" label="Name" rules={[{ required: true }]}>
            <Input />
          </Form.Item>
          <Form.Item
            name="surname"
            label="Surname"
            rules={[{ required: true }]}
          >
            <Input />
          </Form.Item>
          <Form.Item
            name="email"
            label="Email"
            rules={[{ required: true, type: "email" }]}
          >
            <Input />
          </Form.Item>
          <Form.Item name="role" label="Role" rules={[{ required: true }]}>
            <Select>
              {roles.map((role) => (
                <Option key={role.id} value={role.id}>
                  {role.name}
                </Option>
              ))}
            </Select>
          </Form.Item>
        </Form>
      </Modal>
    </div>
  );
}

export default UserListPage;
