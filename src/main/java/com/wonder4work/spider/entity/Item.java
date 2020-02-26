package com.wonder4work.spider.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @since 1.0.0 2020/2/25
 * 需要爬取的Model实体类 以及需要爬取的字段
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    // 任务名称
    private String title;

    // 类型
    private String type;

    // 描述
    private String desc;

    // 费用
    private String price;

    // 工作周期
    private String workCycle;

}
