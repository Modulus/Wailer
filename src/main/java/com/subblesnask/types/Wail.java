package com.subblesnask.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Modulus on 11.03.2015.
 */
public class Wail {

    public Wail() {
        date = LocalDateTime.now();
    }

    public Wail(String message){
        this.message = message;
        this.date = LocalDateTime.now();
    }

    @NotNull
    private Integer id;

    @Length(max = 50)
    private String pseudonym;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @NotEmpty
    @Length(max = 255, min = 15)
    private String message;

    private int upVotes;
    private int downVotes;

    @JsonProperty("timestamp")
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @JsonProperty("text")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("name")
    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @JsonProperty
    public int getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(int upVotes) {
        this.upVotes = upVotes;
    }

    @JsonProperty
    public int getDownVotes() {
        return downVotes;
    }

    public void setDownVotes(int downVotes) {
        this.downVotes = downVotes;
    }

    @JsonIgnore
    @Override
    public String toString() {
        return "Wail{" +
                "id=" + id +
                ", pseudonym='" + pseudonym + '\'' +
                ", date=" + date +
                ", message='" + message + '\'' +
                '}';
    }

    public void increaseUpVotes() {
        this.upVotes += 1;
    }

    public void increateDownVotes(){
        this.downVotes += 1;
    }
}
