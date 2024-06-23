import React, { useState } from 'react';
import './Blank.css';
import { Problem } from 'worksheet';

export const Blank: React.FC<any> = ({blank}) => {
  const [problems] = useState<Problem[]>(blank[0].problems);

  return (
    <div className='blank-wrapper'>
      {problems.slice(0, 3).map((problem:Problem, index) => <BlankContent key={index} problem={problem}/> )}
    </div>
  );
};

const BlankContent:React.FC<{problem:Problem}>= ({ problem }) => {
  return (
    <div>
        <div className="blank-container">
            {renderText(problem.content_kr)}
            <div className='blank-translate'>
                {problem.content_en}
            </div>
        </div>
    </div>
  )
}

const renderText = (text: string) => {
  const parts = text.split('___');
  return parts.map((part, index) => (
    <React.Fragment key={index}>
      {part}
      {index < parts.length - 1 && <input type="text" className="blank-input" />}
    </React.Fragment>
  ));
};

