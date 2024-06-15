import React from 'react'
import "./Explain.css"
import { ContentProps } from 'Preview'

export const Explain: React.FC = () => {
  return (
    <div className="explain-container">
      <div className='main-sentence'>
        사슴이 이쁘다.
      </div>
      <Content type='주어'/>
      <Content type='동사'/>
      <Content type='보어'/>
    </div>
  )
}

const Content: React.FC<ContentProps>= ({ type }) => {
  const bgColor = getColorByType(type);

  return (
    <div className="content-container">
      <div className='content-label-container'>
        <div className="explain-label" style={{ backgroundColor: bgColor }}>주어</div>
      </div>
      <div className='content-text-container'>
        <div className='content-text-title'>
        "사슴"
        </div>
        <div className='content-text-explain'>
         이 문장에서 주어는 "사슴"으로, 누구나 알고 있는 대상을 가리킵니다.
        </div>
      </div>
    </div>
  )
}

const getColorByType = (type: string) => {
  switch (type) {
    case '주어':
      return '#ff7f7f'; // 빨간색
    case '동사':
      return '#7f7fff'; // 파란색
    case '보어':
      return '#b07fff'; // 보라색
    case '형용사':
      return '#7fc8ff'; // 보라색
    default:
      return '#cccccc'; // 기본 회색
  }
};
