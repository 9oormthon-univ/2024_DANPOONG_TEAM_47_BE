package univ.goormthon.kongju.domain.parking.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import univ.goormthon.kongju.domain.member.entity.Member;
import univ.goormthon.kongju.domain.parking.dto.request.ParkingAvailabilityRequest;
import univ.goormthon.kongju.domain.parking.dto.request.ParkingRegisterRequest;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final ParkingImageRepository parkingImageRepository;
    private final ParkingAvailabilityRepository parkingAvailabilityRepository;
    private final S3UploadService s3UploadService;

//    public List<String> uploadImages(List<MultipartFile> images) {
//        List<String> imageUrls = new ArrayList<>();
//        for (MultipartFile image : images) {
//            try {
//                String imageUrl = s3UploadService.upload(image);
//                imageUrls.add(imageUrl);
//            } catch (Exception e) {
//                throw new UploadFailedException(ErrorCode.FAILED_TO_UPLOAD);
//            }
//        }
//        return imageUrls;
//    }
    @Transactional
    public Parking registerParking(HttpSession session, ParkingRegisterRequest request) {
        Member member = validateMemberSession(session);

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
    public Parking updateParking(HttpSession session, Long parkingId, ParkingRegisterRequest request) {
        Member member = validateMemberSession(session);
        Parking parking = validateParkingOwnership(parkingId, member);

        parking.update(request);

        parkingImageRepository.deleteByParkingId(parkingId);
        uploadParkingImages(parkingId, request.getImages());

        parkingAvailabilityRepository.deleteByParkingId(parkingId);
        saveParkingAvailabilities(parkingId, request.getAvailabilities());

        return parking;
    }

    @Transactional
    public void deleteParking(HttpSession session, Long parkingId) {
        Member member = validateMemberSession(session);
        Parking parking = validateParkingOwnership(parkingId, member);

        parkingImageRepository.deleteByParkingId(parkingId);
        parkingAvailabilityRepository.deleteByParkingId(parkingId);
        parkingRepository.delete(parking);
    }

    private Member validateMemberSession(HttpSession session) {
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            throw new NotFoundException(ErrorCode.MEMBER_NOT_FOUND);
        }
        return member;
    }

    private Parking validateParkingOwnership(Long parkingId, Member member) {
        Parking parking = parkingRepository.findById(parkingId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.PARKING_NOT_FOUND));

        if (!parking.getMember().equals(member)) {
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
}
