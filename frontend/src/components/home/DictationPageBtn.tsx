import React from "react";
import icon from "../../assets/dictationIcon.png";
import arrow from "../../assets/arrow_forward_ios.png";
import { useNavigate } from "react-router-dom";

const DictationPageBtn: React.FC = () => {
  const navigate = useNavigate();
  const clickEvent = () => {
    navigate("/dictation");
  };

  return (
    <div className="main-page-btn" onClick={clickEvent}>
      <img src={icon} alt="" className="icon" />
      <div className="text-box">
        <div className="function-name">받아쓰기</div>
        <div className="explanation">한국어 실력을 확인해보세요</div>
      </div>
      <img src={arrow} alt="" className="arrow" />
    </div>
  );
};

export default DictationPageBtn;
