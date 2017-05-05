package com.shop.service;

import com.shop.core.model.Product;
import com.shop.core.util.AssertUtil;
import com.shop.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by TW on 2017/5/1.
 */
@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * 获取货品的默认商品信息
     * @param goodsId
     * @return
     */
    public Product findGoodsDeufaultProduct(Integer goodsId) {

        AssertUtil.isTrue(goodsId == null || goodsId < 1, "请选择货品");
        Product product = productDao.findGoodsDeufaultProduct(goodsId);
        AssertUtil.isTrue(product == null, "该货品没有默认信息，请确定后查询");
        return product;
    }

    public List<Product> findGoodsProducts(Integer goodsId) {
        AssertUtil.isTrue(goodsId == null || goodsId < 1, "请选择货品");
        List<Product> products = productDao.findGoodsProducts(goodsId);
        AssertUtil.isTrue(products == null, "该货品不存在，请确定后查询");
        return products;
    }

    /**
     * 根据ID获取商品信息
     * @param productId
     * @return
     */
    public Product findById(Integer productId) {

        AssertUtil.isTrue(productId == null || productId < 1, "请选择商品");
        Product product = productDao.findById(productId);
        AssertUtil.isTrue(product == null, "该商品不存在，请确定后查询");
        return product;
    }
}
