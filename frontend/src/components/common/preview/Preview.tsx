import React, { useEffect } from 'react'
import './Preview.css';
import { PreviewProps } from 'Preview';
import { Trace } from './content/trace/Trace';
import { Explain } from './content/explain/Explain';
import { Blank } from './content/blank/Blank';
import { Worksheet } from 'worksheet';
import { useRecoilValue } from 'recoil';
import { worksheet } from '../../../store/Worksheet';


export const Preview: React.FC<PreviewProps> = ({title, type}) => {
  const worksheetData:Worksheet = useRecoilValue(worksheet);
  
  return (
    <div className="container">
            <div className="header">
                <span className='header-title'>{title}</span>
                <span className="close-button">
                </span>
            </div>
            <div className="content">
                {type === 'trace' && <Trace traceOption={worksheetData.traceOption}/>}
                {type === 'explain' && <Explain explanation={worksheetData.explanation}/>}
                {type === 'blank' && <Blank blank={worksheetData.blank}/>}
            </div>
        </div>
  )
}
