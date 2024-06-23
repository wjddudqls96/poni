import React, { useState } from 'react'
import { useRecoilValue, useSetRecoilState } from 'recoil';
import "./SelectOption.css"
import Check from "../../assets/check.png"
import { modalTypeState, modalVisibleState } from '../../store/Modal';
import { blankSelect, explainSelect, traceSelect } from '../../store/Cart';
import { stepCount } from '../../store/Worksheet';


export const SelectOption:React.FC = () => {
    const getBlankSelect = useRecoilValue(blankSelect)
    const getExplainSelect = useRecoilValue(explainSelect);
    const getTraceSelect = useRecoilValue(traceSelect);
    const setModalVisible = useSetRecoilState(modalVisibleState);
    const setModalType = useSetRecoilState(modalTypeState);
    const setBlankSelect = useSetRecoilState(blankSelect);
    const setTraceSelect = useSetRecoilState(traceSelect);
    const setExplainSelect = useSetRecoilState(explainSelect);
    const setStepCount = useSetRecoilState(stepCount);
    const getStepCount = useRecoilValue(stepCount);

    const handleTraceClick = () => {
        if(getTraceSelect) {
            setTraceSelect(false);
            setStepCount(getStepCount - 1);
        }
        else {
            setModalType("trace");
            setModalVisible(true);
            setStepCount(getStepCount + 1);
        }
    }

    const handleExplainClick = () => {

        if(getExplainSelect) {
            setStepCount(getStepCount - 1);
        }
        else {
            setStepCount(getStepCount + 1);
        }

        setExplainSelect(!getExplainSelect);
    }

    const handleBlankClick = () => {
        if(getBlankSelect) {
            setBlankSelect(false);
            setStepCount(getStepCount - 1);
        }
        else {
            setModalType("blank");
            setModalVisible(true);
            setStepCount(getStepCount + 1);
        }
    }

    return (
      <div className="list-container">
        <div className={`list-wrapper ${getTraceSelect ? 'selected' : ''}`} onClick={handleTraceClick}>
            <div className='list-title'>
                <div>
                Write along
                </div>
            </div>
            {getTraceSelect ? <CheckDiv/> : <></>}
            
        </div>
        <div className={`list-wrapper ${getExplainSelect ? 'selected' : ''}`} onClick={handleExplainClick}>
            <div className='list-title'>
                <div>
                Create a glossary
                </div>
            </div>
            {getExplainSelect ? <CheckDiv/> : <></>}
        </div>
        <div className={`list-wrapper ${getBlankSelect ? 'selected' : ''}`} onClick={handleBlankClick}>
            <div className='list-title'>
                <div>
                Create a Blank question
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


