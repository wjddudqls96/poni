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
        <div className="function-name">Create a study sheet</div>
        <div className="explanation">Create a personalized study sheet</div>
      </div>
      <img src={arrow} alt="" className="arrow" />
    </div>
  );
};

export default WorksheetPageBtn;
