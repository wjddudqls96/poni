import React from 'react'
import "./Trace.css"

export const Trace: React.FC = () => {
  return (
    <div>
      <table className="explain-grid">
        <tbody>
          <NormalTable/>
          <GuideTable/>
          <BlankTable/>
          <CrossTable/>
        </tbody>
      </table>
    </div>
  )
}

const GuideTable: React.FC = () => {
    return (
        <>
            <tr>
                <td className="faded-label">사</td>
                <td className="faded-label">슴</td>
                <td className="faded-label">이</td>
                <td className="faded-label"> </td>
                <td className="faded-label">이</td>
                <td className="faded-label">쁘</td>
                <td className="faded-label">다</td>
            </tr>
        </>
    );
}

const NormalTable: React.FC = () => {
    return (
        <>
            <tr>
                <td className="trace-label">사</td>
                <td className="trace-label">슴</td>
                <td className="trace-label">이</td>
                <td className="trace-label"> </td>
                <td className="trace-label">이</td>
                <td className="trace-label">쁘</td>
                <td className="trace-label">다</td>
            </tr>
        </>
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
            </tr>
        </>
    );
}

const CrossTable: React.FC = () => {
    return (
        <>
            <tr>
                <td className="explain-grid-cross dashed faded-label">맛</td>
                <td className="explain-grid-cross dashed"></td>
                <td className="explain-grid-cross dashed"></td>
                <td className="explain-grid-cross dashed"></td>
                <td className="explain-grid-cross dashed"></td>
                <td className="explain-grid-cross dashed"></td>
                <td className="explain-grid-cross dashed"></td>
            </tr>
        </>
    );
}
