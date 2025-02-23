package univ.goormthon.kongju.reservation.utils;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.repository.ReservationRepository;
import univ.goormthon.kongju.reservation.domain.Reservation;

import java.util.List;

@Component
public class ReservationFinder {

    private final ReservationRepository reservationRepository;

    public ReservationFinder(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional(readOnly = true)
    public List<Reservation> getReservations(Member member) {
        return reservationRepository.findAllByMemberId(member.getId()).stream()
                .map(Reservation::of)
                .toList();
    }
}
