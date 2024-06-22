import React from "react";
import "regenerator-runtime/runtime";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import TestPage from "./pages/home-page/TestPage";
import { PreviewPage } from "./pages/preview-page/PreviewPage";
import "./App.css";
import { NavBarNormal } from "./components/navbar/normal/NavBarNormal";
import { StepOnePage } from "./pages/make-worksheet-page/StepOnePage";
import LoginPage from "./pages/login-page/LoginPage";
import InsertPrinter from "./components/printer/InsertPrinter";
import MainPage from "./pages/main-page/MainPage";
import WorksheetOptionPage from "./pages/worksheet-option-page/WorksheetOptionPage";
import SelectGradePage from "./pages/dictation-page/SelectGradePage";
import DictationPage from "./pages/dictation-page/DictationPage";
import ScanPage from "./pages/dictation-page/ScanPage";
import RecordPage from "./pages/record-page/RecordPage";
import PdfViewer from "./pages/pdf-viewer-page/PdfViewer";
import PrintOptionPage from "./pages/print-option-page/PrintOptionPage";
import { LoadingPage } from "./pages/loading-page/LoadingPage";
import { CartPage } from "./pages/cart-page/CartPage";
import MainLogo from "./assets/mainLogo.png";

const App: React.FC = () => {
  return (
    <div className="main">
      <div className="home-main-content">
        <div className="home-main-logo">
          <img src={MainLogo} width={199} height={111} />
        </div>
        <div className="home-main-title">
          포니
        </div>
        <div className="home-main-explain">
          <div>
          당신을 위한 특별한 한국어 학습,
          </div>
          <div>
          맞춤형으로 여정을 함께하세요.
          </div>
        </div>
      </div>
      <div className="epson-footer">
        <div className="epson-footer-title">
          EPSON
        </div>
        <div className="epson-footer-explain">
        포니는 엡손 프린트를 활용한 맞춤형 학습지 서비스입니다.
        </div>
      </div>
      <div className="device-main">
        <Router>
          <div>
            <NavBarNormal />
          </div>
          <div className="device-main-content">
            <Routes>
              <Route path="/" element={<TestPage />} />
              <Route path="/main" element={<MainPage />}></Route>
              <Route path="/preview" element={<PreviewPage />} />
              {/* 녹음 페이지 */}
              <Route path="/record" element={<RecordPage />} />
              <Route path="/step1" element={<StepOnePage />} />
              {/* 로그인 페이지 */}
              <Route path="/login" element={<LoginPage />}></Route>
              {/* 프린터 정보 입력 페이지 */}
              <Route path="/insertPrinter" element={<InsertPrinter />}></Route>
              {/* 학습지 유형 선택 페이지 */}
              <Route
                path="/worksheet/option"
                element={<WorksheetOptionPage />}
              ></Route>
              {/* 받아쓰기 난이도 선택 페이지 */}
              <Route path="/select/grade" element={<SelectGradePage />}></Route>
              {/* 받아쓰기 페이지 */}
              <Route path="/dictation" element={<DictationPage />}></Route>
              {/* 받아쓰기 완료, 스캔 페이지 */}
              <Route path="/scan" element={<ScanPage />}></Route>
              {/* 받아쓰기 결과 페이지 */}
              <Route path="/dictation/result"></Route>
              {/* 출력 미리보기 */}
              <Route path="/preview"></Route>
              <Route path="/Pdfviewer" element={<PdfViewer/>}></Route>
              <Route path="/print/option" element={<PrintOptionPage/>}></Route>
              <Route path="/loading" element={<LoadingPage/>}></Route>
              <Route path="/cart" element={<CartPage/>}></Route>
            </Routes>
          </div>
        </Router>
      </div>
    </div>
  );
};

export default App;
