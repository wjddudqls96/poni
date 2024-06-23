import React from "react";

import logo from "../../assets/poni_logo.png";
import "./Login.css"

const Login: React.FC = () => {
  return (
    <div className="login-wrapper">
      <img src={logo} alt="" width={100} height={56} style={{marginBottom: "10px"}}/>
      <div className="login-content">Create your own</div>
      <div className="login-content"><span className="font-green">study sheets</span></div>
      <div className="login-content">now!.</div>
    </div>
  );
};

export default Login;
