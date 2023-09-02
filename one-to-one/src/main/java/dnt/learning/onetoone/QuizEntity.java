package dnt.learning.onetoone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "quiz")
@Getter
@Setter
public class QuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY, mappedBy = "quiz", optional = false, cascade = CascadeType.ALL)
    private QuizResultEntity result;
}
