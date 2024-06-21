import React from "react";
import "./TextModal.css";
import createWroksheet from "../../assets/download.png";
import { useSetRecoilState } from "recoil";
import { content } from "../../store/Content";

interface ModalProps {
  onClose: () => void; // 모달 닫기 함수
}
const createWroksheetClick = () => {};

const TextModal: React.FC<ModalProps> = ({ onClose, children }) => {
  const setContent = useSetRecoilState(content);
  return (
    <div className="modal-content" onClick={(e) => e.stopPropagation()}>
      <span className="close-btn" onClick={onClose}>
        ×
      </span>
      <div className="modal-contents">{children}</div>
      <div className="create-worksheet">
        <img src={createWroksheet} onClick={createWroksheetClick} />
      </div>
    </div>
  );
};

export default TextModal;
