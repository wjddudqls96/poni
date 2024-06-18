import React from "react";
import arrow from "../../assets/arrow_forward_ios.png";
import icon from "../../assets/worksheetIcon.png";
const WorksheetPageBtn: React.FC = () => {
  return (
    <div>
      <img src={icon} alt="" />
      <div>
        <div>학습지 생성</div>
        <div>맞춤 학습지를 만들어보세요</div>
      </div>
      <img src={arrow} alt="" />
    </div>
  );
};

export default WorksheetPageBtn;
