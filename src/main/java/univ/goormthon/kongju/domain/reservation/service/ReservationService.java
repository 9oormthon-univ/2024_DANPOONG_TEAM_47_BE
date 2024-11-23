package univ.goormthon.kongju.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.repository.MemberRepository;
import univ.goormthon.kongju.domain.parking.entity.Parking;
import univ.goormthon.kongju.domain.parking.repository.ParkingRepository;
import univ.goormthon.kongju.domain.reservation.dto.request.ReservationRequest;
import univ.goormthon.kongju.domain.reservation.dto.response.ReservationResponse;
import univ.goormthon.kongju.domain.reservation.entity.Reservation;
import univ.goormthon.kongju.domain.reservation.entity.ReservationStatus;
import univ.goormthon.kongju.domain.reservation.repository.ReservationRepository;
import univ.goormthon.kongju.domain.vehicle.entity.Vehicle;
import univ.goormthon.kongju.domain.vehicle.repository.VehicleRepository;
import univ.goormthon.kongju.global.exception.NotFoundException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ParkingRepository parkingRepository;
    private final VehicleRepository vehicleRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public ReservationResponse reserve(String memberId, ReservationRequest request) {
        Member member = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        Parking parking = parkingRepository.findById(request.parkingId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.PARKING_NOT_FOUND));

        Vehicle vehicle = vehicleRepository.findById(request.vehicleId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.VEHICLE_NOT_FOUND));

        long durationInMinutes = Duration.between(request.startTime(), request.endTime()).toMinutes();
        int totalFee = (int) Math.ceil((double) durationInMinutes / 30) * parking.getRate();

        Reservation reservation = Reservation.builder()
                .memberId(member.getId())
                .parkingId(request.parkingId())
                .vehicleId(request.vehicleId())
                .reservationDate(request.reservationDate())
                .startTime(request.startTime())
                .endTime(request.endTime())
                .reservationStatus(ReservationStatus.ACCEPTED)
                .build();

        reservationRepository.save(reservation);

        return ReservationResponse.builder()
                .id(reservation.getId())
                .parkingId(reservation.getParkingId())
                .memberId(reservation.getMemberId())
                .vehicleId(reservation.getVehicleId())
                .reservationDate(reservation.getReservationDate().toString())
                .startTime(reservation.getStartTime().toString())
                .endTime(reservation.getEndTime().toString())
                .reservationStatus(reservation.getReservationStatus().name())
                .totalFee(totalFee)
                .build();
    }

    @Transactional(readOnly = true)
    public ReservationResponse getReservation(String memberId, Long reservationId) {
        Member member = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.RESERVATION_NOT_FOUND));

        if (!reservation.getMemberId().equals(member.getId())) {
            throw new NotFoundException(ErrorCode.RESERVATION_NOT_FOUND);
        }

        return ReservationResponse.builder()
                .id(reservation.getId())
                .parkingId(reservation.getParkingId())
                .memberId(reservation.getMemberId())
                .vehicleId(reservation.getVehicleId())
                .reservationDate(reservation.getReservationDate().toString())
                .startTime(reservation.getStartTime().toString())
                .endTime(reservation.getEndTime().toString())
                .reservationStatus(reservation.getReservationStatus().name())
                .totalFee(reservation.getTotalFee())
                .build();
    }

    @Transactional(readOnly = true)
    public List<ReservationResponse> getReservations(String memberId) {
        Member member = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        List<Reservation> reservations = reservationRepository.findByMemberId(member.getId());

        return reservations.stream()
                .map(reservation -> ReservationResponse.builder()
                        .id(reservation.getId())
                        .parkingId(reservation.getParkingId())
                        .memberId(reservation.getMemberId())
                        .vehicleId(reservation.getVehicleId())
                        .reservationDate(reservation.getReservationDate().toString())
                        .startTime(reservation.getStartTime().toString())
                        .endTime(reservation.getEndTime().toString())
                        .reservationStatus(reservation.getReservationStatus().name())
                        .totalFee(reservation.getTotalFee())
                        .build())
                .toList();
    }
}
