package ru.levelup.at.homework6;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequestData {

    @JsonProperty("user_id")
    private int userId;
    private String user;
    private String title;
    private String body;
}
