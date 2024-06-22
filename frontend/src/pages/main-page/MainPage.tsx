import React, { useEffect } from "react";
import Banner from "../../components/home/Banner";
import PrintPageBtn from "../../components/home/PrintPageBtn";
import WorksheetPageBtn from "../../components/home/WorksheetPageBtn";
import DictationPageBtn from "../../components/home/DictationPageBtn";
import { useLocation } from "react-router-dom";

const MainPage: React.FC = () => {
  const location = useLocation();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const accessToken = params.get('accessToken');

    if (accessToken) {
      // 액세스 토큰을 로컬 스토리지나 상태 관리에 저장
      localStorage.setItem('accessToken', accessToken);
      console.log('Access Token:', localStorage.getItem("accessToken"));
    }
  }, [location]);

  
  return (
    <div
      style={{
        position: "relative",
        height: "100vh",
        maxHeight: "98%",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <Banner />
      <WorksheetPageBtn />
      <DictationPageBtn />
      <PrintPageBtn />
    </div>
  );
};

export default MainPage;
