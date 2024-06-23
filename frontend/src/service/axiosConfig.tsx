import axios from "axios";
import { useNavigate } from "react-router-dom";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
  },
});

axiosInstance.interceptors.request.use(
  config => {
    const token = localStorage.getItem("accessToken");
    console.log(token);
    
    if(!token) {
      window.location.href = "http://localhost:5173/"
    }


    if (token) {
      config.headers.Authorization = token;
    }
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

export default axiosInstance;
