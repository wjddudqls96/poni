import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import HomePage from './pages/home-page/HomePage';
import TestPage from './pages/home-page/TestPage';
import { Test2Page } from './pages/home-page/Test2Page';
import "./App.css"
import { NavBarNormal } from './components/navbar/normal/NavBarNormal';

const App: React.FC = () => {
  return (
    <div className='main'>
      <HomePage />
      <div className="device-main">
        
        <Router>
        <NavBarNormal/>
            <Routes>
              <Route path="/" element={<TestPage />} />
              <Route path="/2" element={<Test2Page />} />
            </Routes>
        </Router>
      </div>
    </div>
  );
};

export default App;
