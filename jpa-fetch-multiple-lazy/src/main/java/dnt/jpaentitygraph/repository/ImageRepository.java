package dnt.jpaentitygraph.repository;

import dnt.jpaentitygraph.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> { }
