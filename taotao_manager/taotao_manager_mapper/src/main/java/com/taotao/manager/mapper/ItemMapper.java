package com.taotao.manager.mapper;

import com.taotao.manager.model.Item;

import java.util.List;

public interface ItemMapper {


    Item findItemyById();

    List<Item> findItems();

    void  save(Item item);

    void  update(Item item);

    void  delete(Long id);

}
