import React, { useEffect, useState } from 'react'
import "./Explain.css"
import { ContentProps } from 'Preview'
import { Analysis, Explanation } from 'worksheet'

export const Explain: React.FC<any> = (explanation) => {
  const [data, setData] = useState<Explanation>(explanation.explanation[0]);
  const [analysis, setAnalysis] = useState<Analysis[]>(explanation.explanation[0].analysis);

  return (
    <div className="explain-container">
      <div className='main-sentence'>
        {data.sentence}
      </div>
      {analysis.slice(0, 3).map((value:Analysis, index) => (
        <Content key={index} analysis={value} />
      ))}
    </div>
  )
}

const Content: React.FC<{analysis: Analysis}>= ({ analysis }) => {
  const bgColor = getColorByType(analysis.grammar);

  return (
    <div className="content-container">
      <div className='content-label-container'>
        <div className="explain-label" style={{ backgroundColor: bgColor }}>{analysis.grammar}</div>
      </div>
      <div className='content-text-container'>
        <div className='content-text-title'>
        "{analysis.word}"
        </div>
        <div className='content-text-explain'>
         {analysis.word_description}
        </div>
      </div>
    </div>
  )
}

const getColorByType = (grammar: string) => {
  switch (grammar) {
    case 'subject':
      return '#ff7f7f'; // 빨간색
    case 'verb':
      return '#7f7fff'; // 파란색
    case 'complement':
      return '#b07fff'; // 보라색
    case 'adjective':
      return '#7fc8ff'; // 보라색
    default:
      return '#cccccc'; // 기본 회색
  }
};
