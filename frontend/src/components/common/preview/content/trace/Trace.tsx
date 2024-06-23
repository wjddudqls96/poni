import React, { useEffect, useState } from 'react'
import "./Trace.css"
import { useRecoilValue } from 'recoil';
import { content } from '../../../../../store/Content';

interface NormalTableProps {
    content: string;
  }
  

export const Trace: React.FC<any> = ({traceOption}) => {
    const getContent = useRecoilValue(content);
    const [count] = useState<number>(traceOption.count);
    const [isBlurry] = useState<boolean>(traceOption.isBlurry);
    const guideTableCount = Math.min(count, 3);

  return (
    <div>
      <table className="explain-grid">
        <tbody>
          <NormalTable content={getContent}/>
          {isBlurry && Array.from({ length: guideTableCount }).map((_, index) => (
            <GuideTable key={index} content={getContent} />
          ))}
          {!isBlurry && Array.from({ length: guideTableCount }).map((_, index) => (
            <BlankTable key={index} />
          ))}
        </tbody>
      </table>
    </div>
  )
}

const GuideTable: React.FC<NormalTableProps> = ({ content }) => {
    return (
        <tr>
        {content.split('').slice(0, 8).map((char, index) => (
            <td key={index} className="faded-label">
            {char}
            </td>
        ))}
    </tr>
    );
}

const NormalTable: React.FC<NormalTableProps> = ({ content }) => {
    return (
        <tr>
            {content.split('').slice(0, 8).map((char, index) => (
                <td key={index} className="trace-label">
                {char}
                </td>
            ))}
        </tr>
    );
}

const BlankTable: React.FC = () => {
    return (
        <>
            <tr>
                <td className="trace-label"></td>
                <td className="trace-label"></td>
                <td className="trace-label"></td>
                <td className="trace-label"></td>
                <td className="trace-label"></td>
                <td className="trace-label"></td>
                <td className="trace-label"></td>
                <td className="trace-label"></td>
            </tr>
        </>
    );
}

// const CrossTable: React.FC = () => {
//     return (
//         <>
//             <tr>
//                 <td className="explain-grid-cross dashed faded-label">맛</td>
//                 <td className="explain-grid-cross dashed"></td>
//                 <td className="explain-grid-cross dashed"></td>
//                 <td className="explain-grid-cross dashed"></td>
//                 <td className="explain-grid-cross dashed"></td>
//                 <td className="explain-grid-cross dashed"></td>
//                 <td className="explain-grid-cross dashed"></td>
//             </tr>
//         </>
//     );
// }
