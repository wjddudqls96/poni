package com.epson.poni.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoUpdateRequestDto {
    private String language;
    private String deviceId;
}
