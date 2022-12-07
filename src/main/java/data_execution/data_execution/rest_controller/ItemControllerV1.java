package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.response.item.ItemResponse;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.service.item.ItemService;
import data_execution.data_execution.service.mapper.item.ItemMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {
    private final ItemService itemService;
    private final ItemMapper itemMapper;

    public ItemControllerV1(ItemService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    @PostMapping("/")
    public ResponseEntity<ItemResponse> createItem(@RequestBody Item item) {
        var savedItem = itemService.create(item);
        var savedItemDto = itemMapper.map(savedItem);
        return ResponseEntity.ok(savedItemDto);
    }
}
