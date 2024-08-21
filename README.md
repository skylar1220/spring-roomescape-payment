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

# 문서화
- [x] 사용자 예약 페이지를 프론트엔드 개발자가 새로운 디자인을 적용해 만들게 되었다고 가정하고,
클라이언트 개발자가 이해하기 좋은 형태로 해당 페이지에서 호출하는 모든 API를 확인 할 수 있는 API 문서를 작성합니다.
- [x] Database ERD 를 작성하고, 작성 시 테이블 간의 관계를 표현합니다.
- [x] 작성한 API 문서와, ERD는 PR 요청시 리뷰어가 확인 할 수 있도록 PR내용에 포함해주세요.

12
