package org.mjucapstondesign.imgserver.repository;

import lombok.AllArgsConstructor;
import org.mjucapstondesign.imgserver.domain.Image;
import org.mjucapstondesign.imgserver.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class MemberRepository {

    private final List<Member> memberList;
    // 파일 업로드 경로
    private final String filePath = "";

    public void getImageResult(ProcessBuilder processBuilder) {
        try{
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            process.waitFor();
            System.out.println(process.exitValue());
            process.destroy();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public Image getImgByMemberId(UUID memberId) {
        for(Member member : memberList) {
            if(member.getId().equals(memberId)) {
                return member.getImage();
            }
        }
        return null;
    }

    public UUID uploadImg(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename(); // 파일 이름 저장

        File dest = new File(filePath + fileName);
        file.transferTo(dest);

        UUID imgUuid = UUID.randomUUID();
        UUID memberUuid = UUID.randomUUID();

        Member member = new Member(memberUuid, new Image(imgUuid, fileName, filePath));
        memberList.add(member);

        return memberUuid;
    }

    public List<UUID> uploadImgs(List<MultipartFile> files) throws IOException {

        List<UUID> memberUuidList = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            File dest = new File(filePath + fileName);
            file.transferTo(dest);

            UUID imgUuid = UUID.randomUUID();
            UUID memberUuid = UUID.randomUUID();
            Member member = new Member(memberUuid, new Image(imgUuid, fileName, filePath));
            memberList.add(member);
            System.out.println("uploading " + fileName + " is success");

            memberUuidList.add(memberUuid);
        }
        return memberUuidList;
    }

    public List<UUID> getAllImgId() {
        List<UUID> memberUuidList = new ArrayList<>();
        for (Member member : memberList) {
            memberUuidList.add(member.getId());
        }
        return memberUuidList;
    }

    public Image downloadImgById(UUID id) {
        for(Member member : memberList) {
            if(member.getId().equals(id)) {
                return member.getImage();
            }
        }
        return null;
    }
}