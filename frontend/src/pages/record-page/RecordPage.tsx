import React, { useState } from "react";
import OriginalText from "../../components/record/OriginalText";
import RecordBox from "../../components/record/RecordBox";
import TranslatedText from "../../components/record/TranslatedText";

const RecordPage = () => {
  const [transcript, setTranscript] = useState("");
  const [showModal, setShowModal] = useState(false); // 모달 표시 상태 추가

  const handleTranscriptChange = (transcript: string) => {
    setTranscript(transcript);
    setShowModal(true);
  };

  const closeModal = () => {
    setShowModal(false); // 모달 닫기
  };
  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "98%" }}>
      <OriginalText transcript={transcript} />
      {!showModal && <RecordBox onTranscriptChange={handleTranscriptChange} />}
      {showModal && (
        <TranslatedText transcript={transcript} onClose={closeModal} />
      )}
    </div>
  );
};

export default RecordPage;
