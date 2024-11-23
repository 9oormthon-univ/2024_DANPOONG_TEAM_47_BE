package univ.goormthon.kongju.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import univ.goormthon.kongju.domain.review.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponse getAllReviews(String memberId) {
        return ReviewResponse.builder()
                .reviews(reviewRepository.findAllByMemberId(Long.parseLong(memberId)))
                .build();
    }


}
