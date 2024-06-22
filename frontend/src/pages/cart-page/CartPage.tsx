import React from 'react'
import { CartItem } from '../../components/cart/CartItem'
import "./CartPage.css"

export const CartPage:React.FC = () => {
  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "100%", backgroundColor:"white"}}>
      <div style={{height: "30px"}}></div>
        <CartItem />
        <CartItem />
        <CartItem />
        <CartItem />
        <CartItem />
        <CartItem />
        <div style={{height: "30px"}}></div>
        <button className='cart-submit-wrapper'>출력하기</button>
    </div>
  )
}
