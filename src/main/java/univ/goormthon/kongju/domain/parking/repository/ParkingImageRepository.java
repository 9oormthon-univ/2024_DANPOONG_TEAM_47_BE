package univ.goormthon.kongju.domain.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import univ.goormthon.kongju.domain.parking.entity.ParkingImage;

import java.util.List;

@Repository
public interface ParkingImageRepository extends JpaRepository<ParkingImage, Long> {
    void deleteByParkingId(Long parkingId);

    List<ParkingImage> findByParkingId(Long id);
}
