import React, { useState, useEffect } from "react";

interface OriginalTextProps {
  transcript?: string;
}

const TranslatedText: React.FC<OriginalTextProps> = ({ transcript }) => {
  const [isReading, setIsReading] = useState(false);
  const [voices, setVoices] = useState<SpeechSynthesisVoice[]>([]);

  useEffect(() => {
    const synth = window.speechSynthesis;
    const loadVoices = () => {
      setVoices(synth.getVoices());
    };

    loadVoices();
    if (synth.onvoiceschanged !== undefined) {
      synth.onvoiceschanged = loadVoices;
    }
  }, []);

  const speakText = (text: string) => {
    const synth = window.speechSynthesis;
    const utterance = new SpeechSynthesisUtterance(text);
    const selectedVoice = voices.find(
      (voice) => voice.name === "Google 한국의"
    );

    if (selectedVoice) {
      utterance.voice = selectedVoice;
    }

    synth.speak(utterance);
    utterance.onend = () => {
      setIsReading(false);
    };
  };

  const handleButtonClick = () => {
    if (transcript && !isReading) {
      setIsReading(true);
      speakText(transcript);
    }
  };

  return (
    <div>
      <div>TranslatedText</div>
      <div>{transcript}</div>
      <button onClick={handleButtonClick} disabled={isReading}>
        {isReading ? "Reading..." : "Read Text"}
      </button>
    </div>
  );
};

export default TranslatedText;
