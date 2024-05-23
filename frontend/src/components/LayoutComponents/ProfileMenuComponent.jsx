import React from "react";
import { SettingOutlined } from "@ant-design/icons";
import { Menu } from "antd";
import { useNavigate, useLocation } from "react-router-dom";
import "./styles/profile.css";
import { postRequest } from "../../utils/CustomFetcher";

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
    if (
      e.key.includes("logout") &&
      localStorage.getItem("validation") === "true"
    ) {
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
    } else {
      localStorage.getItem("validation") === "true" && navigate(e.key);
    }
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
