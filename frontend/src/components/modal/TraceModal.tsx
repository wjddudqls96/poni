import { TraceOption } from 'Cart';
import React, { useState } from 'react'
import { useSetRecoilState } from 'recoil';
import { traceOption, traceSelect } from '../../store/Cart';
import { modalVisibleState } from '../../store/Modal';

export const TraceModal: React.FC<{ onClose: () => void; nodeRef: React.RefObject<HTMLDivElement> }> = ({ onClose, nodeRef }) => {
    const [number, setNumber] = useState<number>(0);
    const [selectFade, setSelectFade] = useState<boolean>(false);
    const setTraceOption = useSetRecoilState(traceOption);
    const setTraceSelect = useSetRecoilState(traceSelect);
    const setModalVisible = useSetRecoilState(modalVisibleState);

    const numberUp = () => {
        if(number + 1 < 6) {
            setNumber(number + 1);
        }
    }

    const numberDown = () => {
        if(number != 0) {
            setNumber(number - 1);
        } 
    }

    const clickFadeOption = (type:string) => {
        if(type == "fade") {
            setSelectFade(true);
        }
        else {
            setSelectFade(false);
        }
    }

    const submit = () => {
      console.log("asdasd");
      
        const traceOptionData:TraceOption = {
          isBlurry: selectFade,
          isGrid: false,
          count: number,
        }

        setTraceOption(traceOptionData);
        setTraceSelect(true);
        setModalVisible(false);
    }
    
    return (
        <div className="modal-overlay" onClick={onClose}>
          <div className="modal-content" ref={nodeRef} onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h2>따라쓰기 설정</h2>
              <button onClick={onClose}>X</button>
            </div>
            <div className="modal-body">
              <div className="option-container">
                <div className="option">
                  <div>칸 설정</div>
                  <div className="counter">
                    <button onClick={numberDown}>-</button>
                    <span>{number}</span>
                    <button onClick={numberUp}>+</button>
                  </div>
                </div>
                <div className="option two-option">
                  <div>흐린선</div>
                  <div className="buttons">
                    <button className={`two-button-main ${selectFade ? "apply" : ""}`} onClick={() => clickFadeOption("fade")}>적용</button>
                    <button className={`two-button-main ${!selectFade ? "apply" : ""}`} onClick={() => clickFadeOption("no")}>미적용</button>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button className="reset">초기화</button>
                <button className="apply" onClick={()=>submit()}>적용</button>
              </div>
            </div>
          </div>
        </div>
      );
    };