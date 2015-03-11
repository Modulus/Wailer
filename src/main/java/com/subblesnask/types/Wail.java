package com.subblesnask.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by Modulus on 11.03.2015.
 */
public class Wail {

    public Wail() {
        date = LocalDate.now();
    }

    public Wail(String message){
        this.message = message;
        this.date = LocalDate.now();
    }

    @Length(max = 50)
    private String pseudonym;

    @NotNull
    private LocalDate date;

    @NotNull
    @NotEmpty
    @Length(max = 255, min = 15)
    private String message;

    @JsonProperty
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @JsonProperty
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty
    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }
}
