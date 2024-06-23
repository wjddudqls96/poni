import React, { useEffect } from 'react';
import { Preview } from '../../components/common/preview/Preview';
import './PreviewPage.css';
import { useRecoilValue } from 'recoil';
import { worksheet } from '../../store/Worksheet';
import { Worksheet } from 'worksheet';
import { saveCartData } from '../../service/cartSave';
import { content } from '../../store/Content';
import { useNavigate } from 'react-router-dom';

export const PreviewPage: React.FC = () => {
  const getWorksheet = useRecoilValue(worksheet);
  const getContent = useRecoilValue(content);
  const useNavige = useNavigate();

  const submit = async () => {
    console.log(getWorksheet);
    console.log(getContent);
    
    
    const isSuccess = await saveCartData(getWorksheet, getContent);

    if(isSuccess) {
      useNavige('/main');
    }
  }

  return (
    <div className='preview-main'>
      <div className='preview-page-container'>
        <Preview title='Write along' type='trace'/>
        <Preview title='Create a glossary' type='explain'/>
        <Preview title='Blank question' type='blank'/>
      </div>
      <div className='preview-button-wrap'>
        <button className='preview-btn generate' onClick={submit}>Save</button>
      </div>
    </div>
  )
};
