import React from "react";
import Banner from "../../components/home/Banner";
import PrintPageBtn from "../../components/home/PrintPageBtn";
import WorksheetPageBtn from "../../components/home/WorksheetPageBtn";
import DictationPageBtn from "../../components/home/DictationPageBtn";

const MainPage: React.FC = () => {
  return (
    <div>
      <Banner />
      <WorksheetPageBtn />
      <DictationPageBtn />
      <PrintPageBtn />
    </div>
  );
};

export default MainPage;
