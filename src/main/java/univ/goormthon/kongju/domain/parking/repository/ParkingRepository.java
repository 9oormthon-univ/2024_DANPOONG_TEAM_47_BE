package univ.goormthon.kongju.domain.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import univ.goormthon.kongju.domain.parking.entity.Parking;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

    /**
    Haversine formula를 이용하여 주어진 좌표를 중심으로 반경 내에 있는 주차장을 조회한다.
     6371은 지구 반지름을 의미
    @param latitude 중심 좌표의 위도
    @param longitude 중심 좌표의 경도
    @param radius 반경 (km)
    @return 반경 내에 있는 주차장 목록
     */
    @Query(value = "SELECT p FROM Parking p WHERE " +
            "(6371 * acos(cos(radians(:lat)) * cos(radians(p.latitude)) * cos(radians(p.longitude) - radians(:lng)) + sin(radians(:lat)) * sin(radians(p.latitude)))) <= :radius")
    List<Parking> findAllWithinRadius(@Param("lat") double latitude,
                                      @Param("lng") double longitude,
                                      @Param("radius") double radius);

    List<Parking> findAllByMemberId(long memberId);
}
