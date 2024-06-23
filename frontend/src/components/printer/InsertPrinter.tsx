import React, { ChangeEvent, useState } from "react";
import "./InsertPrinter.css";
import { useNavigate } from "react-router-dom";
import axios from "../../service/axiosConfig";

const InsertPrinter: React.FC = () => {
  const [printerId, setPrinterId] = useState("");

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPrinterId(event.target.value);
  };

  const navigate = useNavigate();
  const submitPrinterId = () => {
    const response = axios.post("/update", {
      deviceId: printerId,
      language: "english",
    });
    navigate("/main");
  };

  return (
    <div>
      <div className="insert-printer-container">
        <div className="message-box">
          <div>Please input your</div>
          <div>Multifunction Device ID</div>
        </div>
        <div className="input-box-tag">Multifunction Device ID</div>
        <div className="input-box">
          <input
            className="input-text-box"
            type="text"
            value={printerId}
            onChange={handleInputChange}
            placeholder="Enter device ID here"
          />
        </div>
      </div>
      <div className="submit-btn" onClick={submitPrinterId}>
        Submit
      </div>
    </div>
  );
};

export default InsertPrinter;
