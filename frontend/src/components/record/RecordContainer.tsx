import React, { useState } from "react";
import RecordBox from "./RecordBox";
import OriginalText from "./OriginalText";
import TranslatedText from "./TranslatedText";

const RecordContainer: React.FC = () => {
  const [transcript, setTranscript] = useState("");

  const handleTranscriptChange = (transcript: string) => {
    setTranscript(transcript);
  };

  return (
    <div>
      <OriginalText transcript={transcript} />
      <RecordBox onTranscriptChange={handleTranscriptChange} />
      <TranslatedText transcript={transcript} />
    </div>
  );
};

export default RecordContainer;
