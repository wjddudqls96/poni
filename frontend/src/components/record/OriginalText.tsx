import React from "react";

interface OriginalTextProps {
  transcript?: string;
}

const OriginalText: React.FC<OriginalTextProps> = ({ transcript }) => {
  return (
    <div
      style={{
        display: transcript ? "block" : "none",
        marginLeft: "1rem",
        marginTop: "1rem",
      }}
    >
      <p>Original Text:</p>
      <p>{transcript}</p>
    </div>
  );
};

export default OriginalText;
