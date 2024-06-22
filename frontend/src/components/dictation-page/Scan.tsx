import React from "react";
import "./Scan.css";

const Scan: React.FC = () => {
  return (
    <div>
      <div className="scan-text-box">
        <div>받아쓰기 완료</div>
        <div>작성한 답안지를 스캔해주세요</div>
      </div>
      <div className="get-print-btn">출력 생성</div>
    </div>
  );
};

export default Scan;
