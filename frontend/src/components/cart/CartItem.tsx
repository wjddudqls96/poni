import React, { useState } from 'react'
import "./CartItem.css"
import dot from "../../assets/more_vert.png"

export const CartItem: React.FC = () => {
    const [select, setSelect] = useState<boolean>(false);

    const handleSelect = () => {
        setSelect(!select);
    }

  return (
    <div className='cart-item-wrap' onClick={handleSelect}>
        <div className='cart-item-img'>
            <div className={`cart-item-check ${select ? "cart-check" : ""}`}>
            </div>
        </div>
        <div className='cart-item-content'>
            <div className='cart-item-title'>
                나는 축구가 좋다.
            </div>
            <div className='cart-item-time'>
                2024.06.10
            </div>
        </div>
        <div className='dot-wrap'>
            <img src={dot} width={20} height={20}/>
        </div>
    </div>
  )
}
