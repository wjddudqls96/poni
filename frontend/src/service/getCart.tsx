import axiosInstance from './axiosConfig';
import { ResponseData } from 'response';

export const getCartData = async (): Promise<any> => {

  try {
    const response = await axiosInstance.get('/api/v1/worksheet/');
    const responseData:ResponseData = response.data;
    
    return responseData;

  } catch (error) {
    throw error;
  }
};
