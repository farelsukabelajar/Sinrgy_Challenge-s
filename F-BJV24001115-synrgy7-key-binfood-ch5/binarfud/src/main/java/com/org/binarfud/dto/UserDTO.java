package com.org.binarfud.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UserDTO {
    private UUID usersId;
    private String username;
    private String emailAddress;
    private String password;
    private List<OrderDTO> orders; // Optional, depending on your use case
}
