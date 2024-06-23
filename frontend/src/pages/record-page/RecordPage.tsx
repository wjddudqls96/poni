import React, { useEffect, useState } from "react";
import OriginalText from "../../components/record/OriginalText";
import RecordBox from "../../components/record/RecordBox";
import TranslatedText from "../../components/record/TranslatedText";
import "./RecordPage.css";
import { step, title, type } from "../../store/NavBar";
import { useSetRecoilState } from "recoil";

const RecordPage = () => {
  const [transcript, setTranscript] = useState("");
  const [showModal, setShowModal] = useState(false); // 모달 표시 상태 추가
  const setTitle = useSetRecoilState(title);
  const setType = useSetRecoilState(type);
  const setStep = useSetRecoilState(step);
  
  useEffect(() => {
    setTitle("Voice Input")
    setType("step");
    setStep(1);
  }, [])

  const handleTranscriptChange = (transcript: string) => {
    setTranscript(transcript);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false); // 모달 닫기
  };
  return (
    <div className="record-container">
      <OriginalText transcript={transcript} />
      {!showModal && <RecordBox onTranscriptChange={handleTranscriptChange} />}
      {showModal && (
        <TranslatedText transcript={transcript} onClose={closeModal} />
      )}
    </div>
  );
};

export default RecordPage;
