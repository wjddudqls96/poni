import React from 'react';
import { useNavigate } from 'react-router-dom'; // useNavigate를 import합니다.
import "./NavBarNormal.css";
import PoniLogo from "../../../assets/poni_logo.png";
import Person from "../../../assets/person.png";

export const NavBarNormal: React.FC = () => {
  const navigate = useNavigate(); // useNavigate 훅을 호출하여 navigate 함수를 가져옵니다.

  return (
    <div className='navbar-main'>
        <div className='nav-logo' onClick={() => navigate('/')}>
            <img src={PoniLogo} alt="Poni Logo" />
        </div>
        <div className='nav-space'>
            {/* 여기에 다른 내용이 있을 수 있습니다. */}
        </div>
        <div className='nav-person' onClick={() => navigate('/profile')}>
            <img src={Person} alt="Person Icon" />
        </div>
    </div>
  );
}
