import React from "react";
import "./DictationResult.css";

const DictationResult: React.FC = () => {
  return (
    <div>
      <div className="result-name-box">
        <div>~~~님 </div>
      </div>
      <div className="result-text-box">
        <div>받아쓰기 결과는</div>
        <div className="result-score">~~점</div>
        <div>입니다.</div>
      </div>

      <div>
        <div>문제</div>
        <div>~개</div>
      </div>
      <div>
        <div>정답</div>
        <div>~개</div>
      </div>
      <div>
        <div>오답</div>
        <div>~개</div>
      </div>
      <div>
        <div>다시하기</div>
        <div>오답노트 생성</div>
      </div>
    </div>
  );
};

export default DictationResult;
