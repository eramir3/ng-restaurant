package com.restaurant.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CookRequest {

    private Long Id;

    @NotBlank(message = "Cook first name is required")
    private String firstName;

    @NotBlank(message = "Cook last name is required")
    private String lastName;

    @NotNull(message = "Cook birth date is required")
    @JsonFormat(pattern = "MM-dd-yyyy")
    private LocalDate birthDate;

    @Size(max = 20, message = "Cook remark must have a maximum of 20 characters")
    private String remarks;

    public CookRequest() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
