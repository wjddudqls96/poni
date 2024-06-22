import React from "react";
import arrow from "../../assets/arrow_forward_ios.png";
import icon from "../../assets/worksheetIcon.png";
import { useNavigate } from "react-router-dom";

const WorksheetPageBtn: React.FC = () => {
  const navigate = useNavigate();
  const clickEvent = () => {
    navigate("/record");
  };
  return (
    <div className="main-page-btn" onClick={clickEvent}>
      <img src={icon} alt="" className="icon" />
      <div className="text-box">
        <div className="function-name">학습지 생성</div>
        <div className="explanation">맞춤 학습지를 만들어보세요</div>
      </div>
      <img src={arrow} alt="" className="arrow" />
    </div>
  );
};

export default WorksheetPageBtn;
