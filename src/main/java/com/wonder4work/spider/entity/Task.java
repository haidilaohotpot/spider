package com.wonder4work.spider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since 1.0.0 2020/2/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    // 任务名称
    private String title;

    // 类型
    private String type;

    // 描述
    private String desc;

    // 费用
    private String price;

    // 任务连接
    private String url;



}
