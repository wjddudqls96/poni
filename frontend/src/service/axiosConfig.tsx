import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
    Authorization:
      "BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzdGF0aWMvdGVzdCIsIlVTRVJfTkFNRSI6IuyLrOq3nOugrCIsImV4cCI6MTcxOTAzNjk4M30.QEKlZahvVVaPP9sVm8ORW3f3Wr-AZ-XHLYo2mK7xiMY",
  },
});

export default axiosInstance;
