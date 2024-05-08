package org.mjucapstondesign.imgserver.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.mjucapstondesign.imgserver.domain.Image;
import org.mjucapstondesign.imgserver.dto.response.IdListResponseDto;
import org.mjucapstondesign.imgserver.dto.response.IdResponseDto;
import org.mjucapstondesign.imgserver.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
@Getter
@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void getImageResult(UUID memberId, String path) {
        Image image = this.memberRepository.getImgByMemberId(memberId);
        System.out.println("image path: " + image.getImgPath());
        System.out.println("image name: " + image.getImgName());

        ProcessBuilder processBuilder = new ProcessBuilder("python", path + "ai_server.py", image.getImgPath());
        this.memberRepository.getImageResult(processBuilder);
    }

    public IdResponseDto uploadImg(MultipartFile file) throws IOException {
        UUID memberUuid = memberRepository.uploadImg(file);
        return new IdResponseDto(memberUuid);
    }

    public IdListResponseDto uploadImgs(List<MultipartFile> files) throws IOException {
        List<UUID> uuids = memberRepository.uploadImgs(files);
        return new IdListResponseDto(uuids);
    }

    public IdListResponseDto getAllImgId(){
        List<UUID> uuids = memberRepository.getAllImgId();
        return new IdListResponseDto(uuids);
    }

    public byte[] downloadImgById(UUID id) throws IOException {
        Image image = this.memberRepository.downloadImgById(id);

        InputStream inputStream = new FileInputStream(image.getImgPath() + image.getImgName());
        byte[] bytes = inputStream.readAllBytes();
        inputStream.close();
        return bytes;
    }
}
