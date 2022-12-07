package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.response.item.ItemResponse;
import data_execution.data_execution.entity.item.Item;
import data_execution.data_execution.service.item.ItemService;
import data_execution.data_execution.service.mapper.response.item.ItemResponseMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {
    private final ItemService itemService;
    private final ItemResponseMapper itemResponseMapper;

    public ItemControllerV1(ItemService itemService, ItemResponseMapper itemResponseMapper) {
        this.itemService = itemService;
        this.itemResponseMapper = itemResponseMapper;
    }

    @PostMapping("/")
    public ResponseEntity<ItemResponse> createItem(@RequestBody Item item) {
        var savedItem = itemService.create(item);
        var savedItemDto = itemResponseMapper.map(savedItem);
        return ResponseEntity.ok(savedItemDto);
    }
}
