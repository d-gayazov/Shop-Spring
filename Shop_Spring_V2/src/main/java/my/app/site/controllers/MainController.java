package my.app.site.controllers;

import my.app.site.models.Cart;
import my.app.site.models.Good;
import my.app.site.models.Info;
import my.app.site.repo.CartRepository;
import my.app.site.repo.GoodRepository;
import my.app.site.repo.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Controller
public class MainController {
    @Autowired
    private GoodRepository goodRepository;
    @Autowired
    private InfoRepository infoRepository;
    @Autowired
    private CartRepository cartRepository;

    @GetMapping("/")
    public String main(Model model){
        Iterable<Good> goods = goodRepository.findAll();
        model.addAttribute("goods",goods);
        return "shopMain";
    }

    @GetMapping("/shopGoodCatalog/{id}")
    public String getGoodCatById(@PathVariable(value = "id") int id, Model model){
        Iterable<Good> goods = goodRepository.findByCat(id);
        model.addAttribute("goods",goods);
        return "shopGoodCatalog";
    }

    @GetMapping("/shopInfo/{id}")
    public String getShopInfoById(@PathVariable(value = "id") int id, Model model){
        Info info = infoRepository.findById(id).get();
        model.addAttribute("info",info);
        return "shopInfo";
    }

    @GetMapping("/good/{id}")
    public String getGoodById(@PathVariable(value = "id") int id, Model model){
        Good good = goodRepository.findById(id).get();
        model.addAttribute("good",good);
        return "goodCard";
    }

    @GetMapping("/forAdmin")
    public String mainAdmin(Model model){
        Iterable<Good> goods = goodRepository.findAll();
        model.addAttribute("goods",goods);
        return "shopMainForAdmin";
    }

    @GetMapping("/shopGoodCatalogForAdmin/{id}")
    public String forAdminCatById(@PathVariable(value = "id") int id, Model model){
        Iterable<Good> goods = goodRepository.findByCat(id);
        model.addAttribute("goods",goods);
        return "shopGoodCatalogForAdmin";
    }

    @GetMapping("/delGood/{id}")
    public String delGood(@PathVariable(value = "id") int id){
        goodRepository.deleteById(id);
        return "shopGoodCatalogForAdmin";
    }

    @GetMapping("/good/add")
    public String addGood(Model model){
        return "form";
    }

    @PostMapping("/good/add")
    public String addGood(@RequestParam String title, @RequestParam String info, @RequestParam int price, @RequestParam int cat){
        String photo = "default";

        Good good = new Good(title, info, price, photo, cat);
        goodRepository.save(good);
        return "shopMainForAdmin";
    }

    @GetMapping("/cart/{id}")
    public String getIntoCart(@PathVariable(value = "id") int id){
        Good good = goodRepository.findById(id).get();
            String title = good.getTitle();
            int price = good.getPrice();
            int count = 1;
            int goodid = good.getId();
        Cart cart = new Cart(title,price,count,goodid);
            cartRepository.save(cart);
        return "redirect:/";
        }
}
