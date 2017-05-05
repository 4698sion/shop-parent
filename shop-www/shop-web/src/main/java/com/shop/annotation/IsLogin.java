package com.shop.annotation;

import com.shop.core.model.Cart;

import java.lang.annotation.*;

/**
 * Created by TW on 2017/5/4.
 */
@Target(ElementType.METHOD) // 作用域
@Retention(RetentionPolicy.RUNTIME) //生命周期
@Documented
@Inherited
public @interface IsLogin {

}
