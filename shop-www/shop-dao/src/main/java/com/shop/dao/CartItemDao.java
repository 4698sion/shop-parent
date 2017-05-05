package com.shop.dao;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shop.core.model.CartItem;
import org.apache.ibatis.annotations.*;


public interface CartItemDao {

    @Select("select id, quantity, cart, product from xx_cart_item "
            + "where product = #{productId} and cart = #{cartId} and version > -1")
    CartItem findProductCart(@Param(value = "productId")Integer productId,
                             @Param(value = "cartId")Integer cartId);

    @Insert("insert into xx_cart_item (create_date,modify_date,version,quantity,cart,product"
            + " ) values (now(), now(), 0, #{quantity}, #{cart}, #{product})")
    void insert(CartItem cartItem);

    @Update("update xx_cart_item set quantity = #{quantity}, modify_date = now() where id = #{id} ")
    void updateCartQuantity(@Param(value = "quantity")int quantity,
                            @Param(value = "id")Integer id);

    PageList<CartItem> selectForPage(PageBounds pageBounds, @Param(value = "cartId") Integer cartId);

    @Select("select id, quantity, cart, product from xx_cart_item "
            + "where id = #{id} and version > -1")
    CartItem findId(@Param(value="id") Integer id);

    @Update("update xx_cart_item set version = -1 where id = #{id}")
    void delete(@Param(value="id") Integer id);

    @Update("update xx_cart_item set version = -1 where cart = #{cartId}")
    void clear(@Param(value="cartId") Integer cartId);

    @Select("SELECT sum(quantity) FROM xx_cart_item WHERE cart = #{cartId} and version > -1")
    Integer countQuantity(@Param(value="cartId") Integer cartId);
}
