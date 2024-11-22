package univ.goormthon.kongju.domain.vehicle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.vehicle.entity.Vehicle;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findAllByMemberId(Long id);
}
