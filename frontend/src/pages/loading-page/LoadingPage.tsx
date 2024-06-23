import React, { useEffect } from 'react'
import Loading from "../../assets/loadingCircle.gif";
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { cartIds, pdfUrl } from '../../store/Cart';
import { requestPdf } from '../../service/requestPdf';
import { useNavigate } from 'react-router-dom';

export const LoadingPage:React.FC = () => {
  const navigate = useNavigate();
  const getCartIds = useRecoilValue(cartIds);
  const setPdfUrl = useSetRecoilState(pdfUrl);

  useEffect(() => {
    const getResponse = async () => {
      const url:string = await requestPdf(getCartIds);
      setPdfUrl(url);
      console.log(url);
      
      navigate("/Pdfviewer");
    };
    getResponse();
  }, []);

  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "100%", display: "flex", alignItems:"center", flexDirection:"column"}}>
        
        <div style={{marginTop : "250px"}}>
        <img src={Loading} width={100} height={100}/>
        </div>
        <div>
        Study sheet
        </div>
        <div>
        Printing...
        </div>
    </div>
  )
}
