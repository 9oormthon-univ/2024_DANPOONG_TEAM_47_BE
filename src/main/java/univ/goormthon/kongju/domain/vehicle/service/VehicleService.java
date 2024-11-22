package univ.goormthon.kongju.domain.vehicle.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.repository.MemberRepository;
import univ.goormthon.kongju.domain.vehicle.dto.VehicleRequestDto;
import univ.goormthon.kongju.domain.vehicle.dto.VehicleResponseDto;
import univ.goormthon.kongju.domain.vehicle.dto.request.VehicleRequest;
import univ.goormthon.kongju.domain.vehicle.dto.response.VehicleResponse;
import univ.goormthon.kongju.domain.vehicle.entity.Vehicle;
import univ.goormthon.kongju.domain.vehicle.entity.VehicleType;
import univ.goormthon.kongju.domain.vehicle.repository.VehicleRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<VehicleResponse> getVehiclesByMemberId(HttpSession session) {
        Member member = (Member) session.getAttribute("member");

        List<Vehicle> vehicles = vehicleRepository.findAllByMemberId(member.getId());

        return vehicles.stream()
                .map(vehicle -> VehicleResponse.builder()
                        .id(vehicle.getId())
                        .vehicleType(vehicle.getVehicleType())
                        .vehicleNumber(vehicle.getVehicleNumber())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public VehicleResponse addVehicle(Long memberId, VehicleRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        Vehicle vehicle = Vehicle.builder()
                .member(member)
                .vehicleType(VehicleType.of(request.vehicleType()))
                .vehicleNumber(request.vehicleNumber())
                .build();

        vehicleRepository.save(vehicle);

        return VehicleResponse.builder()
                .id(vehicle.getId())
                .vehicleType(vehicle.getVehicleType())
                .vehicleNumber(vehicle.getVehicleNumber())
                .build();
    }

    @Transactional
    public VehicleResponse updateVehicle(Long vehicleId, VehicleRequest requestDto) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle ID"));

        vehicle.update(requestDto.getVehicleType(), requestDto.getVehicleNumber());

        return VehicleResponse.builder()
                .id(vehicle.getId())
                .vehicleType(vehicle.getVehicleType())
                .vehicleNumber(vehicle.getVehicleNumber())
                .build();
    }

    @Transactional
    public void deleteVehicle(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid vehicle ID"));

        vehicleRepository.delete(vehicle);
    }
}
