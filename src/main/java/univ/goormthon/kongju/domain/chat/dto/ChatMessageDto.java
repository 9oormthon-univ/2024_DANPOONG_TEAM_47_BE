package univ.goormthon.kongju.domain.chat.dto;

import lombok.Builder;

@Builder
public record ChatMessageDto(
        String sender,
        String receiver,
        String content
) {
}
