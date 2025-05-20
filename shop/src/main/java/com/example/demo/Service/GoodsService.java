package com.example.demo.Service;

import com.example.demo.DTO.GoodsDTO;
import com.example.demo.DTO.ItemImgDTO;
import com.example.demo.Entity.Goods;
import com.example.demo.Entity.Image.ItemImg;
import com.example.demo.Repository.GoodsRepository;
import com.example.demo.Repository.ItemImgRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private ItemImgRepository itemImgRepository;
    @Autowired
    private ItemImgService itemImgService;

    public Long saveItem(GoodsDTO goodsDTO, List<MultipartFile> itemImgFileList) throws Exception{
        Goods goods = goodsDTO.createGoods();
        goodsRepository.save(goods);

        for (int i = 0; i < itemImgFileList.size(); i++){
            ItemImg itemImg = new ItemImg();
            itemImg.setGoods(goods);
            if(i == 0)
                itemImg.setRepImgYn("Y");
            else
                itemImg.setRepImgYn("N");
            itemImgService.savedItemImg(itemImg, itemImgFileList.get(i));
        }

        return goods.getId();
    }

    @Transactional(readOnly = true)
    public GoodsDTO getGoodsDTL(Long id){
        List<ItemImg> itemImgList = itemImgRepository.findByGoodsIdOrderByIdAsc(id);
        List<ItemImgDTO> itemImgDTOList = new ArrayList<>();
        for(ItemImg itemImg : itemImgList){
            ItemImgDTO itemImgDTO = ItemImgDTO.of(itemImg);
            itemImgDTOList.add(itemImgDTO);
        }

        Goods goods = goodsRepository.findById(id).get();
        GoodsDTO goodsDTO = GoodsDTO.of(goods);
        goodsDTO.setItemImgDTOList(itemImgDTOList);

        return goodsDTO;
    }

    public Long updateItem(GoodsDTO goodsDTO, List<MultipartFile> itemImgFileList) throws Exception{

        Goods goods = goodsRepository.findByGoodsName(goodsDTO.getGoodsName()).get(0);
        goods.updateGoods(goodsDTO);

        System.out.println(goodsDTO.getItemImgDTOList().toString());
        List<Long> itemImgIds = goodsDTO.getItemImgIds();

        for (int i = 0; i < itemImgFileList.size(); i++){
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }

        return goods.getId();
    }

}
