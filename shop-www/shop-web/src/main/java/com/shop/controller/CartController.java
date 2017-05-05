package com.shop.controller;

import com.shop.annotation.IsLogin;
import com.shop.core.base.BaseController;
import com.shop.core.base.BaseDto;
import com.shop.core.base.ResultInfo;
import com.shop.core.base.ResultListInfo;
import com.shop.core.constant.Constant;
import com.shop.core.model.CartItem;
import com.shop.core.model.Goods;
import com.shop.core.util.LoginIdentityUtil;
import com.shop.core.vo.LoginIndentity;
import com.shop.service.CartService;
import com.shop.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("cart")
public class CartController extends BaseController {
	
	@Autowired
	private CartService cartService;

	@RequestMapping("add")
	@ResponseBody
    @IsLogin
	public ResultInfo addToCart(Integer productId, Integer quantity,
                                Integer goodsId, HttpServletRequest request) {
		// 获取当前登录用户的ID
		Integer loginUserId = LoginIdentityUtil.getFromLoginId(request);
		cartService.add(productId, quantity, loginUserId, goodsId);
		return  success("添加成功", "添加成功");
	}

    @RequestMapping("list")
    @IsLogin
    public String selectForPage(HttpServletRequest request, BaseDto baseDto,Model model) {
        // 获取当前登录用户的ID
        Integer loginUserId = LoginIdentityUtil.getFromLoginId(request);
        //分页结果
		ResultListInfo<CartItem> resultList = cartService.selectForPage(loginUserId, baseDto);
		model.addAttribute("resultList",resultList);
		return "cart/list";
    }
    
    

    @RequestMapping("edit")
    @IsLogin
    @ResponseBody
    public ResultInfo edit(HttpServletRequest request, Integer quantity) {
        // 获取当前登录用户的ID
        Integer loginUserId = LoginIdentityUtil.getFromLoginId(request);
        return success("成功清空");
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    @IsLogin
    public ResultInfo delete(HttpServletRequest request, @PathVariable Integer id) {
        // 获取当前登录用户的ID
        Integer loginUserId = LoginIdentityUtil.getFromLoginId(request);
        return success("成功删除");
    }

    @RequestMapping("clear")
    @IsLogin
    @ResponseBody
    public ResultInfo clear(HttpServletRequest request) {
        // 获取当前登录用户的ID
        Integer loginUserId = LoginIdentityUtil.getFromLoginId(request);
        return success("成功清空");
    }

    @RequestMapping("count")
    @IsLogin
    @ResponseBody
    public ResultInfo countQuantity(HttpServletRequest request) {
        // 获取当前登录用户的ID
        Integer loginUserId = LoginIdentityUtil.getFromLoginId(request);
        return success("成功清空");
    }
}
