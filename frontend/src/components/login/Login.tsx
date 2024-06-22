import React from "react";

import logo from "../../assets/poni_logo.png";
import "./Login.css"

const Login: React.FC = () => {
  return (
    <div className="login-wrapper">
      <img src={logo} alt="" width={100} height={56}/>
      <div className="login-content">지금 바로</div>
      <div className="login-content"><span className="font-green">나만의 학습지</span>를</div>
      <div className="login-content">만들어보세요.</div>
    </div>
  );
};

export default Login;
