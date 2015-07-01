package com.rubblesnask.types;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
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
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wail)) return false;

        Wail wail = (Wail) o;

        if (upVotes != wail.upVotes) return false;
        if (downVotes != wail.downVotes) return false;
        if (id != null ? !id.equals(wail.id) : wail.id != null) return false;
        if (pseudonym != null ? !pseudonym.equals(wail.pseudonym) : wail.pseudonym != null) return false;
        if (date != null ? !date.equals(wail.date) : wail.date != null) return false;
        return !(message != null ? !message.equals(wail.message) : wail.message != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (pseudonym != null ? pseudonym.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + upVotes;
        result = 31 * result + downVotes;
        return result;
    }
}
