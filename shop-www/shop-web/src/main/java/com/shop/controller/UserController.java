package com.shop.controller;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.code.kaptcha.Constants;
import com.rabbitmq.client.AMQP.Access.Request;
import com.shop.core.base.BaseController;
import com.shop.core.base.ResultInfo;
import com.shop.core.base.ResultListInfo;
import com.shop.core.constant.Constant;
import com.shop.core.dto.MemberDto;
import com.shop.core.dto.UserDto;
import com.shop.core.model.User;
import com.shop.core.util.ResultListInfoUtil;
import com.shop.core.vo.LoginIndentity;
import com.shop.service.MemberService;
import com.shop.service.UserService;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;
	
	@PutMapping("add")
	@ResponseBody
	public Map<String, Object> addUser(User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("resultCode", 1);
		map.put("resultMessage", "success");
		
		Integer userId = userService.addUser(user);
		map.put("userId", userId);
		return map;
	}
	
	@GetMapping("get/{id}")
	@ResponseBody
	public Map<String, Object> findById(@PathVariable Integer id) {
		Map<String, Object> map = new HashMap<>();
		map.put("resultCode", 1);
		map.put("resultMessage", "success");
		
		User user = userService.findById(id);
		map.put("result", user);
		return map;
	}
	
	@GetMapping("list")
	public String selectForPage(UserDto userDto, Model model) {
		PageList<User> users = userService.selectForPage(userDto);
		ResultListInfoUtil<User> resultListInfoUtil = new ResultListInfoUtil<>();
		ResultListInfo<User> result = resultListInfoUtil.buildSuccessResultList(users, userDto);
		model.addAttribute("result", result);
		return "user_list";
	}
	
	@GetMapping("list.json")
	@ResponseBody
	public ResultListInfo<User> selectForPageJSON(UserDto userDto) {
		PageList<User> users = userService.selectForPage(userDto);
		ResultListInfoUtil<User> resultListInfoUtil = new ResultListInfoUtil<>();
		ResultListInfo<User> result = resultListInfoUtil.buildSuccessResultList(users, userDto);
		return result;
	}

	@RequestMapping("login")
	@ResponseBody
	public ResultInfo Login(String userName,String password,
							String verifyCode,HttpServletRequest request,HttpServletResponse response){
		String sessionVerifyCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		LoginIndentity loginIndentity = memberService.login(userName, password, verifyCode, sessionVerifyCode);
		request.getSession().setAttribute(Constant.USER_SESSION_KEY, loginIndentity);
		return success("登录成功");
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute(Constant.USER_SESSION_KEY);
		String ctx = request.getContextPath();
		return "redirect:" + ctx + "/index";
//		return "forward:index"
//		return "index";
	}
	
	@RequestMapping("register")
	@ResponseBody
	public ResultInfo register(MemberDto memberDto,HttpServletRequest request){
		HttpSession session=request.getSession();
		String sessionVerifyCode =(String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		String sessionPhoneVerifyCode=(String)session.getAttribute(Constant.VERIFYCODE_SESSION_KEY);
		LoginIndentity loginIndentity =memberService.register(memberDto, sessionVerifyCode, sessionPhoneVerifyCode);
		request.getSession().setAttribute(Constant.USER_SESSION_KEY, loginIndentity);
		return success("注册成功");
	}
}
