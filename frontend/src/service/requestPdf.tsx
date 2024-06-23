import { BlankOption, Cart, TraceOption } from 'Cart';
import axiosInstance from './axiosConfig';
import { ResponseData } from 'response';

export const requestPdf = async (cardIds: any): Promise<string> => {

  const data = {
    cartIds: cardIds
  };

  try {
    const response = await axiosInstance.post('/api/v1/worksheet/', data);
    const responseData:ResponseData = response.data;

    console.log(response.data);

    if(responseData.code != "200") {
        throw console.error(responseData.message);
    }
    
    return responseData.data;

  } catch (error) {
    throw error;
  }
};
