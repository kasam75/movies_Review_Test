package dto;

import lombok.Getter;

@Getter
public class MovieRequest {
    private String title;

    public MovieRequest(String title) {
        this.title = title;
    }
}
