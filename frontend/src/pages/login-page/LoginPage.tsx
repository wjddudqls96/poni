import React from "react";
import Login from "../../components/login/Login";
import loginBtn from "../../assets/login_btn.png";
import "./LoginPage.css"

const LoginPage: React.FC = () => {

  const handleClick = () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google"
  }

  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "100%", backgroundColor:"white"}}>
      <div className="login-space"></div>
      <Login />
       <img className="google-button" src={loginBtn} alt="" onClick={handleClick}/>
    </div>
  );
};

export default LoginPage;
