import React, { useEffect } from "react";
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
import { step, title, type } from "../../store/NavBar";

export const StepOnePage: React.FC = () => {
  const navigate = useNavigate();
  const getBlank = useRecoilValue(blankOption);
  const getExplainSelect = useRecoilValue(explainSelect);
  const getTrace = useRecoilValue(traceOption);
  const setWorksheetData = useSetRecoilState(worksheet);
  const getContent: string = useRecoilValue(content);
  const setTitle = useSetRecoilState(title);
  const setType = useSetRecoilState(type);
  const setStep = useSetRecoilState(step);

  useEffect(() => {
    setTitle("Create")
    setType("step");
    setStep(2)
  }, [])

  const submit = async () => {
    setTitle("Create")
    setType("step");
    navigate("/loading/worksheet");
  };

  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "97.6%" }}>
      <SelectOption />
      <OptionModal />
      <button className="step-one-submit-wrapper" onClick={submit}>
        Next
      </button>
    </div>
  );
};
