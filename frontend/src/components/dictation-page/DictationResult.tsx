import React, { useEffect, useState } from "react";
import "./DictationResult.css";
import axios from "../../service/axiosConfig";

const DictationResult: React.FC = () => {
  const [resultList, setResultList] = useState(null);
  const [correct, setCorrect] = useState(0);
  const [incorrect, setIncorrect] = useState(0);
  const [problemsCount, setProblemsCount] = useState(0);

  useEffect(() => {
    const getDictationResult = async () => {
      const response = await axios.get("/api/dictation/");
      const data = response.data;
      setCorrect(data.correct);
      setIncorrect(data.incorrect);
      setProblemsCount(data.problemsCount);
      setResultList(data.ploblem);
    };
    getDictationResult();
    console.log(resultList);
  }, []);

  return (
    <div className="result-container">
      <div className="result-name-box">
        <div>Maria,</div>
      </div>
      <div className="result-text-box">
        <div>the dictation result is</div>
        <div className="result-score">{(correct * 100) / problemsCount}</div>
      </div>
      <div className="result-score-box">
        <div>
          <span>Question</span>
          <span>{problemsCount}</span>
        </div>
        <div>
          <span>correct answer</span>
          <span style={{ color: "#5585FF" }}>{correct}</span>
        </div>
        <div>
          <span>wrong answer</span>
          <span style={{ color: "#FF6262" }}>{incorrect}</span>
        </div>
      </div>

      <div className="dictation-result-box">
        <div className="dictation-result">
          {resultList &&
            resultList.map((item) => (
              <div>
                <div
                  className={`dictation-input ${
                    item.input !== item.answer && "wrong"
                  }`}
                >
                  {item.input}
                </div>
                <div className="dictation-answer">{item.answer}</div>
              </div>
            ))}
        </div>
      </div>
      <div className="result-page-btns">
        <div className="result-page-btn">Restart</div>
        <div className="result-page-btn create-note">Review</div>
      </div>
    </div>
  );
};

export default DictationResult;
