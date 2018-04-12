package com.irenailieva.nutricounter.models.view;

import com.irenailieva.nutricounter.util.WebConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class ProfileEditModel {

    @NotNull(message = WebConstants.EMPTY_GENDER_MESSAGE)
    @NotEmpty(message = WebConstants.EMPTY_GENDER_MESSAGE)
    private String gender;

    @NotNull
    @Pattern(regexp = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}", message = WebConstants.INVALID_DATE_FORMAT_MESSAGE)
    private String date;

    @Pattern(regexp = "[0-9]+", message = WebConstants.INVALID_HEIGHT_MESSAGE)
    private String height;

    @Pattern(regexp = "[0-9]+", message = WebConstants.INVALID_WEIGHT_MESSAGE)
    private String weight;
}
