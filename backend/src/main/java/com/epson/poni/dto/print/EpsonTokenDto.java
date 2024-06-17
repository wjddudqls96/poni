package com.epson.poni.dto.print;

import lombok.Data;

@Data
public class EpsonTokenDto {
    String token_type;
    String access_token;
    Integer expires_in;
    String refresh_token;
    String subject_type;
    String subject_id;
}
