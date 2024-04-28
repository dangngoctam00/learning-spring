package dnt.learning.jpaprojection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PostRepositoryCustomImpl implements PostRepositoryCustom{

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<PostEntity> findByCustom() {
        var cb = entityManager.getCriteriaBuilder();
        Predicate[] predicates = new Predicate[2];
        var cr = cb.createQuery(PostEntity.class);
        var root = cr.from(PostEntity.class);
        predicates[0] = cb.isNull(root.get("title"));
        predicates[1] = cb.like(root.get("content"), "chair%");
        var and = cb.or(predicates);
        var dateCreated = cr.select(root).where(cb.and(cb.and(cb.isNotNull(root.get("dateCreated"))), and));
        entityManager.createQuery(dateCreated).getResultList();
        return List.of();
    }
}
