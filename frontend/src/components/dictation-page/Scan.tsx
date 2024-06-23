import React from "react";
import "./Scan.css";
import { useNavigate } from "react-router-dom";

const Scan: React.FC = () => {
  const navigate = useNavigate();
  const clickPrintBtn = () => {
    navigate("/dictation/result");
  };
  return (
    <div>
      <div className="scan-text-box">
        <div className="complete-dictation">Finished</div>
        <div className="scan-text">Scan your completed answer sheet</div>
      </div>
      <div className="get-print-btn" onClick={clickPrintBtn}>
        Print
      </div>
    </div>
  );
};

export default Scan;
