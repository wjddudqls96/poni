package com.epson.poni.service.worksheet;

import com.epson.poni.dto.cart.CartListAllResponse;
import com.epson.poni.model.User.User;
import com.epson.poni.model.worksheet.Cart;
import com.epson.poni.model.worksheet.Explanation;
import com.epson.poni.repository.worksheet.CartRepository;
import com.epson.poni.repository.worksheet.ExplanationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorksheetGetService {
    private final CartRepository cartRepository;
    private final ExplanationRepository explanationRepository;

    public List<CartListAllResponse> getListAll(Authentication authentication) {
        List<CartListAllResponse> cartListAllResponseList = new ArrayList<>();

        User user = (User) authentication.getPrincipal();
        List<Cart> cartList = cartRepository.findByUser(user);

        for (Cart cart : cartList) {
            List<Explanation> explanationList = explanationRepository.findByCart(cart);
            for (Explanation explanation : explanationList) {
                String sentence = explanation.getSentence();

                CartListAllResponse cartListAllResponse = new CartListAllResponse();
                cartListAllResponse.setCartId(cart.getId());
                cartListAllResponse.setDate(cart.getDate());
                cartListAllResponse.setSentence(sentence);

                cartListAllResponseList.add(cartListAllResponse);
            }
        }

        return cartListAllResponseList;
    }

}
