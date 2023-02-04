package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class PasswordDto {
    private String currentPassword;
    private String newPassword;
}
