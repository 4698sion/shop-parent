package com.shop.dao;

import com.shop.core.model.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by TW on 2017/5/1.
 */
public interface ProductDao {

    @Select("select id, allocated_stock,cost,exchange_point,market_price,"
            + "price,reward_point,sn, specification_values, stock from "
            + "xx_product where goods = #{goodsId} and is_default = 1")
    Product findGoodsDeufaultProduct(@Param(value = "goodsId") Integer goodsId);

    @Select("select id, allocated_stock,cost,exchange_point,market_price,"
            + "price,reward_point,sn, specification_values, stock from "
            + "xx_product where goods = #{goodsId}")
    List<Product> findGoodsProducts(Integer goodsId);

    @Select("select id, allocated_stock,cost,exchange_point,market_price,"
            + "price,reward_point,sn, specification_values, stock from "
            + "xx_product where id = #{productId}")
    Product findById(@Param(value="productId")Integer productId);
}
