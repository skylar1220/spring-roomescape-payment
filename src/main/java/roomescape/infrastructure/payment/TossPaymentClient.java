package roomescape.infrastructure.payment;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import roomescape.dto.payment.PaymentRequest;
import roomescape.dto.payment.PaymentResponse;
import roomescape.dto.payment.TossErrorResponse;
import roomescape.exception.PaymentException;
import roomescape.exception.PaymentInternalException;
import roomescape.util.LogSaver;

/**
 * @see <a href="https://docs.tosspayments.com/reference/error-codes">토스 결제 오류 코드 정의서</a>
 */
public class TossPaymentClient {

    private final RestClient restClient;
    private final LogSaver logSaver;

    public TossPaymentClient(final RestClient restClient, final LogSaver logSaver) {
        this.restClient = restClient;
        this.logSaver = logSaver;
    }

    public PaymentResponse confirm(PaymentRequest paymentRequest) {

        try {
            PaymentResponse paymentResponse = restClient.post()
                    .uri("/confirm")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(paymentRequest)
                    .retrieve()
                    .body(PaymentResponse.class);

            logSaver.logInfo(paymentRequest);
            logSaver.logInfo(paymentResponse);
            return paymentResponse;

        } catch (HttpClientErrorException | HttpServerErrorException e) {
            TossErrorResponse errorResponse = getErrorResponse(e);
            HttpStatusCode clientStatusCode = TossErrorHandler.covertStatusCode(e.getStatusCode(), errorResponse.code());
            throw new PaymentException(e, clientStatusCode, errorResponse.message(), paymentRequest);

        } catch (ResourceAccessException e) {
            throw new PaymentException(e, HttpStatus.INTERNAL_SERVER_ERROR, "요청 시간을 초과하였습니다.", paymentRequest);

        } catch (Exception e) {
            throw new PaymentInternalException(e, "시스템에서 오류가 발생했습니다.");
        }
    }

    private TossErrorResponse getErrorResponse(final HttpStatusCodeException exception) {
        try {
            return exception.getResponseBodyAs(TossErrorResponse.class);
        } catch (RestClientResponseException e) {
            throw new PaymentInternalException(e, "결제 오류 객체를 생성하는 과정에서 오류가 발생했습니다.");
        }
    }
}

