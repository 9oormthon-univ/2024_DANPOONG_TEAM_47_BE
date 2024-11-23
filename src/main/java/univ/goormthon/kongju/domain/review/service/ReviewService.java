package univ.goormthon.kongju.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ.goormthon.kongju.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public void addReview() {}

    public void getReviewFindByParkingId() {}

    public void getReviewFindByMemberId() {}

    public void deleteReview() {}


}
