package com.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.shop.core.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shop.core.base.ResultListInfo;
import com.shop.core.constant.ProductCategoryGrade;
import com.shop.core.dto.GoodsDto;
import com.shop.core.util.AssertUtil;
import com.shop.core.util.ResultListInfoUtil;
import com.shop.dao.GoodsDao;

@Service
public class GoodsService {
	
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private ProductService productService;

	public List<Goods> findGoodsList(Integer productCategoryId, 
			int tagId, int count) {
		List<Goods> goods = goodsDao.findGoodsList(productCategoryId, tagId, count);
		return goods;
	}
	
	/**
	 * 搜索
	 * @param goodsDto
	 * @return
	 */
	public ResultListInfo<Goods> search(GoodsDto goodsDto) {
		// 构建pagebounds
		// 利用pagebounds调用dao的方法
		if (StringUtils.isBlank(goodsDto.getSort())) {
			goodsDto.setSort("create_date.desc");
		}
		PageList<Goods> result = goodsDao.selectForPage(goodsDto, goodsDto.toPageBounds());
		ResultListInfoUtil<Goods> resultListInfoUtil = new ResultListInfoUtil<>();
		ResultListInfo<Goods> resultListInfo = resultListInfoUtil.buildSuccessResultList(result, goodsDto);
		return resultListInfo;
	}

	public List<Goods> findHotGoodsList(int tagId, int count) {
		List<Goods> goods = goodsDao.findHotGoodsList(tagId, count);
		return goods;
	}
	
	/**
	 * 分页获取分类下的商品
	 * @param productCategoryId
	 * @param goodsDto
	 * @return
	 */
	public Object[] findProductCategoryGoods(Integer productCategoryId, 
			GoodsDto goodsDto) {
		if (StringUtils.isBlank(goodsDto.getSort())) {
			goodsDto.setSort("create_date.desc");
		}
		AssertUtil.isTrue(productCategoryId == null || productCategoryId < 1, "请选择一个分类进行查询");
		
		// 获取分类
		ProductCategory productCategory = productCategoryService.findById(productCategoryId);
		goodsDto.setProductCategoryId(productCategoryId);
		// 拼接treePath
		// 如果这个分类不是第1级：,父级,本级
		String treePath = "";
		if (productCategory.getGrade() == ProductCategoryGrade.ROOT.getGrade()) { // 第一级
			treePath = "," + productCategoryId + ",";
		} else {
			treePath = productCategory.getTreePath() + productCategoryId + ",";
		}
		goodsDto.setTreePath(treePath);
		PageList<Goods> result = goodsDao.list(goodsDto, goodsDto.toPageBounds());
		ResultListInfoUtil<Goods> resultListInfoUtil = new ResultListInfoUtil<>();
		ResultListInfo<Goods> resultListInfo = resultListInfoUtil.buildSuccessResultList(result, goodsDto);
		return new Object[]{productCategory, resultListInfo};
	}

	/**
	 * 货品详细页面
	 * @param id
	 * @return
	 */
    public Map<String,Object> findById(Integer id) {
		// 基本参数验证
		AssertUtil.isTrue(id == null || id < 1, "请选择商品信息");
		// 从数据库读取goods信息
		Goods goods = goodsDao.findById(id);
		// 判断goods是否存在
		AssertUtil.isTrue(goods == null, "该商品信息不存在");
		// 格式化数据-->sku信息 | 商品参数 | 图片格式化
		String specificalItems = goods.getSpecificationItems();
		List<SpecificationItem> specificationItems = JSON.parseArray(specificalItems, SpecificationItem.class);

		String parameterValue = goods.getParameterValues();
		List<ParameterValue> parameterValues = JSON.parseArray(parameterValue, ParameterValue.class);

		String productImage = goods.getProductImages();
		List<ProductImage> productImages = JSON.parseArray(productImage, ProductImage.class);

		// 默认商品信息
		Product product = productService.findGoodsDeufaultProduct(goods.getId());

		// 获取商品的所有sku信息
		List<Product> products = productService.findGoodsProducts(goods.getId());

		// 分类信息
		ProductCategory productCategory = productCategoryService.findById(goods.getProductCategory());

		// 返回结果集
		Map<String, Object> result = new HashMap<>();
		result.put("goods", goods);
		result.put("specificationValues", specificationItems);
		result.put("parameterValues", parameterValues);
		result.put("productImages", productImages);
		result.put("defaultProduct", product);
		result.put("productCategory", productCategory);
		result.put("products", products);

		return result;
    }
}
