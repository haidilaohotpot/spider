package com.wonder4work.spider.pipeline;

import com.alibaba.fastjson.JSON;
import com.wonder4work.spider.entity.Item;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.IOException;

/**
 * @since 1.0.0 2020/2/25
 */
public class MSPipeline implements Pipeline {

    public void process(ResultItems resultItems, Task task) {
        Item item = resultItems.get("item");
        System.out.println(JSON.toJSONString(resultItems.getRequest().getUrl()));
        try {
            if (validate(item)){
                System.out.println(JSON.toJSONString(item));
                FileUtils.writeStringToFile(new File("D:\\ms.json"),
                        JSON.toJSONString(item)+","+ "\r\n","utf-8",true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(Item item){

        if (item!=null&&!StringUtils.isEmpty(item.getDesc())&&!StringUtils.isEmpty(item.getPrice())
                &&!StringUtils.isEmpty(item.getTitle())&&!StringUtils.isEmpty(item.getType())&&
                    !StringUtils.isEmpty(item.getWorkCycle())){
            return true;
        }
        return false;
    }
}
