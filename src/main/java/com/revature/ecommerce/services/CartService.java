package com.revature.ecommerce.services;

import com.revature.ecommerce.entities.Item;
import com.revature.ecommerce.entities.User;
import com.revature.ecommerce.entities.dtos.requests.NewCartRequest;
import com.revature.ecommerce.entities.dtos.responses.CartPrincipal;
import com.revature.ecommerce.entities.dtos.responses.ItemPrincipal;
import com.revature.ecommerce.entities.dtos.responses.Principal;
import com.revature.ecommerce.entities.junctions.Cart;
import com.revature.ecommerce.entities.keys.CartKey;
import com.revature.ecommerce.repositories.CartRepository;
import com.revature.ecommerce.repositories.ItemRepository;
import com.revature.ecommerce.repositories.UserRepository;
import com.revature.ecommerce.utils.custom_exceptions.InvalidItemException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepo;
    private final UserRepository userRepo;
    private final ItemRepository itemRepo;

    public CartService(CartRepository cartRepo, UserRepository userRepo, ItemRepository itemRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.itemRepo = itemRepo;
    }

    public void addToCart(NewCartRequest req) {
        Optional<User> user = userRepo.findById(req.getUserId());
        Optional<Item> item = itemRepo.findById(req.getItemId());
        CartKey key = new CartKey(req.getItemId(), req.getUserId());
        Cart entry = new Cart(key, item.get(), user.get(), req.getAmount());
        cartRepo.save(entry);
    }

    public void updateAmount(NewCartRequest req) {
        CartKey key = new CartKey(req.getItemId(), req.getUserId());
        cartRepo.updateAmount(req.getAmount(), key);
    }

    public List<CartPrincipal> getCart(Principal principal) {
        List<Cart> cart = cartRepo.findByUserId(principal.getId());
        List<CartPrincipal> list = new ArrayList<>();
        for (Cart c : cart) {
            Item item = c.getItem();
            ItemPrincipal itemP = new ItemPrincipal(item.getId(), item.getName(), item.getDescription(), item.getStock(), item.getMsrp(),
                                                    item.getCurrent_price(), item.getImg_url(), item.getItemType());
            list.add(new CartPrincipal(itemP, c.getAmount()));
        }
        return list;
    }

    public void delete(NewCartRequest req) {
        cartRepo.deleteByCompositeKey(req.getItemId(), req.getUserId());
    }

    public boolean isValidItem(NewCartRequest req) {
        Optional<Item> item = itemRepo.findById(req.getItemId());
        if (!item.isPresent())
            throw new InvalidItemException("Invalid item id");
        return true;
    }

    public boolean isValidItemAmount(NewCartRequest req) {
        Optional<Item> item = itemRepo.findById(req.getItemId());
        int amount = req.getAmount();
        int stock = item.get().getStock();

        if (amount <= 0 || amount > stock)
            throw new InvalidItemException("Invalid amount specified");
        return true;
    }

    public boolean isUnique(NewCartRequest req) {
        CartKey key = new CartKey(req.getItemId(), req.getUserId());
        Optional<Cart> entry = cartRepo.findById(key);
        if (entry.isPresent())
            throw new InvalidItemException("This item is already in your cart");
        return true;
    }

    public boolean isPresent(NewCartRequest req) {
        CartKey key = new CartKey(req.getItemId(), req.getUserId());
        Optional<Cart> entry = cartRepo.findById(key);
        if (!entry.isPresent())
            throw new InvalidItemException("This item is not already in your cart");
        return true;
    }
}
