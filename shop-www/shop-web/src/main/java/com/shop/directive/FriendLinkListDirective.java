package com.shop.directive;

import com.shop.core.model.FriendLink;
import com.shop.service.FriendLinkService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by YuWenJing on 2017/5/6.
 */
@Component
public class FriendLinkListDirective extends BaseDeirective{

    @Autowired
    private FriendLinkService friendLinkService;

    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        BigDecimal count = (BigDecimal) getParameter("count",params);

        List<FriendLink> friendLinks = friendLinkService.findFriendLinkList(count.intValue());

        setVariable(env,body,"friendLinks",friendLinks);
    }

}
