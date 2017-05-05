package com.shop.service;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.shop.core.base.BaseDto;
import com.shop.core.base.ResultListInfo;
import com.shop.core.model.Cart;
import com.shop.core.model.CartItem;
import com.shop.core.model.Product;
import com.shop.core.util.AssertUtil;
import com.shop.core.util.ResultListInfoUtil;
import com.shop.dao.CartDao;
import com.shop.dao.CartItemDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class CartService {

	@Autowired
	private CartDao cartDao;
	@Autowired
	private CartItemDao cartItemDao;
	@Autowired
	private ProductService productService;

	/**
	 * 将商品添加到购物车
	 * @param productId 商品id
	 * @param quantity 商品数量
	 * @param loginUserId 登录用户id
	 */
	public void add(Integer productId, Integer quantity, Integer loginUserId, Integer goodsId) {
		// 基本参数验证
		AssertUtil.isTrue((productId == null || productId < 1) && (goodsId == null || goodsId < 1), "请选择要添加的商品");
		AssertUtil.isTrue(quantity == null || quantity < 1, "请选择要添加商品的数量");
		AssertUtil.isTrue(loginUserId == null || loginUserId < 1, "请先登录");
		Product product = null;
		if (goodsId != null) { // 传入的是货品ID
			// 根据货品ID获取默认的商品
			Product defaultProduct = productService.findGoodsDeufaultProduct(goodsId);
			AssertUtil.isTrue(defaultProduct == null, "该商品不存在");
			productId = defaultProduct.getId();
		}
		// 商品库存验证
		if (product == null) {
			product = productService.findById(productId);
		}

		AssertUtil.isTrue(product == null, "该商品不存在");
		// 库存判断
		AssertUtil.isTrue(product.getAvailableStock() < quantity, "该商品库存不足，请重新确认数量" );

		// 根据用户id去获取此用户的购物车xx_cart
		Cart cart = cartDao.findByMemberId(loginUserId);
		// 如果此用户没有购物车就生成一个购物车
		if (cart == null) {
			// 添加
			cart = insert(null, loginUserId);
			// 添加明细
			addCartItem(quantity, cart.getId(), productId);
			return;
		}

		// 去xx_cart_item明细中查询此商品是否已存在，如果存在就更新数量，不存在就插入明细
		CartItem cartItem = cartItemDao.findProductCart(productId, cart.getId());
		if (cartItem == null) {
			// 插入
			addCartItem(quantity, cart.getId(), productId);
		} else { // 更新数量
			cartItemDao.updateCartQuantity(cartItem.getQuantity() + quantity, cartItem.getId());
		}
	}

	/**
	 * 添加购物车信息
	 * @param cartKey 购物车的Key
	 * @param member 用户ID
	 */
	private Cart insert(String cartKey, Integer member) {
		Cart cart = new Cart();
		if (StringUtils.isBlank(cartKey)) {
			cartKey = DigestUtils.md5Hex(UUID.randomUUID() + RandomStringUtils.randomAlphabetic(30));
		}
		cart.setCartKey(cartKey);
		Date now = new Date();
		cart.setCreateDate(now);
		cart.setExpire(new Date(now.getTime() + Cart.TIMEOUT));
		cart.setMember(member);
		cart.setModifyDate(now);
		cart.setVersion(0);
		cartDao.insert(cart);
		return cart;
	}

	/**
	 * 添加购物车明细
	 * @param quantity
	 * @param cartId
	 * @param productId
	 */
	private void addCartItem(Integer quantity, Integer cartId, Integer productId) {
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(quantity);
		cartItem.setCart(cartId);
		cartItem.setProduct(productId);
		cartItemDao.insert(cartItem);
	}

	
	public ResultListInfo<CartItem> selectForPage(Integer loginUserId,BaseDto baseDto) {
	
		AssertUtil.isTrue(loginUserId == null || loginUserId<1, "请先登入");
		baseDto.setSort("modify_date.desc");
		//获取当前购物车
		Cart cart =getCurrentCart(loginUserId);
		//分页
		PageList<CartItem> cartItems =  cartItemDao.selectForPage(baseDto.toPageBounds(),cart.getId());
		
		
		//返回结果集
		ResultListInfoUtil<CartItem> result =new ResultListInfoUtil<>();
		
		return result.buildSuccessResultList(cartItems, baseDto);
	}
	
	private Cart getCurrentCart(Integer memberId) {
		Cart cart=cartDao.findByMemberId(memberId);
		if(cart !=null){
			return cart;
		}
		return null;
	}

	
}
