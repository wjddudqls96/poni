package com.epson.poni.repository.worksheet;

import com.epson.poni.model.worksheet.Cart;
import com.epson.poni.model.worksheet.Trace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TraceRepository  extends JpaRepository<Trace,Long> {
    Trace findByCart(Cart cart);
}
