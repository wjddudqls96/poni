import React from "react";
import icon from "../../assets/printIcon.png";
import arrow from "../../assets/arrow_forward_ios.png";

const PrintPageBtn: React.FC = () => {
  return (
    <div>
      <img src={icon} alt="" />
      <div>
        <div>출력하기</div>
        <div>나만의 학습지 출력</div>
      </div>
      <img src={arrow} alt="" />
    </div>
  );
};

export default PrintPageBtn;
