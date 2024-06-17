import React from 'react'
import { SelectOption } from '../../components/select-option/SelectOption'
import { OptionModal } from '../../components/modal/OptionModal'
import { useRecoilValue, useSetRecoilState } from 'recoil';
import "./StepOnePage.css"
import { blankOption, explainSelect, traceOption } from '../../store/Cart';
import { getworkSheetData } from '../../service/cartRequest';
import { Worksheet } from 'worksheet';
import { worksheet } from '../../store/Worksheet';
import { useNavigate } from 'react-router-dom';

export const StepOnePage:React.FC = () => {
  const navigate = useNavigate();
  const getBlank = useRecoilValue(blankOption)
  const getExplainSelect = useRecoilValue(explainSelect);
  const getTrace = useRecoilValue(traceOption);
  const setWorksheetData = useSetRecoilState(worksheet);

  const submit = async () =>  {
    const worksheetData:Worksheet = await getworkSheetData(getTrace, getBlank, getExplainSelect, "축구와 농구는 재미있는 운동이다.");
    setWorksheetData(worksheetData);
    navigate('/preview');
  }


  return (
    <div style={{position: "relative", height: "100vh", maxHeight:"770px"}}>
        <SelectOption/>
        <OptionModal/>
        <button className='step-one-submit-wrapper' onClick={submit}>
          다 음
        </button>
    </div>
  )
}
