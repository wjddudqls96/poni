import React, { useEffect, useState } from "react";
import axios from "../../service/axiosConfig";
import { useRecoilValue } from "recoil";
import { grade } from "../../store/Grade";
import speakBtn from "../../assets/speak.png";
import stopSpeakBtn from "../../assets/stopSpeak.png";
import "./Dictation.css";

const Dictation: React.FC = () => {
  const difficulty = useRecoilValue(grade);
  const [voices, setVoices] = useState<SpeechSynthesisVoice[]>([]);
  const [contentList, setContentList] = useState(null);
  const [idx, setIdx] = useState<number>(0);
  var flag: boolean = true;
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
    flag = true;
    while (flag) {
      const text = contentList[idx / 2];
      console.log(contentList);
      speakText(text.content);
      await delay(4000);
      setIdx(idx + 1);
      console.log(idx);
    }
  };

  const stopSpeak = async () => {
    flag = false;
    console.log(flag);
  };

  return (
    <div className="speak-container">
      <div onClick={speak} className="speak-btn">
        <div>받아쓰기중..</div>
        <img src={speakBtn} />
      </div>
      <img src={stopSpeakBtn} className="stop-btn" onClick={stopSpeak}></img>
    </div>
  );
};

export default Dictation;
