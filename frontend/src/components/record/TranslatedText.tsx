import React, { useState, useEffect } from "react";
import axios from "../../service/axiosConfig";
import TextModal from "./TextModal"; // 모달 컴포넌트를 import
import sound from "../../assets/sound.png";
import { useSetRecoilState } from "recoil";
import { content } from "../../store/Content";

interface TranslatedTextProps {
  transcript?: string;
  onClose: () => void; // 모달 닫기 함수를 props로 받음
}

const TranslatedTextModal: React.FC<TranslatedTextProps> = ({
  transcript,
  onClose,
}) => {
  const [isReading, setIsReading] = useState(false);
  const [voices, setVoices] = useState<SpeechSynthesisVoice[]>([]);
  const [translatedText, setTranslatedText] = useState<string | null>(null);
  const [pronunciation, setPronunciation] = useState<string | null>(null);
  const setContent = useSetRecoilState(content);

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
            setPronunciation(response.data.data.pronunciation);
            setContent(response.data.data.translatedSentence);
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
    <TextModal onClose={onClose}>
      <div>
        <img
          src={sound}
          onClick={handleButtonClick}
          style={{ marginTop: 15 }}
        ></img>
        <h2>Translated Text</h2>
        <p>{translatedText}</p>
        <p>{pronunciation}</p>
      </div>
    </TextModal>
  );
};

export default TranslatedTextModal;
