import React from "react";
import icon from "../../assets/dictationIcon.png";
import arrow from "../../assets/arrow_forward_ios.png";
import { useNavigate } from "react-router-dom";

const DictationPageBtn: React.FC = () => {
  const navigate = useNavigate();
  const clickEvent = () => {
    navigate("/select/grade");
  };

  return (
    <div className="main-page-btn" onClick={clickEvent}>
      <img src={icon} alt="" className="icon" />
      <div className="text-box">
        <div className="function-name">Dictation</div>
        <div className="explanation">Check your Korean language skills.</div>
      </div>
      <img src={arrow} alt="" className="arrow" />
    </div>
  );
};

export default DictationPageBtn;
