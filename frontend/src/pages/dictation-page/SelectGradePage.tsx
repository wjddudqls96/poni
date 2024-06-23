import React from "react";
import SelectGrade from "../../components/dictation-page/SelectGrade";

const SelectGradePage: React.FC = () => {
  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "100%" }}>
      <SelectGrade />
    </div>
  );
};

export default SelectGradePage;
