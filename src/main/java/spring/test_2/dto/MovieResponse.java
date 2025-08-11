package spring.test_2.dto;

import lombok.Getter;
import org.springframework.stereotype.Repository;

@Getter
public class MovieResponse {

    private final Long id;
    private final String title;

    public MovieResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}
