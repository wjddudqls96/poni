import React from "react";
import icon from "../../assets/dictationIcon.png";
import arrow from "../../assets/arrow_forward_ios.png";

const DictationPageBtn: React.FC = () => {
  return (
    <div>
      <img src={icon} alt="" />
      <div>
        <div>받아쓰기</div>
        <div>한국어 실력을 확인해보세요</div>
      </div>
      <img src={arrow} alt="" />
    </div>
  );
};

export default DictationPageBtn;
