package com.example.demo.Service;

import com.example.demo.Entity.Image.ItemImg;
import com.example.demo.Repository.ItemImgRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.codehaus.groovy.util.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {
    @Value("${custom.path.upload-images}")
    private String itemImgLocation;

    private final ItemImgRepository itemImgRepository;

    private final FileService fileService;

    public void savedItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws Exception{
        // default setting
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgURL = "";

        // upload new file
        if(!StringUtils.isEmpty(oriImgName)){
            System.out.println(oriImgName);
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            imgURL = itemImgLocation + imgName;
        }

        // update data of new image to database
        itemImg.updateItemImg(oriImgName, imgName, imgURL);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception{
        if(!itemImgFile.isEmpty()){
            ItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);

            // delete original file
            if(!StringUtils.isEmpty(savedItemImg.getImgName())){
                fileService.deleteFile(itemImgLocation+"/"+savedItemImg.getImgName());
            }

            // save updated file
            String originalImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, originalImgName, itemImgFile.getBytes());
            String imgURL = itemImgLocation + imgName;
            savedItemImg.updateItemImg(originalImgName, imgName, imgURL);
        }
    }

}
