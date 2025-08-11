package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor

public class Review {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    public Review(String content, Movie movie) {
        this.content = content;
        this.movie = movie;
    }
    public void updateContent(String content) {
        this.content = content;
    }
}
