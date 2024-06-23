import React, { useEffect } from "react";
import Login from "../../components/login/Login";
import loginBtn from "../../assets/login_btn.png";
import "./LoginPage.css"
import { useSetRecoilState } from "recoil";
import { step, title, type } from "../../store/NavBar";

const LoginPage: React.FC = () => {

  const setTitle = useSetRecoilState(title);
  const setType = useSetRecoilState(type);
  const setStep = useSetRecoilState(step);

  useEffect(() => {
    setTitle("Login")
    setType("normal");
    setStep(0);
  }, [])

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
