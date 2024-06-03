package roomescape.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogSaver {

    private final Logger log;
    private final ObjectMapper objectMapper;

    private LogSaver(final ObjectMapper objectMapper) {
        this.log = LoggerFactory.getLogger(getClass());
        this.objectMapper = objectMapper;
    }

    public void logInfo(final Object object) throws Exception {
        String json = "";

        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new Exception("로그 과정에서 변환에 실패했습니다.");
        }

        log.info(json);
    }
}
