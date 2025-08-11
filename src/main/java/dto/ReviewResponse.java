package dto;

import lombok.Getter;

@Getter

public class ReviewResponse {
    private final Long id;
    private final String title;

    public ReviewResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
