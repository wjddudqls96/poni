import React, { useState } from 'react';
import { Document, Page, pdfjs } from 'react-pdf';
import 'react-pdf/dist/esm/Page/AnnotationLayer.css';
import TestPdf from '../../assets/test.pdf'; // 경로 확인

// PDF.js worker 설정 (필수)
pdfjs.GlobalWorkerOptions.workerSrc = `//cdnjs.cloudflare.com/ajax/libs/pdf.js/${pdfjs.version}/pdf.worker.js`;

const PdfViewer: React.FC = () => {
  const [numPages, setNumPages] = useState<number | null>(null);
  const [pageNumber, setPageNumber] = useState(1);

  const onDocumentLoadSuccess = ({ numPages }: any) => {
    setNumPages(numPages);
  };

  return (
    <div>
      <Document file='../../assets/test.pdf' onLoadSuccess={onDocumentLoadSuccess} onLoadError={console.error}>
                <Page pageNumber={pageNumber}/>
      </Document>
    </div>
  );
};

export default PdfViewer;
