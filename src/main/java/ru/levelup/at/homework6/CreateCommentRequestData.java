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
public class CreateCommentRequestData {

    @JsonProperty("post_id")
    private int postId;
    private String name;
    private String email;
    private String body;
}
