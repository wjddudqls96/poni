import React from 'react';
import './Blank.css';
import { BlankProps } from 'Preview';

const renderText = (text: string) => {
    const parts = text.split('___');
    return parts.map((part, index) => (
      <React.Fragment key={index}>
        {part}
        {index < parts.length - 1 && <input type="text" className="blank-input" />}
      </React.Fragment>
    ));
};

export const Blank: React.FC = () => {
  return (
    <div className='blank-wrapper'>
        <BlankContent text='놀이터 ___ 집으로 가요.'/>
        <BlankContent text='우리는 ___ 최고의 요리사.'/>
    </div>
  );
};

const BlankContent:React.FC<BlankProps>= ({ text }) => {
  return (
    <div>
        <div className="blank-container">
            {renderText(text)}
            <div className='blank-translate'>
                i'm going home from the playground
            </div>
        </div>
    </div>
  )
}

