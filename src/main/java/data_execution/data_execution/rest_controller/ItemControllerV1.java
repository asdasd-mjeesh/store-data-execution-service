package data_execution.data_execution.rest_controller;

import data_execution.data_execution.service.item.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {
    private final ItemService itemService;

    public ItemControllerV1(ItemService itemService) {
        this.itemService = itemService;
    }


}
