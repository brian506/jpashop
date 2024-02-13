package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.domain.item.Top;
import jpabook.jpashop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new TopForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(TopForm form){
        Top top = new Top();
        top.setName(form.getName());
        top.setPrice(form.getPrice());
        top.setStockQuantity(form.getStockQuantity());
        top.setOuter(form.getOuter());
        top.setSweats(form.getSweats());

        itemService.saveItem(top);
        return "redirect:/items";

    }
    @GetMapping("/items")
    public String list(Model model){
        List<Item> items = itemService.findItems();
        model.addAttribute("items",items);
        return "items/itemList";
    }

    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId,Model model){
        Top item = (Top) itemService.findOne(itemId);

        TopForm form = new TopForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setOuter(item.getOuter());
        form.setSweats(item.getSweats());

        model.addAttribute("form",form);
        return "items/updateItemForm";
    }

    //**변경 감지**//
    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable Long itemId, @ModelAttribute("form") TopForm form){
//        Top top = new Top();
//        top.setId(form.getId());
//        top.setName(form.getName());
//        top.setPrice(form.getPrice());
//        top.setStockQuantity(form.getStockQuantity());
//        top.setOuter(form.getOuter());
//        top.setSweats(form.getSweats());

        itemService.updateItem(itemId,form.getName(),form.getPrice(),form.getStockQuantity());

        return "redirect:/items";
    }
}
