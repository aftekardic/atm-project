import React from "react";
import { SettingOutlined } from "@ant-design/icons";
import { Menu } from "antd";
import { useNavigate, useLocation } from "react-router-dom";
import "./styles/profile.css";

function ProfileMenuComponent() {
  const navigate = useNavigate();
  const location = useLocation();

  const items = [
    {
      icon: <SettingOutlined />,
      children: [
        {
          key: "/profile",
          label: "Profile",
        },
        {
          key: "/logout",
          label: "Log Out",
        },
      ],
    },
  ];

  const handleMenuClick = (e) => {
    navigate(e.key);
  };
  return (
    <Menu
      onClick={handleMenuClick}
      style={{
        width: "auto",
        backgroundColor: "transparent",
        borderInlineEnd: "None",
        height: "auto",
        display: "flex",
      }}
      selectedKeys={[location.pathname]}
      triggerSubMenuAction="click"
      mode="vertical"
      items={items}
    />
  );
}

export default ProfileMenuComponent;
