import React from "react";
import "regenerator-runtime/runtime";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./pages/home-page/HomePage";
import TestPage from "./pages/home-page/TestPage";
import { PreviewPage } from "./pages/preview-page/PreviewPage";
import "./App.css";
import RecordContainer from "./components/record/RecordContainer";
import { NavBarNormal } from './components/navbar/normal/NavBarNormal';
import { StepOnePage } from "./pages/make-worksheet-page/StepOnePage";
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { modalVisibleState } from "./store/Modal";

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
                            <Route path="/preview" element={<PreviewPage />} />
                            <Route path="/record" element={<RecordContainer />} />
                            <Route path="/step1" element={<StepOnePage />} />
                        </Routes>
                    </div>
                </Router>
            </div>
        </div>
    );
};

export default App;
