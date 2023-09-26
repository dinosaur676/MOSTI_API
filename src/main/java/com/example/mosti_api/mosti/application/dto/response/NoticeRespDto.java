package com.example.mosti_api.mosti.application.dto.response;


import com.example.mosti_api.mosti.application.domain.Notice;

import java.time.LocalDateTime;

public record NoticeRespDto(
        long id,
        String writer,
        String title,
        String content,
        LocalDateTime createdOn
) {
    public static NoticeRespDto 실행(Notice notice) {
        return new NoticeRespDto(
                notice.getId(),
                notice.getWriter(),
                notice.getTitle(),
                notice.getContent(),
                notice.getCreatedOn()
        );
    }
}
