import React, { useEffect, useState } from "react";
import SpeechRecognition from "react-speech-recognition";
import { useSpeechRecognition } from "react-speech-recognition";
import recordBtn from "../../assets/person.png";
import stopBtn from "../../assets/stopBtn.png";
import "./RecordBox.css";

interface RecordBoxProps {
  onTranscriptChange: (transcript: string) => void;
}

const RecordBox: React.FC<RecordBoxProps> = ({ onTranscriptChange }) => {
  const [isRecording, setIsRecording] = useState(false);
  const [buttonImage, setButtonImage] = useState(recordBtn); // 초기 이미지 설정
  const { transcript, resetTranscript } = useSpeechRecognition();

  useEffect(() => {
    // 페이지에 들어오자마자 녹음 시작
    handleToggleRecording();
  }, []);

  const handleToggleRecording = () => {
    if (!isRecording) {
      resetTranscript();
      SpeechRecognition.startListening({ continuous: true } as any);
      setButtonImage(stopBtn); // 녹음 시작 시 이미지 변경
    } else {
      SpeechRecognition.stopListening();
      onTranscriptChange(transcript);
      setButtonImage(recordBtn); // 녹음 중지 시 이미지 변경
    }
    setIsRecording(!isRecording);
  };

  return (
    <div className="record-box">
      <div>음성 입력해주세요</div>
      {/* 이미지 클릭 시 녹음 시작 또는 중지 */}
      <img
        src={buttonImage}
        alt={isRecording ? "Stop Button" : "Record Button"}
        onClick={handleToggleRecording}
        style={{ cursor: "pointer" }}
      />
    </div>
  );
};

export default RecordBox;
