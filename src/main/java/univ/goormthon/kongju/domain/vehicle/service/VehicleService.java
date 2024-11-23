package univ.goormthon.kongju.domain.vehicle.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.repository.MemberRepository;
import univ.goormthon.kongju.domain.vehicle.dto.request.VehicleRequest;
import univ.goormthon.kongju.domain.vehicle.dto.response.VehicleResponse;
import univ.goormthon.kongju.domain.vehicle.entity.Vehicle;
import univ.goormthon.kongju.domain.vehicle.entity.VehicleType;
import univ.goormthon.kongju.domain.vehicle.repository.VehicleRepository;
import univ.goormthon.kongju.global.exception.NotFoundException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<VehicleResponse> getVehicles(String memberId) {
        Member member = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        List<Vehicle> vehicles = vehicleRepository.findAllByMember(member);

        return vehicles.stream()
                .map(vehicle -> VehicleResponse.builder()
                        .id(vehicle.getId())
                        .vehicleType(vehicle.getVehicleType())
                        .vehicleNumber(vehicle.getVehicleNumber())
                        .build())
                .collect(Collectors.toList());
    }

    @Transactional
    public VehicleResponse addVehicle(String memberId, VehicleRequest request) {
        Member member = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

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
    public VehicleResponse updateVehicle(String memberId, VehicleRequest request) {
        Member member = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        Vehicle vehicle = vehicleRepository.findByMember(member)
                .orElseThrow(() -> new NotFoundException(ErrorCode.VEHICLE_NOT_FOUND));

        vehicle.update(request.vehicleType(), request.vehicleNumber());

        return VehicleResponse.builder()
                .id(vehicle.getId())
                .vehicleType(vehicle.getVehicleType())
                .vehicleNumber(vehicle.getVehicleNumber())
                .build();
    }

    @Transactional
    public void deleteVehicle(String memberId, Long vehicleId) {
        Member member = memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.VEHICLE_NOT_FOUND));

        vehicleRepository.delete(vehicle);
    }
}
