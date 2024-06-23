import React from "react";
import banner from "../../assets/banner.png";
import "./Banner.css";

const Banner: React.FC = () => {
  return (
    <div className="banner-container">
      <img src={banner} width={"100%"} />
    </div>
  );
};

export default Banner;
