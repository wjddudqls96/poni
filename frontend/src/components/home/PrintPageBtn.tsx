import React from "react";
import icon from "../../assets/printIcon.png";
import arrow from "../../assets/arrow_forward_ios.png";
import "./MainPageBtn.css";
import { useNavigate } from "react-router-dom";

const PrintPageBtn: React.FC = () => {
  const navigate = useNavigate();
  const clickEvent = () => {
    navigate("/cart");
  };

  return (
    <div className="main-page-btn" onClick={clickEvent}>
      <img src={icon} className="icon" />
      <div className="text-box">
        <div className="function-name">Print</div>
        <div className="explanation">Print your own study sheets</div>
      </div>
      <img src={arrow} className="arrow" />
    </div>
  );
};

export default PrintPageBtn;
