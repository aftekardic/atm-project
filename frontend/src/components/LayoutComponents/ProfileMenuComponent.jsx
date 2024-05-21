import React, { useState } from "react";
import { SettingOutlined } from "@ant-design/icons";
import { Menu } from "antd";
import { useNavigate } from "react-router-dom";
import "./styles/profile.css";

function ProfileMenuComponent() {
  const [current, setCurrent] = useState("1");
  const onClick = (e) => {
    console.log("click ", e);
    setCurrent(e.key);
  };
  const navigate = useNavigate();
  const items = [
    {
      icon: <SettingOutlined />,
      children: [
        {
          key: "profile",
          label: "Profile",
          onClick: () => {
            navigate(0);
          },
        },
        {
          key: "11",
          label: "Log Out",
          onClick: () => {
            navigate(0);
          },
        },
      ],
    },
  ];
  return (
    <Menu
      onClick={onClick}
      style={{
        width: "auto",
        backgroundColor: "transparent",
        borderInlineEnd: "None",
        height: "auto",
        display: "flex",
      }}
      selectedKeys={[current]}
      triggerSubMenuAction="click"
      mode="vertical"
      items={items}
    />
  );
}

export default ProfileMenuComponent;
