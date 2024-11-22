package univ.goormthon.kongju.domain.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.vehicle.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByMember(Member member);

    Optional<Vehicle> findByMember(Member member);
}
