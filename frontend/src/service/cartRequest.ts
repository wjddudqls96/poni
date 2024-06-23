import { BlankOption, Cart, TraceOption } from 'Cart';
import axiosInstance from './axiosConfig';
import { ResponseData } from 'response';
import { Worksheet } from 'worksheet';

export const getworkSheetData = async (traceOption:TraceOption, blankOption:BlankOption, explanation: boolean, content:string): Promise<Worksheet> => {

  const data:Cart = {
    traceOption: traceOption,
    blankOption: blankOption,
    explanation: explanation,
    content: content
  };

  try {
    const response = await axiosInstance.post('/api/v1/worksheet/cart', data);
    const responseData:ResponseData = response.data;
    
    if(responseData.code != "201") {
        throw console.error(responseData.message);
    }
    
    return responseData.data;

  } catch (error) {
    throw error;
  }
};
