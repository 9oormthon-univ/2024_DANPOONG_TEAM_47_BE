package univ.goormthon.kongju.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univ.goormthon.kongju.domain.reservation.entity.Reservation;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMemberId(Long id);
}
