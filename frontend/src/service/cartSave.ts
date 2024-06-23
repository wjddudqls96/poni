import { BlankOption, Cart, TraceOption } from 'Cart';
import axiosInstance from './axiosConfig';
import { ResponseData } from 'response';
import { Worksheet } from 'worksheet';

export const saveCartData = async (worksheet:Worksheet, content:string): Promise<boolean> => {

  const data = {
    traceOption: worksheet.traceOption,
    blank: worksheet.blank,
    explanation: worksheet.explanation,
    content: content
  };

  try {
    const response = await axiosInstance.post('/api/v1/worksheet/save', data);
    const responseData:ResponseData = response.data;

    console.log(response.data);
    var isSuccess = false;

    if(responseData.code != "201") {
        throw console.error(responseData.message);
    }

    isSuccess = true;
    
    return isSuccess;

  } catch (error) {
    throw error;
  }
};
