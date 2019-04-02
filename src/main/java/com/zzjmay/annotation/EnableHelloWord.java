package com.zzjmay.annotation;

import com.zzjmay.selector.MyHelloworldImportSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 *  import建议倒入选择器Select，灵活的选择使用那个对象装配
 * Created by zzjmay on 2019/4/2.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyHelloworldImportSelector.class)
public @interface EnableHelloWord {
}
