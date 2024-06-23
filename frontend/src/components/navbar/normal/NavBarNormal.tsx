import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; // useNavigate를 import합니다.
import "./NavBarNormal.css";
import PoniLogo from "../../../assets/poni_logo.png";
import Person from "../../../assets/person.png";
import Home from "../../../assets/home.png";
import Arrow from "../../../assets/arrow_back.png";
import { useRecoilValue } from 'recoil';
import { step, title, type } from '../../../store/NavBar';

export const NavBar: React.FC<any> = () => {
  const getType = useRecoilValue(type);

  return (
    <div>
      {getType == "normal" ? <NomarNavBar/> : <></>}
      {getType == "step" ? <StepNavBar /> : <></>}
    </div>
  );
}


export const NomarNavBar:React.FC = () => {
  const navigate = useNavigate(); // useNavigate 훅을 호출하여 navigate 함수를 가져옵니다.

  return (
    <div className='navbar-main'>
        <div className='nav-logo' onClick={() => navigate('/main')}>
            <img src={PoniLogo} alt="Poni Logo" width={61.3} height={34} />
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


export const StepNavBar:React.FC = () => {
  const navigate = useNavigate(); // useNavigate 훅을 호출하여 navigate 함수를 가져옵니다.
  const getStep = useRecoilValue(step);
  const getTitle = useRecoilValue(title);
  return (
    <div className='navbar-main'>
        <div className='nav-logo' onClick={() => navigate(-1)}>
            <img src={Arrow} alt="Poni Logo" width={24} height={24}/>
        </div>
        <div className='nav-logo2' onClick={() => navigate('/main')}>
            <img src={Home} alt="Poni Logo" width={24} height={24}/>
        </div>
       
        <div className='nav-space2'>
        {getTitle}
        </div>
        <div className='nav-spacing'></div>
        <div className='nav-person'>
            
        </div>
        <div className='progress-bar' style={{ width: `${(getStep / 4) * 100}%` }}></div>
    </div>
  );
}