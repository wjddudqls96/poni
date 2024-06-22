import React, { useEffect } from 'react';
import { Preview } from '../../components/common/preview/Preview';
import './PreviewPage.css';
import { useRecoilValue } from 'recoil';
import { worksheet } from '../../store/Worksheet';
import { Worksheet } from 'worksheet';

export const PreviewPage: React.FC = () => {

  return (
    <div className='preview-main'>
      <div className='preview-page-container'>
        <Preview title='따라쓰기' type='trace'/>
        <Preview title='용어 설명' type='explain'/>
        <Preview title='빈칸문제' type='blank'/>
      </div>
      <div className='preview-button-wrap'>
        <button className='preview-btn generate'>담기</button>
      </div>
    </div>
  )
};
