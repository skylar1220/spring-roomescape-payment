package roomescape.service.booking.time.module;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roomescape.domain.time.ReservationTime;
import roomescape.exception.RoomEscapeException;
import roomescape.repository.ReservationRepository;
import roomescape.repository.ReservationTimeRepository;

@Service
@Transactional
public class TimeDeleteService {

    private final ReservationTimeRepository reservationTimeRepository;
    private final ReservationRepository reservationRepository;

    public TimeDeleteService(ReservationTimeRepository reservationTimeRepository,
                             ReservationRepository reservationRepository) {
        this.reservationTimeRepository = reservationTimeRepository;
        this.reservationRepository = reservationRepository;
    }

    public void deleteTime(Long timeId) {
        ReservationTime reservationTime = findTimeById(timeId);
        validateDeletable(reservationTime);
        reservationTimeRepository.delete(reservationTime);
    }

    private ReservationTime findTimeById(Long timeId) {
        return reservationTimeRepository.findById(timeId)
                .orElseThrow(() -> new RoomEscapeException(
                        "잘못된 잘못된 예약시간 정보 입니다.",
                        "time_id : " + timeId
                ));
    }

    private void validateDeletable(ReservationTime reservationTime) {
        if (reservationRepository.existsByTimeId(reservationTime.getId())) {
            throw new RoomEscapeException(
                    "해당 시간에 예약이 존재해서 삭제할 수 없습니다.",
                    "예약 시간 : " + reservationTime.getStartAt()
            );
        }
    }
}
