package sejong.europlanner.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sejong.europlanner.dto.CommentsDto;
import sejong.europlanner.service.serviceinterface.CommentsService;
import sejong.europlanner.vo.request.comments.RequestCreateComments;
import sejong.europlanner.vo.request.comments.RequestUpdateComments;
import sejong.europlanner.vo.response.comments.ResponseDeleteComments;
import sejong.europlanner.vo.response.comments.ResponseGetComments;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "Authorization")
public class CommentsController {
    private final CommentsService commentsService;

    @Autowired
    public CommentsController(CommentsService commentsService) {
        this.commentsService = commentsService;
    }

    @GetMapping("/comments/read/{boardId}")
    public ResponseEntity<List<ResponseGetComments>> getComments(@PathVariable Long boardId){
        List<CommentsDto> commentsDtoList = commentsService.getCommentsList(boardId);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        List<ResponseGetComments> responseGetCommentsList = new ArrayList<>();

        for(CommentsDto cd : commentsDtoList){
            ResponseGetComments responseGetComments = mapper.map(cd, ResponseGetComments.class);
            responseGetComments.setCommentsId(cd.getId());
            responseGetCommentsList.add(responseGetComments);
        }

        return ResponseEntity.ok().body(responseGetCommentsList);
    }

    @PostMapping("/comments/create/{boardId}")
    public ResponseEntity<ResponseGetComments> createComments(@PathVariable Long boardId,
                                                              @RequestBody RequestCreateComments requestCreateComments){
        CommentsDto commentsDto = commentsService.createComments(boardId, requestCreateComments);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ResponseGetComments responseGetComments = mapper.map(commentsDto, ResponseGetComments.class);
        responseGetComments.setCommentsId(commentsDto.getId());

        return ResponseEntity.ok().body(responseGetComments);
    }

    @PutMapping("/comments/update/{commentsId}")
    public ResponseEntity<ResponseGetComments> updateComments(@PathVariable Long commentsId,
                                                              @RequestBody RequestUpdateComments requestUpdateComments){
        CommentsDto commentsDto = commentsService.updateComments(commentsId, requestUpdateComments);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        ResponseGetComments responseGetComments = mapper.map(commentsDto, ResponseGetComments.class);
        responseGetComments.setCommentsId(commentsDto.getId());

        return ResponseEntity.ok().body(responseGetComments);
    }

    @DeleteMapping("/comments/delete/{commentsId}")
    public ResponseEntity<ResponseDeleteComments> deleteComments(@PathVariable Long commentsId){
        ResponseDeleteComments responseDeleteComments = commentsService.deleteComments(commentsId);

        return ResponseEntity.ok().body(responseDeleteComments);
    }
}
