package roomescape.domain.theme;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import roomescape.exception.custom.RoomEscapeException;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ThumbnailTest {

    @Test
    void 썸네일이_비어있을_경우_예외_발생() {
        //given
        String thumbnail = "";

        //when, then
        assertThatThrownBy(() -> new Thumbnail(thumbnail))
                .isInstanceOf(RoomEscapeException.class);
    }
}
