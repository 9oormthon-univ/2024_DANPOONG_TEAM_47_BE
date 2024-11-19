package univ.goormthon.kongju.domain.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univ.goormthon.kongju.domain.parking.entity.LandCertification;

@Repository
public interface LandCertificationRepository extends JpaRepository<LandCertification, Long> {
}