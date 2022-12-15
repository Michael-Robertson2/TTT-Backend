package com.revature.ecommerce.entities.keys;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CartKey implements Serializable {

    @Column(name = "item_id")
    String itemId;

    @Column(name = "user_id")
    String userId;

    public CartKey(String itemId, String userId) {
        this.itemId = itemId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartKey cartKey = (CartKey) o;
        return Objects.equals(itemId, cartKey.itemId) && Objects.equals(userId, cartKey.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, userId);
    }
}
