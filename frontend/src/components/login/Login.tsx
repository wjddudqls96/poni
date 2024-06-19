import React from "react";
import loginBtn from "../../assets/login_btn.png";
import logo from "../../assets/poni_logo.png";

const Login: React.FC = () => {
  return (
    <div>
      <div></div>
      <img src={logo} alt="" />
      <div>지금 바로</div>
      <div>나만의 학습지를</div>
      <div>만들어보세요.</div>
      <img src={loginBtn} alt="" />
    </div>
  );
};

export default Login;
