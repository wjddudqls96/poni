import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
    "Access-Control-Allow-Origin": "*",
    Authorization:
      "BEARER eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzdGF0aWMvdGVzdCIsIlVTRVJfTkFNRSI6Iuygle2YhOyasCIsImV4cCI6MTcxOTEwMDU5MX0.5zWtk9SM5DaYDwhjszSM5eWNVy5yirttVi17cN46eeg",
  },
});

export default axiosInstance;
