package lk.ijse.dep.pos.datajpa.controller;

import lk.ijse.dep.pos.datajpa.business.custom.ItemBO;
import lk.ijse.dep.pos.datajpa.dto.ItemDTO;
import lk.ijse.dep.pos.datajpa.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/items")
@RestController
public class ItemController {

    @Autowired
    private ItemBO itemBO;

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemDTO> allItems = itemBO.findAllItems();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Count", allItems.toString());
        return new ResponseEntity<>(allItems,httpHeaders,HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO getItem(@PathVariable String id) {
        try {
            return itemBO.findItem(id);
        } catch (NullPointerException e) {
            throw new NotFoundException(e);
        }
    }

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity handleNotFoundException(NotFoundException e) {
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }


    @GetMapping(params = "q=last")
    public String getLastItemCode() {
        return itemBO.getLastItemCode();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String saveItem(@RequestBody ItemDTO item) {
        itemBO.saveItem(item);
        return "\"" + item.getCode() + "\"";
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id) {
        itemBO.deleteItem(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateItem(@PathVariable String id, @RequestBody ItemDTO item) {
        if (id.equals(item.getCode())) {
            itemBO.updateItem(item);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(params = "category=id")
    public List<String> getAllItemCodes() {
        return itemBO.getAllItemCodes();
    }
}
