import React from "react";
import { Layout, Menu, theme } from "antd";
import { Outlet } from "react-router-dom";
import { useNavigate } from "react-router-dom";
import ProfileMenuComponent from "./ProfileMenuComponent";

const { Header, Content, Footer } = Layout;

function LayoutComponent() {
  const navigate = useNavigate();
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();
  const items = [
    {
      key: "1",
      label: "Home",
      onClick: () => {
        navigate("/home");
      },
    },
    {
      key: "2",
      label: "Deposit",
      onClick: () => {
        navigate("/deposit");
      },
    },
    {
      key: "3",
      label: "Withdraw",
      onClick: () => {
        navigate("/withdraw");
      },
    },
  ];
  return (
    <Layout style={{ height: "100vh" }}>
      <Header
        style={{
          position: "sticky",
          top: 0,
          zIndex: 1,
          display: "flex",
          alignItems: "center",
        }}
      >
        <div className="demo-logo" />
        <Menu
          theme="dark"
          mode="horizontal"
          defaultSelectedKeys={["1"]}
          items={items}
          style={{
            flex: 1,
            minWidth: 0,
          }}
        />
        <ProfileMenuComponent />
      </Header>
      <Content
        style={{
          padding: "48px 48px",
        }}
      >
        <div
          style={{
            padding: 24,
            minHeight: 380,
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
          }}
        >
          <Outlet />
        </div>
      </Content>
      <Footer
        style={{
          textAlign: "center",
        }}
      >
        ATM Project ©{new Date().getFullYear()} Created by aftekardic
      </Footer>
    </Layout>
  );
}

export default LayoutComponent;
