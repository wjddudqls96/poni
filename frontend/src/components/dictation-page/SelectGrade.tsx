import React from "react";
import { useRecoilState, useRecoilValue, useSetRecoilState } from "recoil";
import { grade } from "../../store/Grade";
import "./SelectGrade.css"; // 추가된 CSS 파일
import { useNavigate } from "react-router-dom";

const SelectGrade: React.FC = () => {
  const currentGrade = useRecoilValue(grade);
  const setGrade = useSetRecoilState(grade);
  const handleClick = (selectedGrade: string) => {
    setGrade(selectedGrade);
  };

  const navigate = useNavigate();

  const handleNextClick = () => {
    navigate("/dictation");
  };

  return (
    <div className="grade-container">
      <div className="grade-text">
        <div>한국어 난이도를</div>
        <div>선택해주세요.</div>
      </div>
      <div
        onClick={() => handleClick("입문")}
        className={`grade-option ${currentGrade === "입문" ? "selected" : ""}`}
      >
        입문
      </div>
      <div
        onClick={() => handleClick("초급")}
        className={`grade-option ${currentGrade === "초급" ? "selected" : ""}`}
      >
        초급
      </div>
      <div
        onClick={() => handleClick("중급")}
        className={`grade-option ${currentGrade === "중급" ? "selected" : ""}`}
      >
        중급
      </div>
      <div
        onClick={() => handleClick("고급")}
        className={`grade-option ${currentGrade === "고급" ? "selected" : ""}`}
      >
        고급
      </div>
      <div onClick={handleNextClick} className={`next-button`}>
        다음
      </div>
    </div>
  );
};

export default SelectGrade;
