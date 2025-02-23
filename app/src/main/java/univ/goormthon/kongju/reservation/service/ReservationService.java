package univ.goormthon.kongju.reservation.service;

import org.springframework.stereotype.Service;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.reservation.domain.Reservation;
import univ.goormthon.kongju.reservation.utils.ReservationFinder;
import univ.goormthon.kongju.reservation.utils.ReservationRegistrant;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationFinder reservationFinder;
    private final ReservationRegistrant reservationRegistrant;

    public ReservationService(ReservationFinder reservationFinder, ReservationRegistrant reservationRegistrant) {
        this.reservationFinder = reservationFinder;
        this.reservationRegistrant = reservationRegistrant;
    }

    public List<Reservation> getReservations(Member member) {
        return reservationFinder.getReservations(member);
    }

    public Reservation reserve(Member member, Reservation request) {
        return reservationRegistrant.register(member, request);
    }
}
