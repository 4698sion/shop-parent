package com.shop.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.core.dto.MemberDto;
import com.shop.core.model.Member;
import com.shop.core.util.AssertUtil;
import com.shop.core.util.MD5;
import com.shop.core.vo.LoginIndentity;
import com.shop.dao.MemberDao;
@Service
public class MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	/**
	 * 登录
	 * @param userName
	 * @param password
	 * @param verifyCode
	 * @param sessionVerifyCode
	 * @return 
	 */
	public LoginIndentity login(String userName, String password, String verifyCode, 
			String sessionVerifyCode) {
		
		// 基本参数验证——非空以及验证码是否正确
		AssertUtil.notNull(userName, "请输入用户名或邮箱");
		AssertUtil.notNull(password, "请输入密码");
		AssertUtil.notNull(verifyCode, "请输入验证码");
		AssertUtil.isTrue(!verifyCode.equals(sessionVerifyCode), "验证码输入有误，请重新输入");
		// 根据userName(username, email) 去数据库查询 --验证
		Member member = memberDao.findByUserNameOrEmail(userName);
		AssertUtil.isTrue(member == null, "用户名错误，请重新输入");
		
		// 验证password --加密验证
		password = MD5.toMD5(password);
		System.out.println(password);
		AssertUtil.isTrue(!password.equals(member.getPassword()), "密码错误，请重新输入");

		// 构建一个登录信息
		LoginIndentity loginIndentity = new LoginIndentity();
		BeanUtils.copyProperties(member, loginIndentity);
		return loginIndentity;
	}
	
	/**
	 * 注册
	 * @param memberDto
	 * @param sessionVerifyCode
	 * @param sessionPhoneVerifyCode
	 * @return
	 */
	public LoginIndentity register(MemberDto memberDto,
									String sessionVerifyCode,String sessionPhoneVerifyCode) {
		
		//基本参数验证
		checkRegisterParams(memberDto,sessionVerifyCode,sessionPhoneVerifyCode);
		
		
		//唯一性验证 username email phone
		checkResgisterUnique(memberDto);
		
		//提交数据库
		Member member = new Member();
		String password =memberDto.getPassword();
		member.setPassword(MD5.toMD5(password));
		memberDao.insert(member);
		
		LoginIndentity loginIndentity =buildLoginIndentity(member);
		return loginIndentity;
	}

	/**
	 * 唯一性
	 * @param memberDto
	 */
	private void checkResgisterUnique(MemberDto memberDto) {
		Member member = memberDao.findByColumn("username",memberDto.getName() );
		AssertUtil.isTrue(member!=null, "该用户名已注册");
		member=memberDao.findByColumn("email",memberDto.getEmail());
		AssertUtil.isTrue(member!=null,"该邮箱已注册");
		member =memberDao.findByColumn("phone", memberDto.getPhone());
		AssertUtil.isTrue(member!=null,	"该手机号已注册");
	}
	/**
	 * 注册基本参数验证
	 * @param memberDto
	 * @param sessionVerifyCode
	 * @param sessionPhoneVerifyCode
	 */
	private static void checkRegisterParams(MemberDto memberDto,
											String sessionVerifyCode,String sessionPhoneVerifyCode) {
		AssertUtil.notNull(memberDto.getName(),"请输入用户名");
		AssertUtil.notNull(memberDto.getPassword(), "请输入密码");
		AssertUtil.isTrue(!memberDto.getPassword().equals(memberDto.getRePassword()), "两次密码输入不相同");
		AssertUtil.notNull(memberDto.getEmail(), "请输入邮箱");
		AssertUtil.notNull(memberDto.getPhone(), "请输入手机号");
		AssertUtil.notNull(memberDto.getPhoneVerifyCode(), "请输入手机验证码");
		AssertUtil.isTrue(!memberDto.getPhoneVerifyCode().toLowerCase().equals(sessionPhoneVerifyCode), "手机验证码输入有误，请重新输入");
		AssertUtil.notNull(memberDto.getVerifyCode(), "请输入验证码");
		AssertUtil.isTrue(!memberDto.getVerifyCode().toLowerCase().equals(sessionVerifyCode), "验证码输入有误，请重新输入");
		
	}
	
	/**
	 * 构建LoginIdentity结果集
	 * @param member
	 * @return
	 */
	private LoginIndentity buildLoginIndentity(Member member) {
		LoginIndentity loginIndentity = new LoginIndentity();
		BeanUtils.copyProperties(member, loginIndentity);
		return loginIndentity;
	}
}
