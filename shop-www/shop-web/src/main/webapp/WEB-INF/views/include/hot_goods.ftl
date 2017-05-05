[#if productCategory?exists ]
	[@goods_list productCategoryId=productCategory.id count = 3 tagId = 3]
		[#if goodsList?has_content]
			<div class="hotGoods">
				<dl>
					<dt>热销商品</dt>
					[#list goodsList as good ]
						<dd>
							<a href="${ctx}/goods/detail/${good.id}">
								<img src="${good.image}" alt="${good.name}" />
								<span title="${good.name}">${good.name}</span>
							</a>
							<strong>
								￥${good.price}
									<del>￥${good.marketPrice}</del>
							</strong>
						</dd>
					[/#list]
				</dl>
			</div>
		[/#if]
	[/@goods_list]	
[#else]
	[@hot_goods_list count = 3 tagId = 3]
		[#if goodsList?has_content]
			<div class="hotGoods">
				<dl>
					<dt>热销商品</dt>
					[#list goodsList as good ]
						<dd>
							<a href="${ctx}/goods/detail/${good.id}">
								<img src="${good.image}" alt="${good.name}" />
								<span title="${good.name}">${good.name}</span>
							</a>
							<strong>
								￥${good.price}
									<del>￥${good.marketPrice}</del>
							</strong>
						</dd>
					[/#list]
				</dl>
			</div>
		[/#if]
	[/@hot_goods_list]	
[/#if]

[#--
[@macro hotGoods goodsList ]
	[#list goodsList as good ]
		<dd>
			<a href="${ctx}/goods/detail/${good.id}">
				<img src="${good.image}" alt="${good.name}" />
				<span title="${good.name}">${good.name}</span>
			</a>
			<strong>
				￥${good.price}
					<del>￥${good.marketPrice}</del>
			</strong>
		</dd>
	[/#list]
[/@macro] 
--]