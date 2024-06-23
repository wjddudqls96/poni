package com.epson.poni.repository.worksheet;

import com.epson.poni.model.User.User;
import com.epson.poni.model.worksheet.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findByUser(User user);
}
