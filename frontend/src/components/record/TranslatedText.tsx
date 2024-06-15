import React, { useState, useEffect } from "react";
import axios from "../../service/axiosConfig";

interface OriginalTextProps {
  transcript?: string;
}

const TranslatedText: React.FC<OriginalTextProps> = ({ transcript }) => {
  const [isReading, setIsReading] = useState(false);
  const [voices, setVoices] = useState<SpeechSynthesisVoice[]>([]);
  const [translatedText, setTranslatedText] = useState<string | null>(null);

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

  useEffect(() => {
    const fetchTranslatedText = async () => {
      if (transcript) {
        try {
          const response = await axios.post("/api/v1/worksheet/translate", {
            originalSentence: transcript,
          });

          if (response.status === 200 || response.status === 201) {
            console.log(response.data);
            setTranslatedText(response.data.data.translatedSentence);
          } else {
            console.error("Failed to fetch translated text");
          }
        } catch (error) {
          console.error("Error:", error);
        }
      }
    };

    fetchTranslatedText();
  }, [transcript]);

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
    if (translatedText && !isReading) {
      setIsReading(true);
      speakText(translatedText);
    }
  };

  return (
    <div>
      <div>TranslatedText</div>
      <div>{translatedText}</div>
      <button onClick={handleButtonClick} disabled={isReading}>
        {isReading ? "Reading..." : "Read Text"}
      </button>
    </div>
  );
};

export default TranslatedText;
