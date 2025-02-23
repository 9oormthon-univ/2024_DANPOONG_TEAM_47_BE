package univ.goormthon.kongju.reservation;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import univ.goormthon.kongju.jwt.MemberContext;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.member.utils.MemberFinder;
import univ.goormthon.kongju.reservation.domain.Reservation;
import univ.goormthon.kongju.reservation.dto.ReservationRequest;
import univ.goormthon.kongju.reservation.dto.ReservationResponse;
import univ.goormthon.kongju.reservation.service.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final MemberFinder memberFinder;
    private final ReservationService reservationService;

    public ReservationController(MemberFinder memberFinder, ReservationService reservationService) {
        this.memberFinder = memberFinder;
        this.reservationService = reservationService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ReservationResponse>> getReservations(){
        Member member = getMember();
        List<Reservation> reservations = reservationService.getReservations(member);
        return ResponseEntity.ok(reservations.stream().map(ReservationResponse::of).toList());
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> reserve(@RequestBody ReservationRequest request) {
        Member member = getMember();
        Reservation requestReservation = request.toDomain();
        Reservation reservation = reservationService.reserve(member, requestReservation);
        return ResponseEntity.ok(ReservationResponse.of(reservation));
    }

    private Member getMember() {
        String email = MemberContext.getCurrentMemberEmail();
        return memberFinder.findByEmail(email);
    }

}
