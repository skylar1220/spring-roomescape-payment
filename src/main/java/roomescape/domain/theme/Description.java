package roomescape.domain.theme;

import jakarta.persistence.Embeddable;
import roomescape.exception.custom.RoomEscapeException;

@Embeddable
public class Description {

    private static final int MIN_DESCRIPTION_LENGTH = 10;

    private String description;

    protected Description() {
    }

    public Description(String description) {
        validateDescription(description);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    private void validateDescription(String description) {
        if (description.length() < MIN_DESCRIPTION_LENGTH) {
            throw new RoomEscapeException(
                    "설명은 10글자 이상 입력해주세요.",
                    " theme_description : " + description
            );
        }
    }
}
