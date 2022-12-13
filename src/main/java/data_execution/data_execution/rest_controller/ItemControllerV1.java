package data_execution.data_execution.rest_controller;

import data_execution.data_execution.dto.filter.ItemFilter;
import data_execution.data_execution.dto.request.item.ItemRequest;
import data_execution.data_execution.dto.response.item.ItemResponse;
import data_execution.data_execution.service.item.ItemService;
import data_execution.data_execution.service.mapper.request.item.ItemRequestMapper;
import data_execution.data_execution.service.mapper.response.item.ItemResponseMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemControllerV1 {
    private final ItemService itemService;
    private final ItemRequestMapper itemRequestMapper;
    private final ItemResponseMapper itemResponseMapper;

    public ItemControllerV1(ItemService itemService,
                            ItemRequestMapper itemRequestMapper,
                            ItemResponseMapper itemResponseMapper) {
        this.itemService = itemService;
        this.itemRequestMapper = itemRequestMapper;
        this.itemResponseMapper = itemResponseMapper;
    }

    @PostMapping("/")
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemRequest itemRequest) {
        var item = itemRequestMapper.map(itemRequest);
        item = itemService.create(item);
        var savedItemResponse = itemResponseMapper.map(item);
        return new ResponseEntity<>(savedItemResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getById(@PathVariable(name = "id") Long id) {
        var item = itemService.getByIdWithResultChecking(id);
        var itemResponse = itemResponseMapper.map(item);
        return ResponseEntity.ok(itemResponse);
    }

    @PutMapping("/filter")
    public ResponseEntity<List<ItemResponse>> getByFilter(@RequestBody ItemFilter filter) {
        System.out.println(filter);
        var items = itemService.getByFilter(filter);
        var itemsResponse = itemResponseMapper.map(items);
        return ResponseEntity.ok(itemsResponse);
    }

    @PatchMapping("/")
    public ResponseEntity<ItemResponse> update(@RequestBody ItemRequest itemRequest) {
        var item = itemRequestMapper.map(itemRequest);
        item = itemService.update(item);
        var itemResponse = itemResponseMapper.map(item);
        return ResponseEntity.ok(itemResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") Long id) {
        boolean isDeleted = itemService.deleteById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        }
        return new ResponseEntity<>("Delete was failed", HttpStatus.CONFLICT);
    }
}
