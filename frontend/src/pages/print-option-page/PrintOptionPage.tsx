import React, { useState } from 'react';
import './PrintOptions.css';

const PrintOptionPage: React.FC = () => {
  const [colorMode, setColorMode] = useState<boolean>(false);
  const [bothMode, setBothMode] = useState<boolean>(false);
  const [quilityMode, setQuilityMode] = useState<boolean>(false);
  const [reverseMode, setReverseMode] = useState<boolean>(false);

  const handleColorMode = () => {
    setColorMode(!colorMode);
  }

  const handleBothMode = () => {
    setBothMode(!bothMode);
  }

  const handleQuilityMode = () => {
    setQuilityMode(!quilityMode);
  }

  const handleReverseMode = () => {
    setReverseMode(!reverseMode);
  }

  const submit = () => {

  }

  return (
    <div style={{position: "relative", height: "100vh", maxHeight:"100%", backgroundColor:"white"}}>
      <div className="print-options-container">
      <div className="print-options-section">
        <div>컬러모드</div>
            <div className="buttons">
                <button className={`two-button-main ${colorMode ? "apply" : ""}`} onClick={() => handleColorMode()}>컬러</button>
                <button className={`two-button-main ${!colorMode ? "apply" : ""}`} onClick={() => handleColorMode()}>흑백</button>
        </div>
      </div>
      <div className="print-options-section">
        <div className="print-options-label">용지크기</div>
        <div className="print-options-paper-size">A4 21 × 29.7cm</div>
      </div>
      <div className="print-options-section">
        <div>양면 인쇄</div>
        <div className="buttons">
              <button className={`two-button-main ${bothMode ? "apply" : ""}`} onClick={() => handleBothMode()}>적용</button>
              <button className={`two-button-main ${!bothMode ? "apply" : ""}`} onClick={() => handleBothMode()}>미적용</button>
        </div>
      </div>
      <div className="print-options-section">
        <div>인쇄 품질</div>
            <div className="buttons">
                <button className={`two-button-main ${quilityMode ? "apply" : ""}`} onClick={() => handleQuilityMode()}>보통</button>
                <button className={`two-button-main ${!quilityMode ? "apply" : ""}`} onClick={() => handleQuilityMode()}>높음</button>
        </div>
      </div>
      <div className="print-options-section">
        <div>역순 인쇄</div>
            <div className="buttons">
                <button className={`two-button-main ${reverseMode ? "apply" : ""}`} onClick={() => handleReverseMode()}>적용</button>
                <button className={`two-button-main ${!reverseMode ? "apply" : ""}`} onClick={() => handleReverseMode()}>미적용</button>
        </div>
      </div>
      
    </div>
    <button className="print-options-submit-button" onClick={submit}>출력하기</button>
    </div>
  );
};

export default PrintOptionPage;
