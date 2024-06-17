import React, { useState } from 'react'
import { useRecoilValue, useSetRecoilState } from 'recoil';
import "./SelectOption.css"
import Check from "../../assets/check.png"
import { modalTypeState, modalVisibleState } from '../../store/Modal';
import { blankSelect, explainSelect, traceSelect } from '../../store/Cart';


export const SelectOption:React.FC = () => {
    const getBlankSelect = useRecoilValue(blankSelect)
    const getExplainSelect = useRecoilValue(explainSelect);
    const getTraceSelect = useRecoilValue(traceSelect);
    const setModalVisible = useSetRecoilState(modalVisibleState);
    const setModalType = useSetRecoilState(modalTypeState);
    const setBlankSelect = useSetRecoilState(blankSelect);
    const setTraceSelect = useSetRecoilState(traceSelect);
    const setExplainSelect = useSetRecoilState(explainSelect);

    const handleTraceClick = () => {
        if(getTraceSelect) {
            setTraceSelect(false);
        }
        else {
            setModalType("trace");
            setModalVisible(true);
        }
    }

    const handleExplainClick = () => {
        setExplainSelect(!getExplainSelect);
    }

    const handleBlankClick = () => {
        if(getBlankSelect) {
            setBlankSelect(false);
        }
        else {
            setModalType("blank");
            setModalVisible(true);
        }
    }

    return (
      <div className="list-container">
        <div className={`list-wrapper ${getTraceSelect ? 'selected' : ''}`} onClick={handleTraceClick}>
            <div className='list-title'>
                <div>
                따라쓰기
                </div>
            </div>
            {getTraceSelect ? <CheckDiv/> : <></>}
            
        </div>
        <div className={`list-wrapper ${getExplainSelect ? 'selected' : ''}`} onClick={handleExplainClick}>
            <div className='list-title'>
                <div>
                용어 설명 생성
                </div>
            </div>
            {getExplainSelect ? <CheckDiv/> : <></>}
        </div>
        <div className={`list-wrapper ${getBlankSelect ? 'selected' : ''}`} onClick={handleBlankClick}>
            <div className='list-title'>
                <div>
                빈칸 문제 생성
                </div>
            </div>
            {getBlankSelect ? <CheckDiv/> : <></>}
        </div>

      </div>
    );
}

const CheckDiv:React.FC = () => {
    return (
        <div className='list-select'>
            <img src={Check} width={20} height={20}/>
        </div>
    )
}


