import React from "react";
import "regenerator-runtime/runtime";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import HomePage from "./pages/home-page/HomePage";
import TestPage from "./pages/home-page/TestPage";
import { PreviewPage } from "./pages/preview-page/PreviewPage";
import "./App.css";
import RecordContainer from "./components/record/RecordContainer";
import { NavBarNormal } from './components/navbar/normal/NavBarNormal';

const App: React.FC = () => {
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
                            <Route path="/2" element={<PreviewPage />} />
                            <Route path="/record" element={<RecordContainer />} />
                        </Routes>
                    </div>
                </Router>
            </div>
        </div>
    );
};

export default App;
