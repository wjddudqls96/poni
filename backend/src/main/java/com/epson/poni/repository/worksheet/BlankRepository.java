package com.epson.poni.repository.worksheet;

import com.epson.poni.model.worksheet.Blank;
import com.epson.poni.model.worksheet.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlankRepository  extends JpaRepository<Blank,Long> {
    List<Blank> findByCart(Cart cart);
}
