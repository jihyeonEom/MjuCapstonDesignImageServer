package org.mjucapstondesign.imgserver.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Image {
    private UUID id;
    private String imgName; // 이미지 이름
    private String imgPath; // 이미지 경로
}
