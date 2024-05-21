import React from "react";
import { Layout, Menu, theme } from "antd";
import { Outlet, useLocation, useNavigate } from "react-router-dom";
import ProfileMenuComponent from "./ProfileMenuComponent";

const { Header, Content, Footer } = Layout;

function LayoutComponent() {
  const navigate = useNavigate();
  const location = useLocation();

  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();
  const items = [
    { key: "/home", label: "Home" },
    { key: "/deposit", label: "Deposit" },
    { key: "/withdraw", label: "Withdraw" },
  ];

  const handleMenuClick = (e) => {
    navigate(e.key);
  };
  return (
    <Layout style={{ height: "100vh" }}>
      <Header
        style={{
          position: "sticky",
          top: 0,
          zIndex: 1,
          width: "100%",
          display: "flex",
          alignItems: "center",
        }}
      >
        <div className="demo-logo" />
        <Menu
          theme="dark"
          mode="horizontal"
          selectedKeys={[location.pathname]}
          items={items}
          onClick={handleMenuClick}
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
