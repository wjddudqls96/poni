import React from "react";
import "./DictationResult.css";

const DictationResult: React.FC = () => {
  return (
    <div className="result-container">
      <div className="result-name-box">
        <div>Maria,</div>
      </div>
      <div className="result-text-box">
        <div>the dictation result is</div>
        <div className="result-score">00.</div>
      </div>
      <div className="result-score-box">
        <div>
          <span>Question</span>
          <span>10</span>
        </div>
        <div>
          <span>correct answer</span>
          <span style={{ color: "#5585FF" }}>8</span>
        </div>
        <div>
          <span>wrong answer</span>
          <span style={{ color: "#FF6262" }}>2</span>
        </div>
      </div>

      <div className="dictation-result-box">
        <div className="dictation-result">리스트</div>
      </div>
      <div className="result-page-btns">
        <div className="result-page-btn">Restart</div>
        <div className="result-page-btn create-note">Review</div>
      </div>
    </div>
  );
};

export default DictationResult;
