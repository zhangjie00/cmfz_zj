package com.baizhi.annotcation;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})   //只能在方法上生效
@Retention(RetentionPolicy.RUNTIME)  //运行时生效
public @interface DelCache {
}
