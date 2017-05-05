<!DOCTYPE html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<link href="${ctx}/favicon.ico" rel="icon" type="image/x-icon" />
	<link href="${ctx}/slider/slider.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/common.css" rel="stylesheet" type="text/css" />
	<link href="${ctx}/css/index.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${ctx}/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.tools.js"></script>
	<script type="text/javascript" src="${ctx}/js/jquery.lazyload.js"></script>
	<script type="text/javascript" src="${ctx}/slider/slider.js"></script>
	<script type="text/javascript" src="${ctx}/js/common.js"></script>
	<style type="text/css">
		.header {
			margin-bottom: 0px;
		}
	</style>
<script type="text/javascript">
$().ready(function() {

	var $productCategoryMenuItem = $("#productCategoryMenu li");
	var $slider = $("#slider");
	var $newArticleTab = $("#newArticle ul.tab");
	var $hotGoodsImage = $("div.hotGoods img");
	
	$productCategoryMenuItem.hover(
		function() {
			$(this).children("div.menu").show();
		}, function() {
			$(this).children("div.menu").hide();
		}
	);
	
	$slider.nivoSlider({
		effect: "random",
		animSpeed: 1000,
		pauseTime: 6000,
		controlNav: true,
		keyboardNav: false,
		captionOpacity: 0.4
	});
	
	$newArticleTab.tabs("ul.tabContent", {
		tabs: "li",
		event: "mouseover"
	});
	
	$hotGoodsImage.lazyload({
		threshold: 100,
		effect: "fadeIn",
		skip_invisible: false
	});

});
</script>
</head>
<body>
<script type="text/javascript">
$().ready(function() {

	var $headerName = $("#headerName");
	var $headerLogin = $("#headerLogin");
	var $headerRegister = $("#headerRegister");
	var $headerLogout = $("#headerLogout");
	var $goodsSearchForm = $("#goodsSearchForm");
	var $keyword = $("#goodsSearchForm input");
	var defaultKeyword = "商品搜索";
	
	var username = getCookie("username");
	var nickname = getCookie("nickname");
	if ($.trim(nickname) != "") {
		$headerName.text(nickname).show();
		$headerLogout.show();
	} else if ($.trim(username) != "") {
		$headerName.text(username).show();
		$headerLogout.show();
	} else {
		$headerLogin.show();
		$headerRegister.show();
	}
	
	$keyword.focus(function() {
		if ($.trim($keyword.val()) == defaultKeyword) {
			$keyword.val("");
		}
	});
	
	$keyword.blur(function() {
		if ($.trim($keyword.val()) == "") {
			$keyword.val(defaultKeyword);
		}
	});
	
	$goodsSearchForm.submit(function() {
		if ($.trim($keyword.val()) == "" || $keyword.val() == defaultKeyword) {
			return false;
		}
	});

});
</script>
	[#--header--]
    [#include "include/header.ftl" /]
	
<div class="container index">
		<div class="row">
			<div class="span2">
					<div id="productCategoryMenu" class="productCategoryMenu">
						<ul>
							[@product_category_root_list count = 6]
								[#if productCategories?has_content ]
									[#list productCategories as productCategory ]
										<li>
											<div class="item">
												<div>
													[@product_category_children_list productCategoryId = productCategory.id count=3]
														[#if productChildrenCategories?has_content ]
															[#list productChildrenCategories as productCategory ]
																<a href="${ctx}/goods/list/${productCategory.id}">
																	<strong>${productCategory.name}</strong>
																</a>
															[/#list]
														[/#if]
													[/@product_category_children_list]
												</div>
												<div>
													[@brand_list productCategoryId = productCategory.id count=4]
														[#if brands?has_content ]
															[#list brands as brand ]
																<a href="${ctx}/brand/list/${brand.id}">
																	${brand.name}
																</a>
															[/#list]
														[/#if]
													[/@brand_list]
												</div>
											</div>
											
											[#--展开分类--]
											<div class="menu">
												[@product_category_children_list productCategoryId = productCategory.id count=7]
													[#if productChildrenCategories?has_content ]
														[#list productChildrenCategories as productCategory ]
															<dl class="clearfix[#if !productCategory_has_next] last[/#if]">
																<dt>
																	<a href="${ctx}/goods/list/${productCategory.id}">${productCategory.name}</a>
																</dt>
																[@product_category_children_list productCategoryId = productCategory.id count=7]
																	[#list productChildrenCategories as productCategory ]
																		<dd>
																			<a href="${ctx}/goods/list/${productCategory.id}">${productCategory.name}</a>[#if productCategory_has_next]|[/#if]
																		</dd>
																	[/#list]
																[/@product_category_children_list]
															</dl>
														[/#list]
													[/#if]
												[/@product_category_children_list]
											
											<div class="auxiliary">
												[@brand_list productCategoryId = productCategory.id count = 8]
													[#if brands?has_content ]
														<div>
															<strong>推荐品牌</strong>
															[#list brands as brand]
																<a href="${ctx}/brand/list/${brand.id}">${brand.name}</a>
															[/#list]
														</div>
													[/#if]
												[/@brand_list]
												[@promotion_list productCategoryId = productCategory.id hasEnded = false count = 4]
													[#if promotions?has_content]
														<div>
															<strong>热门促销</strong>
															[#list promotions as promotion]
																[#if promotion.image?has_content]
																	<a href="${ctx}/brand/list/${promotion.id}" title="${promotion.title}">
																		<img src="${promotion.image}" alt="${promotion.title}" />
																	</a>
																[/#if]
															[/#list]
														</div>
													[/#if]
												[/@promotion_list]
											</div>
										</div>
											
										</li>
									[/#list]
								[/#if]
							[/@product_category_root_list]

						</ul>
				</div>
			</div>
			
			[#--广告1--]
			<div class="span10">
				[@ad_position id=1 /]		
			</div>
		</div>
		
		[#--第二个广告位--]		
		<div class="row">
			<div class="span9">
				[@ad_position id=2 /]
			</div>
			<div class="span3">
			[@article_category_root_list count = 2]
				<div id="newArticle" class="newArticle">
				[#if articleCategories?has_content ]
					<ul class="tab">
						[#list articleCategories as articleCategory ]
							<li>
								<a href="${ctx}/article_category/${articleCategory.id}" target="_blank">${articleCategory.name}</a>
							</li>
						[/#list]
					</ul>
					
					[#--获取文章分类下的文章--]
					[#list articleCategories as articleCategory ]
						[@article_list categoryId=articleCategory.id count= 6 ]
							<ul class="tabContent">
								[#list articles as article ]
									<li>
										<a href="${ctx}/article/${article.id}" title="${article.title}" target="_blank">${article.title}</a>
									</li>
								[/#list]
							</ul>
						[/@article_list]
					[/#list]
					
				[/#if]
			[/@article_category_root_list]
				</div>
			</div>
		</div>
		<div class="row">
			<div class="span12">
				<div class="middleAd">
					[@ad_position id = 3 /]
				</div>			
			</div>
		</div>
		
		[#--热门商品展示--]
		[#--获取三个分类--]
		[@product_category_root_list count = 3]
			[@ad_position id = 4]
				[#if adPosition??]
					[#assign adIterator = adPosition.ads.iterator() /]
				[/#if]
			[/@ad_position]
			
			[#--循环分类--]
			[#list productCategories as productCategory ]
			<div class="row">
				<div class="span12">
					<div class="hotGoods">
						<dl class="title${productCategory_index + 1}">
							<dt>
								<a href="${ctx}/goods/list/${productCategory.id}">${productCategory.name}</a>
							</dt>
							[#--获取到子分类--]
							[@product_category_children_list productCategoryId=productCategory.id count=7]
								[#list productChildrenCategories as productChildrenCategory ]
									<dd>
										<a href="${ctx}/goods/list/${productChildrenCategory.id}">${productChildrenCategory.name}</a>
									</dd>
								[/#list]
							[/@product_category_children_list]
							
						</dl>
						
						[#--广告位--]
						[#if adIterator?? && adIterator.hasNext()]
							[#assign ad = adIterator.next() /]
							<div>
								[#if ad.url??]
									<a href="${ad.url}">
										<img src="${ad.path}" alt="${ad.title}" title="${ad.title}" />
									</a>
								[#else]
									<img src="${ad.path}" alt="${ad.title}" title="${ad.title}" />
								[/#if]
							</div>
						[/#if]
						
						[#--分类下的热门商品开始--]
						[@goods_list productCategoryId = productCategory.id count = 10 tagId = 3]
							<ul>
							[#list goodsList as good ]
								[#if good_index < 5 ]
									<li>
										<a href="${ctx}/good/${good.id}" title="${good.name}" target="_blank">
											<div>
												<span title="${good.name}">${good.name}</span>
												<em title="${good.caption}">${good.caption}</em>
											</div>
											<strong>￥${good.price}</strong>
											<img src="${ctx}/upload/image/blank.gif" data-original="${good.image}" />
										</a>
									</li>
								[#else]
									<li class="low">
										<a href="${ctx}/good/${good.id}" title="${good.name}" target="_blank">
											<img src="${ctx}/upload/image/blank.gif" data-original="${good.image}" />
											<span title="${good.name}">${good.name}</span>
											<strong>￥${good.price}</strong>
										</a>
									</li>
								[/#if]
							[/#list]
							</ul>
						[/@goods_list]
					</div>
				</div>
			</div>
			[/#list]
			
		[/@product_category_root_list]
		
					
		
		<div class="row">
			<div class="span12">
				<div class="bottomAd">
					<ul>
						<li>
							<a href="#">
								<img src="http://image.demo.shopxx.net/4.0/201501/e44f5fe1-523d-4ba5-af75-c60ff9125ed4.jpg" alt="数码相机" title="数码相机" />
							</a>
						</li>
						<li>
							<a href="#">
								<img src="http://image.demo.shopxx.net/4.0/201501/b955bbb2-b9ad-4e6b-9dc2-5159a18cdaee.jpg" alt="智能手表" title="智能手表" />
							</a>
						</li>
						<li>
							<a href="#">
								<img src="http://image.demo.shopxx.net/4.0/201501/dff83843-d61a-4406-9a00-1d5efe7fbe38.jpg" alt="智能手机" title="智能手机" />
							</a>
						</li>
					</ul>
				</div>			
			</div>
		</div>
		
		<div class="row">
			<div class="span12">
				<div class="hotBrand">
					<ul class="clearfix">
							<li>
								<a href="" title="苹果">
									<img src="http://image.demo.shopxx.net/4.0/201501/a8275260-f9fa-4e20-8173-35b755fabb14.gif" alt="苹果" />
								</a>
							</li>
							<li>
								<a href="" title="三星">
									<img src="http://image.demo.shopxx.net/4.0/201501/8aa08a42-f5b3-4f52-bea0-5ee8bd123b0c.gif" alt="三星" />
								</a>
							</li>
							<li>
								<a href="" title="索尼">
									<img src="http://image.demo.shopxx.net/4.0/201501/dd75c116-51a7-4fbd-b014-6cf4bedcd0bb.gif" alt="索尼" />
								</a>
							</li>
							<li>
								<a href="" title="华为">
									<img src="http://image.demo.shopxx.net/4.0/201501/2a5efa56-c4cd-4984-b11a-d56cadca6cff.gif" alt="华为" />
								</a>
							</li>
							<li>
								<a href="" title="魅族">
									<img src="http://image.demo.shopxx.net/4.0/201501/72657c6c-d279-4952-ac20-1abcff776b07.gif" alt="魅族" />
								</a>
							</li>
							<li>
								<a href="" title="佳能">
									<img src="http://image.demo.shopxx.net/4.0/201501/081d4e29-b631-4a49-8672-792a1308ce97.gif" alt="佳能" />
								</a>
							</li>
							<li>
								<a href="" title="尼康">
									<img src="http://image.demo.shopxx.net/4.0/201501/5bd8bec2-f2df-4c84-937a-6eead637abd4.gif" alt="尼康" />
								</a>
							</li>
							<li>
								<a href="" title="松下">
									<img src="http://image.demo.shopxx.net/4.0/201501/e921d086-358d-4610-9a53-b46a551b6557.gif" alt="松下" />
								</a>
							</li>
							<li>
								<a href="" title="戴尔">
									<img src="http://image.demo.shopxx.net/4.0/201501/1ff77e2d-6190-4fea-9e12-be75d2d4ce83.gif" alt="戴尔" />
								</a>
							</li>
							<li>
								<a href="" title="惠普">
									<img src="http://image.demo.shopxx.net/4.0/201501/271e4fc7-4451-488b-9d10-a7f80b41d719.gif" alt="惠普" />
								</a>
							</li>
					</ul>
				</div>
			</div>
		</div>
		
		
	</div>
<div class="footer">
	<div class="service clearfix">
		<dl>
			<dt class="icon1">新手指南</dt>
			<dd>
				<a href="#">购物流程</a>
			</dd>
			<dd>
				<a href="#">会员注册</a>
			</dd>
			<dd>
				<a href="#">购买宝贝</a>
			</dd>
			<dd>
				<a href="#">支付货款</a>
			</dd>
			<dd>
				<a href="#">用户协议</a>
			</dd>
		</dl>
		<dl>
			<dt class="icon2">特色服务</dt>
			<dd>
				<a href="#">购物流程</a>
			</dd>
			<dd>
				<a href="#">会员注册</a>
			</dd>
			<dd>
				<a href="#">购买宝贝</a>
			</dd>
			<dd>
				<a href="#">支付货款</a>
			</dd>
			<dd>
				<a href="#">用户协议</a>
			</dd>
		</dl>
		<dl>
			<dt class="icon3">支付方式</dt>
			<dd>
				<a href="#">购物流程</a>
			</dd>
			<dd>
				<a href="#">会员注册</a>
			</dd>
			<dd>
				<a href="#">购买宝贝</a>
			</dd>
			<dd>
				<a href="#">支付货款</a>
			</dd>
			<dd>
				<a href="#">用户协议</a>
			</dd>
		</dl>
		<dl>
			<dt class="icon4">配送方式</dt>
			<dd>
				<a href="#">购物流程</a>
			</dd>
			<dd>
				<a href="#">会员注册</a>
			</dd>
			<dd>
				<a href="#">购买宝贝</a>
			</dd>
			<dd>
				<a href="#">支付货款</a>
			</dd>
			<dd>
				<a href="#">用户协议</a>
			</dd>
		</dl>
		<div class="qrCode">
			<img src="/images/qr_code.gif" alt="官方微信" />
			官方微信
		</div>
	</div>
	<div class="bottom">
		<div class="bottomNav">
			<ul>
				[@navigation_list position = 2]
					[#list navigations as navigation]
						<li>
							<a href="${ctx}${navigation.url}" [#if navigation.isBlankTarget] target="_blank"[/#if]>
							${navigation.name}</a>
							[#if navigation_has_next]|[/#if]
						</li>
					[/#list]
				[/@navigation_list]
			</ul>
		</div>
		<div class="info">
			<p>湘ICP备10000000号</p>
			<p>Copyright © 2005-2015 尚HAI购 版权所有</p>
			[#-- 
			[@friend_link_list count = 10]
				<ul>
					[#list friendLinks as friendLink]
						<li>
							<a href="" target="_blank">
								<img src="" alt="" />
							</a>
						</li>
					[/#list]
				</ul>
			[/@friend_link_list]--]
		</div>
	</div>
</div>
</body>
</html>