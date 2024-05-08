package org.mjucapstondesign.imgserver.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mjucapstondesign.imgserver.dto.ResponseDto;
import org.mjucapstondesign.imgserver.dto.response.IdListResponseDto;
import org.mjucapstondesign.imgserver.dto.response.IdResponseDto;
import org.mjucapstondesign.imgserver.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @GetMapping(value="images/{member_id}/result")
    public ResponseEntity<ResponseDto<Void>> getImageResult(@PathVariable("member_id") UUID memberId) throws IOException {
        // ai_server.py가 있는 디렉토리 절대경로
        String path = "";
        this.memberService.getImageResult(memberId, path);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 사진 한 장만 업로드할 경우
    @PostMapping(value = "images")
    public ResponseEntity<ResponseDto<IdResponseDto>> uploadImg(@RequestBody MultipartFile file) throws IOException {
        IdResponseDto idResponseDto = this.memberService.uploadImg(file);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "upload image", idResponseDto), HttpStatus.OK);
    }

    // 사진 여러개를 업로드할 경우
    @PostMapping(value = "images/list")
    public ResponseEntity<ResponseDto<IdListResponseDto>> uploadImgs(@RequestBody List<MultipartFile> files) throws IOException {
        IdListResponseDto idListResponseDto = this.memberService.uploadImgs(files);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "upload image list", idListResponseDto), HttpStatus.OK);
    }

    // 모든 이미지의 아이디 조회하기
    @GetMapping(value = "images/id")
    public ResponseEntity<ResponseDto<IdListResponseDto>> getAllImgId() {
        IdListResponseDto idListResponseDto = this.memberService.getAllImgId();
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "get all image id list", idListResponseDto), HttpStatus.OK);
    }

    // 이미지 아이디를 통해 이미지 다운로드하기
    @GetMapping(value = "images/{member_id}")
    public ResponseEntity<byte[]> downloadImgById(@PathVariable("member_id") UUID id) throws IOException {
        byte[] bytes = this.memberService.downloadImgById(id);
        return new ResponseEntity<>(bytes, HttpStatus.OK);
    }

}
