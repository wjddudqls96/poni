import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
    Authorization:
      "BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzdGF0aWMvdGVzdCIsIlVTRVJfTkFNRSI6IuygleyYgeu5iCIsImV4cCI6MTcxOTA4MDQyMn0.6Andc6mGreBOdXauxi9kqpnBI-NGhsHoUznt-6j7OfU",
  },
});

export default axiosInstance;
