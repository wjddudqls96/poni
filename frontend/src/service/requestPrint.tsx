import { BlankOption, Cart, TraceOption } from 'Cart';
import axiosInstance from './axiosConfig';
import { ResponseData } from 'response';

export const requestPrint = async (data: any): Promise<any> => {

  try {
    const response = await axiosInstance.post('api/print/worksheetPrint', data);
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
