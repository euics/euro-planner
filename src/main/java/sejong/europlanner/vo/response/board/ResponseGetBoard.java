package sejong.europlanner.vo.response.board;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import sejong.europlanner.enumtype.Gender;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseGetBoard {
    private Long boardId;
    private String title;
    private String content;
    private String createdBy;
    private String modifiedBy;
    private LocalDateTime regTime;
    private LocalDateTime updateTime;
    private String birthDate;
    private Gender gender;
    private String departDate;
}
