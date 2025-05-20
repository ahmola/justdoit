package com.example.demo.Service;

import com.example.demo.Constant.ItemSellStatus;
import com.example.demo.Constant.Role;
import com.example.demo.DTO.GoodsDTO;
import com.example.demo.Entity.Goods;
import com.example.demo.Entity.Image.ItemImg;
import com.example.demo.Repository.GoodsRepository;
import com.example.demo.Repository.ItemImgRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yml")
class GoodsServiceTest {

    @Autowired
    GoodsService goodsService;

    @Autowired
    GoodsRepository goodsRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    List<MultipartFile> createMultipartFiles() throws Exception{

        List<MultipartFile> multipartFileList = new ArrayList<>();
        String path = "/Users/park/Documents/images/shop";

        for(int i = 0; i < 5; i++){
            String imageName = "image" + i + ".jpg";
            MockMultipartFile multipartFile = new MockMultipartFile(path, imageName,
                    "image/jpg", new byte[]{1,2,3,4});
            multipartFileList.add(multipartFile);
        }

        return multipartFileList;
    }

    @Test
    @DisplayName("Adding Goods Test")
    @WithMockUser(username = "admin", roles = "ADMIN")
    void saveItem() throws Exception{
        GoodsDTO goodsDTO = GoodsDTO.builder()
                .goodsName("TestGoods")
                .itemSellStatus(ItemSellStatus.SELL)
                .goodsDetails("TestCase")
                .price(1000)
                .stockNumber(100)
                .build();

        List<MultipartFile> multipartFileList = createMultipartFiles();
        Long goodsId = goodsService.saveItem(goodsDTO, multipartFileList);

        List<ItemImg> itemImgList = itemImgRepository.findByGoodsIdOrderByIdAsc(goodsId);
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(EntityNotFoundException::new);

        assertEquals(goodsDTO.getGoodsName(), goods.getGoodsName());
        assertEquals(goodsDTO.getItemSellStatus(), goods.getItemSellStatus());
        assertEquals(multipartFileList.get(0).getOriginalFilename(), itemImgList.get(0).getOriginalImgName());
    }

}