import { BlankOption, TraceOption } from 'Cart';
import React, { useEffect, useRef, useState } from 'react'
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { blankOption, blankSelect } from '../../store/Cart';
import { modalVisibleState } from '../../store/Modal';

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
              </div>
              <div className="modal-footer">
                <button className="footer-button reset2">초기화</button>
                <button className="footer-button submit" onClick={()=>submit()}>적용</button>
              </div>
            </div>
          </div>
        </div>
      );
    };