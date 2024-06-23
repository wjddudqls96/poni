import React, { useEffect, useState } from 'react'
import Loading from "../../assets/loadingCircle.gif";
import { useNavigate } from 'react-router-dom';
import { blankOption, explainSelect, traceOption } from '../../store/Cart';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { worksheet } from '../../store/Worksheet';
import { content } from '../../store/Content';
import { Worksheet } from 'worksheet';
import { getworkSheetData } from '../../service/cartRequest';
import { step, title, type } from '../../store/NavBar';

export const LoadingMake:React.FC = () => {
  const navigate = useNavigate();
  const getBlank = useRecoilValue(blankOption);
  const getExplainSelect = useRecoilValue(explainSelect);
  const getTrace = useRecoilValue(traceOption);
  const setWorksheetData = useSetRecoilState(worksheet);
  const getContent: string = useRecoilValue(content);
  const setTitle = useSetRecoilState(title);
  const setType = useSetRecoilState(type);
  const setStep = useSetRecoilState(step);

  useEffect(() => {
    const getResponse = async () => {
      const worksheetData: Worksheet = await getworkSheetData(
        getTrace,
        getBlank,
        getExplainSelect,
        getContent
      );
      setWorksheetData(worksheetData);
      setTitle("create")
      setType("step");
      setStep(3)
      navigate("/preview");
    };
    getResponse();
  }, []);
  
  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "100%", display: "flex", alignItems:"center", flexDirection:"column"}}>
        
        <div style={{marginTop : "250px"}}>
        <img src={Loading} width={100} height={100}/>
        </div>
        <div>
        Study sheet
        </div>
        <div>
        Making...
        </div>
    </div>
  )
}
