import React, { useEffect, useRef } from 'react'
import { useRecoilValue, useSetRecoilState } from 'recoil';
import './Modal.css';
import { CSSTransition } from 'react-transition-group';
import { modalTypeState, modalVisibleState } from '../../store/Modal';
import { TraceModal } from './TraceModal';
import { BlankModal } from './BlankModal';

export const OptionModal = () => {
    const setModalVisible = useSetRecoilState(modalVisibleState);
    const modalVisible = useRecoilValue(modalVisibleState);
    const modalType = useRecoilValue(modalTypeState);
    const nodeRef = useRef(null);

    useEffect(() => {
        console.log(modalVisible);
        
    }, []);
    
  return (
    <div>
        <CSSTransition
        in={modalVisible}
        timeout={300}
        classNames="modal"
        unmountOnExit
        nodeRef={nodeRef}
        >
          {modalType == "trace" ? 
          <TraceModal onClose={() => setModalVisible(false)} nodeRef={nodeRef}/>
          :
          <BlankModal onClose={() => setModalVisible(false)} nodeRef={nodeRef}/>
          }
        </CSSTransition>
    </div>
  )
}