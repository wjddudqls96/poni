import { BlankOption, TraceOption } from 'Cart';
import React, { useEffect, useRef, useState } from 'react'
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { blankOption, blankSelect } from '../../store/Cart';
import { modalVisibleState } from '../../store/Modal';
import Close from "../../assets/close.png";

export const BlankModal: React.FC<{ onClose: () => void; nodeRef: React.RefObject<HTMLDivElement> }> = ({ onClose, nodeRef }) => {
    const [number, setNumber] = useState<number>(0);
    const setBlankOption = useSetRecoilState(blankOption);
    const setBlankSelect = useSetRecoilState(blankSelect);
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

    const submit = () => {
        const blankOptionData:BlankOption = {
            count: number,
            type: "WORD",
            isTranslation: false
        }

        setBlankOption(blankOptionData);
        setBlankSelect(true);
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
                  <div>Number of questions</div>
                  <div className="counter">
                    <button onClick={numberDown}>-</button>
                    <span>{number}</span>
                    <button onClick={numberUp}>+</button>
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