package org.mjucapstondesign.imgserver.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class IdListResponseDto {
    private List<UUID> imgIds;
}
