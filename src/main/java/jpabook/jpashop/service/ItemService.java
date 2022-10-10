package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void save(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, Book param){
        // 더치체킹
        // 트랜잭션 안에서 엔티티를 다시 조회, 변경할 값 선택
        // -> 트랜잭션 커밋 시점에 변경감지하여 DB에 update 실행행
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(param.getPrice());
        findItem.setName(param.getName());
        findItem.setStockQuantity(param.getStockQuantity());

    }

    public Item findOne(Long id){
        Item item = itemRepository.findOne(id);
        return item;
    }

    public List<Item> findItem(){
        return  itemRepository.findAll();
    }
}
