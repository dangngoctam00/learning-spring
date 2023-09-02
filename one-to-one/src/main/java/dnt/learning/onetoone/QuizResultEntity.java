package dnt.learning.onetoone;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "quiz_result")
@Getter
@Setter
public class QuizResultEntity {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = LAZY, optional = false)
    @JoinColumn(name = "quiz_id")
    @MapsId
    private QuizEntity quiz;
}
