# 예약 시 결제단계 추가
- [x] 사용자가 날짜, 테마, 시간을 선택하고 결제를 해야 예약할 수 있도록 변경합니다.
- [x] 결제 기능은 외부의 결제 서비스를 사용하여 외부의 결제 API를 연동하세요.
- [x] 결제 승인 API 호출에 실패 한 경우, 안전하게 에러를 핸들링 하세요.
- [x] 사용자는 예약 실패 시, 결제 실패 사유를 알 수 있어야 합니다.

# 내 예약 페이지 변경
- [x] 내 예약 페이지에서 예약 정보 외에 결제 정보도 함께 볼 수 있도록 수정합니다.
- [x] 내 예약 페이지에서 필수로 확인 할 수 있어야 하는 결제 정보는 paymentKey, 결제 금액입니다.
- [x] 그 외 결제 정보는 DB에 선택적으로 저장합니다.
- [x] 내 예약 목록 조회 API 에 결제 관련 필드를 추가합니다.
- [x] response 설계는 자유롭게 할 수 있습니다.
- [ ] 자신만의 기준을 세워 DB를 설계하고, 기능을 구현해보세요.
  - [ ] toss 결제 승인 API 응답의 필드 중 어떤 데이터를 저장할 것인가? 그 이유는 무엇인가?
  - [ ] 결제 정보를 새로운 테이블로 저장할 것인가? 예약에 컬럼만 추가할 것인가? 그 이유는 무엇인가?
  - [ ] 예약과 결제를 분리한다면 이유는 무엇인가? 둘은 어떤 관계로 설정할 것인가? 그 이유는 무엇인가?

# 리팩토링 목록
- [x] 시크릿키 숨기기
- [x] 시크릿키와 콜론 분리
- [x] 타임아웃 적용
  - [x] 타임아웃 예외 내 예외로 전환하기
- [ ] 예약,결제 쿼리 두번 쏘는 문제 해결
- [ ] 예약시 결제 정보 db 저장 테스트 추가
- [ ] db에 저장할 정보 고민하기
- [ ] base url, default header 적용
- [ ] yml 파일로의 전환
- [ ] 결제 관련 EtoE 테스트 추가
  - [ ] ReservationPayment 네이밍 애매
- [ ] 대기 취소 왜 안돼?
