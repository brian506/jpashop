package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Top;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
   // 상품 서비스는 상품 리포지토리에 단순히 위임만 하는 클래스


    private final ItemRepository itemRepository;

    @Transactional // read only로 하면 저장이 안됨
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void  updateItem(Long itemId, String name, int price,int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
        // 업데이트 할 항목들은 merge를 사용하지 않고 변경감지를 사용해서 데이터를 직접 변경하는 것이 좋음


    }
    public List<Item> findItems(){ //  전체 찾기
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId){ // 하나만 찾기
        return itemRepository.findOne(itemId);
    }
}
