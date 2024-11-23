package univ.goormthon.kongju.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univ.goormthon.kongju.domain.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
