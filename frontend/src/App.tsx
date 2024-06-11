import React from "react";
import "regenerator-runtime/runtime";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./pages/home-page/HomePage";
import TestPage from "./pages/home-page/TestPage";
import { Test2Page } from "./pages/home-page/Test2Page";
import "./App.css";
import RecordContainer from "./components/record/RecordContainer";

const App: React.FC = () => {
  return (
    <div className="main">
      <HomePage />
      <div className="device-main">
        <Router>
          <Routes>
            <Route path="/" element={<TestPage />} />
            <Route path="/2" element={<Test2Page />} />

            {/* 음성 입력 페이지 */}
            <Route path="/record" element={<RecordContainer />}></Route>
          </Routes>
        </Router>
      </div>
    </div>
  );
};

export default App;
