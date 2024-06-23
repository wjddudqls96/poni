import React from "react";
import "./DictationResult.css";

const DictationResult: React.FC = () => {
  return (
    <div className="result-container">
      <div className="result-name-box">
        <div>~~~님 </div>
      </div>
      <div className="result-text-box">
        <div>받아쓰기 결과는</div>
        <div className="result-score">~~점</div>
        <div>입니다.</div>
      </div>
      <div className="result-score-box">
        <div>
          <span>문제</span>
          <span>~개</span>
        </div>
        <div>
          <span>정답</span>
          <span style={{ color: "#5585FF" }}>~개</span>
        </div>
        <div>
          <span>오답</span>
          <span style={{ color: "#FF6262" }}>~개</span>
        </div>
      </div>

      <div className="dictation-result-box">
        <div className="dictation-result">리스트</div>
      </div>
      <div className="result-page-btns">
        <div className="result-page-btn">다시하기</div>
        <div className="result-page-btn create-note">오답노트 생성</div>
      </div>
    </div>
  );
};

export default DictationResult;
