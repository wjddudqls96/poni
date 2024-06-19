package com.epson.poni.dto.user;

import com.epson.poni.model.User.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private Long id;
    private String email;
    private String userName;
    private String language;
    private String deviceId;
    private Role role;
}
