import React from "react";
import "regenerator-runtime/runtime";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./pages/home-page/HomePage";
import TestPage from "./pages/home-page/TestPage";
import { PreviewPage } from "./pages/preview-page/PreviewPage";
import "./App.css";
import RecordContainer from "./components/record/RecordContainer";
import { NavBarNormal } from "./components/navbar/normal/NavBarNormal";
import { StepOnePage } from "./pages/make-worksheet-page/StepOnePage";
import { useRecoilValue, useSetRecoilState } from "recoil";
import { modalVisibleState } from "./store/Modal";
import LoginPage from "./pages/login-page/LoginPage";
import InsertPrinter from "./components/printer/InsertPrinter";
import MainPage from "./pages/main-page/MainPage";
import WorksheetOptionPage from "./pages/worksheet-option-page/WorksheetOptionPage";

const App: React.FC = () => {
  const modalVisible = useRecoilValue(modalVisibleState);

  return (
    <div className="main">
      <HomePage />
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
              <Route path="/record" element={<RecordContainer />} />
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
            </Routes>
          </div>
        </Router>
      </div>
    </div>
  );
};

export default App;
