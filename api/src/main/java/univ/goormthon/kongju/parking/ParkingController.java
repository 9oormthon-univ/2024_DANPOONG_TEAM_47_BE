package univ.goormthon.kongju.parking;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import univ.goormthon.kongju.jwt.MemberContext;
import univ.goormthon.kongju.member.domain.Member;
import univ.goormthon.kongju.member.utils.MemberFinder;
import univ.goormthon.kongju.parking.domain.Parking;
import univ.goormthon.kongju.parking.dto.ParkingResponse;
import univ.goormthon.kongju.parking.service.ParkingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parkings")
public class ParkingController {

    private final ParkingService parkingService;
    private final MemberFinder memberFinder;

    public ParkingController(ParkingService parkingService, MemberFinder memberFinder) {
        this.parkingService = parkingService;
        this.memberFinder = memberFinder;
    }

    @GetMapping("/nearby")
    public ResponseEntity<List<ParkingResponse>> getNearbyParkings(@RequestParam Double latitude,
                                                                   @RequestParam Double longitude,
                                                                   @RequestParam(defaultValue = "0.5") Double radius) {
        List<Parking> parkings = parkingService.getNearByParkings(latitude, longitude, radius);
        return ResponseEntity.ok(parkings.stream().map(ParkingResponse::of).toList());
    }


    @GetMapping("/my")
    public ResponseEntity<List<ParkingResponse>> getMyParkings() {
        Member member = getMember();
        return ResponseEntity.ok(parkingService.getMyParkings(member)
                .stream()
                .map(ParkingResponse::of).toList());
    }

//    @PostMapping("/register")
//    public ResponseEntity<ParkingResponse> registerParking(
//            @RequestPart("request") ParkingRegisterRequest request,
//            @RequestPart("images") List<MultipartFile> images) {
//        Member member = getMember();
//        request.setImages(images);
//        ParkingResponse response = parkingService.registerParking(member, request);
//        return ResponseEntity.ok(response);
//    }
//
//
//    @PutMapping("/update")
//    public ResponseEntity<ParkingResponse> updateParking(@RequestParam Long parkingId,
//                                                         @RequestPart("request") ParkingRegisterRequest request,
//                                                         @RequestPart("images") List<MultipartFile> images) {
//        Member member = getMember();
//        request.setImages(images);
//        return ResponseEntity.ok(parkingService.updateParking(member, parkingId, request));
//    }
//
//    @DeleteMapping("/delete")
//    public ResponseEntity<Void> deleteParking(@RequestParam Long parkingId) {
//        Member member = getMember();
//        parkingService.deleteParking(member, parkingId);
//        return ResponseEntity.noContent().build();
//    }

    private Member getMember() {
        String email = MemberContext.getCurrentMemberEmail();
        return memberFinder.findByEmail(email);
    }
}
