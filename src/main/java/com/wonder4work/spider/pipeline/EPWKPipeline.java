package com.wonder4work.spider.pipeline;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.FileUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @since 1.0.0 2020/2/24
 */
public class EPWKPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {

        com.wonder4work.spider.entity.Task myTask = resultItems.get("task");
        System.out.println(JSON.toJSONString(myTask));


        try {

            if (myTask!=null&&myTask.getTitle()!=null){

                FileUtils.writeStringToFile(new File("D:\\epwk.json"),JSON.toJSONString(myTask)+ "\r\n","utf-8",true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
