import { TraceOption } from 'Cart';
import React, { useState } from 'react'
import { useSetRecoilState } from 'recoil';
import { traceOption, traceSelect } from '../../store/Cart';
import { modalVisibleState } from '../../store/Modal';
import Close from "../../assets/close.png";

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
          <div className="modal-content-option" ref={nodeRef} onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h2>Setting</h2>
              
              <img src={Close} width={24} height={24} onClick={onClose}/>
            </div>
            <div className="modal-body">
              <div className="option-container">
                <div className="option">
                  <div>Column settings</div>
                  <div className="counter">
                    <button onClick={numberDown}>-</button>
                    <span>{number}</span>
                    <button onClick={numberUp}>+</button>
                  </div>
                </div>
                <div className="option two-option">
                  <div>Guidelines</div>
                  <div className="buttons">
                    <button className={`two-button-main ${selectFade ? "apply" : ""}`} onClick={() => clickFadeOption("fade")}>Apply</button>
                    <button className={`two-button-main ${!selectFade ? "apply" : ""}`} onClick={() => clickFadeOption("no")}>Not applied</button>
                  </div>
                </div>
              </div>
              <div className="modal-footer">
                <button className="footer-button reset2">Reset</button>
                <button className="footer-button submit" onClick={()=>submit()}>Submit</button>
              </div>
            </div>
          </div>
        </div>
      );
    };