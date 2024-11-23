package univ.goormthon.kongju.domain.parking.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.member.repository.MemberRepository;
import univ.goormthon.kongju.domain.parking.dto.request.ParkingAvailabilityRequest;
import univ.goormthon.kongju.domain.parking.dto.request.ParkingRegisterRequest;
import univ.goormthon.kongju.domain.parking.dto.response.ParkingRegisterResponse;
import univ.goormthon.kongju.domain.parking.entity.AvailableDay;
import univ.goormthon.kongju.domain.parking.entity.Parking;
import univ.goormthon.kongju.domain.parking.entity.ParkingAvailability;
import univ.goormthon.kongju.domain.parking.entity.ParkingImage;
import univ.goormthon.kongju.domain.parking.repository.ParkingAvailabilityRepository;
import univ.goormthon.kongju.domain.parking.repository.ParkingImageRepository;
import univ.goormthon.kongju.domain.parking.repository.ParkingRepository;
import univ.goormthon.kongju.global.exception.NotFoundException;
import univ.goormthon.kongju.global.exception.UnauthorizedException;
import univ.goormthon.kongju.global.exception.UploadFailedException;
import univ.goormthon.kongju.global.exception.dto.ErrorCode;
import univ.goormthon.kongju.global.service.S3UploadService;

import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParkingService {

    private final MemberRepository memberRepository;
    private final ParkingRepository parkingRepository;
    private final ParkingImageRepository parkingImageRepository;
    private final ParkingAvailabilityRepository parkingAvailabilityRepository;
    private final S3UploadService s3UploadService;

    @Transactional(readOnly = true)
    public List<Parking> getNearbyParkings(Double latitude, Double longitude, Double radius) {
        return parkingRepository.findAllWithinRadius(latitude, longitude, radius);
    }

    @Transactional(readOnly = true)
    public List<Parking> getMyParkings(String memberId) {
        return parkingRepository.findAllByMemberId(Long.parseLong(memberId));
    }

    @Transactional
    public Parking registerParking(String memberId, ParkingRegisterRequest request) {
        Member member = validateMember(memberId);

        Parking parking = Parking.builder()
                .member(member)
                .name(request.getName())
                .address(request.getAddress())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .carCapacity(request.getCarCapacity())
                .pmCapacity(request.getPmCapacity())
                .description(request.getDescription())
                .rate(request.getRate())
                .build();

        parkingRepository.save(parking);

        uploadParkingImages(parking.getId(), request.getImages());
        saveParkingAvailabilities(parking.getId(), request.getAvailabilities());

        return parking;
    }

    @Transactional
    public Parking updateParking(String memberId, Long parkingId, ParkingRegisterRequest request) {
        Member member = validateMember(memberId);
        Parking parking = validateParkingOwnership(parkingId, member);

        parking.update(request);

        parkingImageRepository.deleteByParkingId(parkingId);
        uploadParkingImages(parkingId, request.getImages());

        parkingAvailabilityRepository.deleteByParkingId(parkingId);
        saveParkingAvailabilities(parkingId, request.getAvailabilities());

        return parking;
    }

    @Transactional
    public void deleteParking(String memberId, Long parkingId) {
        Member member = validateMember(memberId);
        Parking parking = validateParkingOwnership(parkingId, member);

        parkingImageRepository.deleteByParkingId(parkingId);
        parkingAvailabilityRepository.deleteByParkingId(parkingId);
        parkingRepository.delete(parking);
    }

    private Member validateMember(String memberId) {
        return memberRepository.findById(Long.parseLong(memberId))
                .orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
    }

    private Parking validateParkingOwnership(Long parkingId, Member member) {
        Parking parking = parkingRepository.findById(parkingId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.PARKING_NOT_FOUND));

        if (!parking.getMember().equals(member)) {
            log.info("member: {}, parkingOwner: {}", member.getId(), parking.getMember().getId());
            throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
        }
        return parking;
    }

    private void uploadParkingImages(Long parkingId, List<MultipartFile> images) {
        for (MultipartFile image : images) {
            try {
                String imageUrl = s3UploadService.upload(image);
                ParkingImage parkingImage = ParkingImage.builder()
                        .parkingId(parkingId)
                        .imageUrl(imageUrl)
                        .build();
                parkingImageRepository.save(parkingImage);
            } catch (Exception e) {
                throw new UploadFailedException(ErrorCode.FAILED_TO_UPLOAD);
            }
        }
    }

    private void saveParkingAvailabilities(Long parkingId, List<ParkingAvailabilityRequest> availabilities) {
        for (ParkingAvailabilityRequest availability : availabilities) {
            ParkingAvailability parkingAvailability = ParkingAvailability.builder()
                    .parkingId(parkingId)
                    .day(AvailableDay.of(availability.day()))
                    .startTime(LocalTime.parse(availability.startTime()))
                    .endTime(LocalTime.parse(availability.endTime()))
                    .build();
            parkingAvailabilityRepository.save(parkingAvailability);
        }
    }

    public ParkingRegisterResponse EntitytoDto(Parking parking) {
        return new ParkingRegisterResponse(parking,
                parkingAvailabilityRepository.findByParkingId(parking.getId()),
                parkingImageRepository.findByParkingId(parking.getId()));
    }
}
