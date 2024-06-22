import React, { useEffect, useState } from 'react';
import { Worker, Viewer } from '@react-pdf-viewer/core';
import { defaultLayoutPlugin } from '@react-pdf-viewer/default-layout';
import '@react-pdf-viewer/core/lib/styles/index.css';
import '@react-pdf-viewer/default-layout/lib/styles/index.css';
import * as pdfjsLib from 'pdfjs-dist';
import 'pdfjs-dist/build/pdf.worker.entry';
import "./PdfViewer.css"

pdfjsLib.GlobalWorkerOptions.workerSrc = new URL('pdfjs-dist/build/pdf.worker.min.js', import.meta.url).toString();


const PdfViewer: React.FC = () => {
  const s3PdfUrl = 'https://ponibucket.s3.ap-northeast-2.amazonaws.com/01c46b55-a7c2-46b9-8b2c-e800aa3f929d.pdf';
  const defaultLayoutPluginInstance = defaultLayoutPlugin();

  return (
    <div style={{ position: 'relative', height: '100vh', maxHeight: '100%', overflowX: "hidden"}}>
      <Worker workerUrl={pdfjsLib.GlobalWorkerOptions.workerSrc}>
          <Viewer fileUrl={s3PdfUrl} plugins={[defaultLayoutPluginInstance]} />
      </Worker>
      <button className="step-one-submit-wrapper">
        인쇄하기
      </button>
    </div>
  );
};

export default PdfViewer;
