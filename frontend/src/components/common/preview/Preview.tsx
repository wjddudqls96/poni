import React from 'react'
import './Preview.css';
import { PreviewProps } from 'Preview';
import { Trace } from './content/trace/Trace';
import { Explain } from './content/explain/Explain';
import { Blank } from './content/blank/Blank';


export const Preview: React.FC<PreviewProps> = ({title, type}) => {
  return (
    <div className="container">
            <div className="header">
                <span className='header-title'>{title}</span>
                <span className="close-button">
                </span>
            </div>
            <div className="content">
                {type === 'trace' && <Trace />}
                {type === 'explain' && <Explain />}
                {type === 'blank' && <Blank />}
            </div>
        </div>
  )
}
