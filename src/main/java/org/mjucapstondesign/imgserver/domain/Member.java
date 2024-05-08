package org.mjucapstondesign.imgserver.domain;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private UUID id;
    private Image image;
}
