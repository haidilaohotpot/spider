package com.wonder4work.spider.pipeline;

import com.alibaba.fastjson.JSON;
import com.wonder4work.spider.entity.Task;
import org.apache.commons.io.FileUtils;
import org.omg.IOP.Encoding;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @since 1.0.0 2020/2/24
 */
public class ZBJPipeline implements Pipeline {


    public void process(ResultItems resultItems, us.codecraft.webmagic.Task task) {

        List<Task> taskList = resultItems.get("taskList");
        System.out.println(JSON.toJSONString(taskList));


        try {
            FileUtils.writeStringToFile(new File("D:\\task.json"),JSON.toJSONString(taskList)+ "\r\n","utf-8",true);
//            FileUtils.writeStringToFile(new File("D:\\epwk.json"),JSON.toJSONString(taskList)+ "\r\n","utf-8",true);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
