package univ.goormthon.kongju.reservation.utils;

import org.springframework.stereotype.Component;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.repository.ReservationRepository;
import univ.goormthon.kongju.reservation.domain.Reservation;

@Component
public class ReservationRegistrant {

    private final ReservationRepository reservationRepository;

    public ReservationRegistrant(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation register(Member member, Reservation request) {
        reservationRepository.save(request.toEntity(member));
        return request;
    }
}
