import React, { ChangeEvent, useState } from "react";

const InsertPrinter: React.FC = () => {
  const [printerId, setPrinterId] = useState("");

  const handleInputChange = (event: ChangeEvent<HTMLInputElement>) => {
    setPrinterId(event.target.value);
  };

  return (
    <div>
      <div>복합기 아이디를</div>
      <div>입력해주세요</div>
      <div>
        <div>복합기 아이디</div>
        <input type="text" value={printerId} onChange={handleInputChange} />
      </div>
      <div>완료</div>
    </div>
  );
};

export default InsertPrinter;
