import React, { useEffect, useState } from 'react';
import { Worker, Viewer } from '@react-pdf-viewer/core';
import { defaultLayoutPlugin } from '@react-pdf-viewer/default-layout';
import '@react-pdf-viewer/core/lib/styles/index.css';
import '@react-pdf-viewer/default-layout/lib/styles/index.css';
import * as pdfjsLib from 'pdfjs-dist';
import 'pdfjs-dist/build/pdf.worker.entry';
import "./PdfViewer.css"
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { pdfUrl } from '../../store/Cart';
import { useNavigate } from 'react-router-dom';
import { step, title, type } from '../../store/NavBar';

pdfjsLib.GlobalWorkerOptions.workerSrc = new URL('pdfjs-dist/build/pdf.worker.min.js', import.meta.url).toString();


const PdfViewer: React.FC = () => {
  const navigate = useNavigate();
  const s3PdfUrl = useRecoilValue(pdfUrl);
  const defaultLayoutPluginInstance = defaultLayoutPlugin();
  const setTitle = useSetRecoilState(title);
  const setType = useSetRecoilState(type);
  const setStep = useSetRecoilState(step);

  useEffect(() => {
    setTitle("Print Preview")
    setType("step");
    setStep(0);
  }, []);

  const handleClick = () => {
    navigate("/print/option")
  }

  return (
    <div style={{ position: 'relative', height: '100vh', maxHeight: '100%', overflowX: "hidden"}}>
      {s3PdfUrl.length > 0  ? <Worker workerUrl={pdfjsLib.GlobalWorkerOptions.workerSrc}>
          <Viewer fileUrl={s3PdfUrl} plugins={[defaultLayoutPluginInstance]} />
      </Worker> : <>아직 없음</>}
      
      <button className="step-one-submit-wrapper" onClick={handleClick}>
        인쇄하기
      </button>
    </div>
  );
};

export default PdfViewer;
