package jpabook.jpashop.service;

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

    public Item findOne(Long id){
        Item item = itemRepository.findOne(id);
        return item;
    }

    public List<Item> findItem(){
        return  itemRepository.findAll();
    }
}
