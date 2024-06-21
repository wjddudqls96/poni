import React from "react";
import SelectGrade from "../../components/dictation-page/SelectGrade";
import { useNavigate } from "react-router-dom";
import "./SelectGradePage.css";

const SelectGradePage: React.FC = () => {
  const navigate = useNavigate();

  const handleNextClick = () => {
    navigate("/dictation");
  };
  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "100%" }}>
      <SelectGrade />
      <div onClick={handleNextClick} className={`next-button`}>
        다음
      </div>
    </div>
  );
};

export default SelectGradePage;
