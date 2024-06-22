import React, { useState } from 'react';
import 'react-pdf/dist/esm/Page/AnnotationLayer.css';
import TestPdf from '../../assets/test2.pdf'; // 경로 확인

const PdfViewer: React.FC = () => {

  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "100%" }}>
      <iframe src={TestPdf} width="99%" height="90%"/>
    </div>
  );
};

export default PdfViewer;
