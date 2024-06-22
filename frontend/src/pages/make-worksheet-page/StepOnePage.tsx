import React from "react";
import { SelectOption } from "../../components/select-option/SelectOption";
import { OptionModal } from "../../components/modal/OptionModal";
import { useRecoilValue, useSetRecoilState } from "recoil";
import "./StepOnePage.css";
import { blankOption, explainSelect, traceOption } from "../../store/Cart";
import { getworkSheetData } from "../../service/cartRequest";
import { Worksheet } from "worksheet";
import { worksheet } from "../../store/Worksheet";
import { useNavigate } from "react-router-dom";
import { content } from "../../store/Content";

export const StepOnePage: React.FC = () => {
  const navigate = useNavigate();
  const getBlank = useRecoilValue(blankOption);
  const getExplainSelect = useRecoilValue(explainSelect);
  const getTrace = useRecoilValue(traceOption);
  const setWorksheetData = useSetRecoilState(worksheet);
  const getContent: string = useRecoilValue(content);

  const submit = async () => {
    navigate("/loading/worksheet");
  };

  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "770px" }}>
      <SelectOption />
      <OptionModal />
      <button className="step-one-submit-wrapper" onClick={submit}>
        다 음
      </button>
    </div>
  );
};
