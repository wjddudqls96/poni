import React from 'react';
import "./CartItem.css";
import dot from "../../assets/more_vert.png";

export const CartItem: React.FC<any> = ({ cart, onClick, isSelected }) => {
  
  const formatDate = (datTime: any) => {
    const date = new Date(datTime);

    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더합니다.
    const day = String(date.getDate()).padStart(2, '0');

    const formattedDate = `${year}.${month}.${day}`;
    return formattedDate;
  };

  return (
    <div className='cart-item-wrap' onClick={onClick}>
      <div className='cart-item-img'>
        <div className={`cart-item-check ${isSelected ? "cart-check" : ""}`}>
        </div>
      </div>
      <div className='cart-item-content'>
        <div className='cart-item-title'>
          {cart.sentence}
        </div>
        <div className='cart-item-time'>
          {formatDate(cart.date)}
        </div>
      </div>
      <div className='dot-wrap'>
        <img src={dot} width={20} height={20} />
      </div>
    </div>
  );
};
