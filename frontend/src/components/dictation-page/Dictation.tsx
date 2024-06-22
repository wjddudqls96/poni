import React, { useEffect, useState } from "react";
import axios from "../../service/axiosConfig";
import { useRecoilValue } from "recoil";
import { grade } from "../../store/Grade";
import speakBtn from "../../assets/speak.png";
import stopSpeakBtn from "../../assets/stopSpeak.png";
import "./Dictation.css";
import { useNavigate } from "react-router-dom";

const Dictation: React.FC = () => {
  const difficulty = useRecoilValue(grade);
  const [voices, setVoices] = useState<SpeechSynthesisVoice[]>([]);
  const [contentList, setContentList] = useState(null);
  const navigate = useNavigate();
  useEffect(() => {
    // tts
    const synth = window.speechSynthesis;
    const loadVoices = () => {
      setVoices(synth.getVoices());
    };

    loadVoices();
    if (synth.onvoiceschanged !== undefined) {
      synth.onvoiceschanged = loadVoices;
    }

    const getDictationText = async () => {
      const response = await axios.post("/api/dictation/difficulty", {
        difficulty: difficulty,
        count: 3,
      });
      setContentList(response.data.contentList);
    };
    getDictationText();
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
  };

  const delay = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

  const speak = async () => {
    if (contentList == null) return;
    speakText("문제는 총 세 문항이며 두 번 들려드립니다.");
    await delay(6000);

    for (const text of contentList) {
      speakText(text.content);
      await delay(3000);
      speakText(text.content);
      await delay(6000);
    }
    navigate("/scan");
  };

  return (
    <div className="speak-container">
      <div onClick={speak} className="speak-btn">
        <div>받아쓰기중..</div>
        <img src={speakBtn} />
      </div>
      {/* <img src={stopSpeakBtn} className="stop-btn">
        정지버튼
      </img> */}
    </div>
  );
};

export default Dictation;
