import React, { useEffect, useState } from 'react';
import { CartItem } from '../../components/cart/CartItem';
import './CartPage.css';
import { getCartData } from '../../service/getCart';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { cartIds } from '../../store/Cart';
import { step, title, type } from '../../store/NavBar';

export const CartPage: React.FC = () => {
  const [carts, setCarts] = useState<any[]>([]); // 상태를 빈 배열로 초기화
  const [selectedItems, setSelectedItems] = useState<any[]>([]); // 선택된 아이템 상태 추가
  const setCardIds = useSetRecoilState(cartIds)
  const navigate = useNavigate();
  const setTitle = useSetRecoilState(title);
  const setType = useSetRecoilState(type);
  const setStep = useSetRecoilState(step);
  

  useEffect(() => {
    setTitle("List")
    setType("step");
    setStep(0);
    const getCart = async () => {
      try {
        const body = await getCartData();
        console.log(body);
        setCarts(body);
      } catch (error) {
        console.error('Error fetching cart data:', error);
      }
    }
    getCart();
  }, []);

  useEffect(() => {
    console.log(selectedItems);
    
  }, [selectedItems])

  const handleItemClick = (cart: any) => {
    setSelectedItems(prevSelectedItems => {
      if (prevSelectedItems.includes(cart)) {
        return prevSelectedItems.filter(item => item !== cart); // 리스트에서 제거
      } else {
        return [...prevSelectedItems, cart]; // 리스트에 추가
      }
    });
  };

  const submit = () => {
    const set = new Set();
    selectedItems.map((value, index) => set.add(value.cartId));
    const myArray = Array.from(set);
    setCardIds(myArray);
    navigate("/loading/print")
  }

  return (
    <div style={{ position: "relative", height: "100vh", maxHeight: "100%", backgroundColor: "white" }}>
      <div style={{ height: "30px" }}></div>
      {carts.length > 0 ? (
        carts.map((cart: any, index: number) => (
          <CartItem
            key={index}
            cart={cart}
            onClick={() => handleItemClick(cart)} // 클릭 이벤트 핸들러 추가
            isSelected={selectedItems.includes(cart)} // 선택된 상태를 전달
          />
        ))
      ) : <></>}
      <div style={{ height: "30px" }}></div>
      <button className='cart-submit-wrapper' onClick={submit}>출력하기</button>
    </div>
  );
};
