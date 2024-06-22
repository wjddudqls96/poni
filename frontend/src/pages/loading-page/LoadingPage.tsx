import React from 'react'
import Loading from "../../assets/loadingCircle.gif";

export const LoadingPage:React.FC = () => {
  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "100%", display: "flex", alignItems:"center", flexDirection:"column"}}>
        
        <div style={{marginTop : "250px"}}>
        <img src={Loading} width={100} height={100}/>
        </div>
        <div>
        나만의 맞춤 학습지
        </div>
        <div>
        출력 중...
        </div>
    </div>
  )
}
