package com.example.mosti_api.mosti.adapter.api;

import com.example.mosti_api.framework.dto.ResponseDto;
import com.example.mosti_api.framework.dto.SuccessRespDto;
import com.example.mosti_api.mosti.application.dto.response.NoticeRespDto;
import com.example.mosti_api.mosti.application.port.in.INoticeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notice")
public class NoticeController {
    private final INoticeService noticeService;

    public NoticeController(INoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping
    public ResponseDto 목록조회() {
        List<NoticeRespDto> noticeRespDtos = noticeService.목록조회();

        if (noticeRespDtos == null || noticeRespDtos.isEmpty()) {
            return new SuccessRespDto("No Data");
        }

        return new SuccessRespDto(noticeRespDtos, "조회가 성공적으로 이루어졌습니다.");
    }

}
