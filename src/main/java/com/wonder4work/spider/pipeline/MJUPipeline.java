package com.wonder4work.spider.pipeline;

import com.alibaba.fastjson.JSON;
import com.wonder4work.spider.entity.Article;
import org.apache.commons.io.FileUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.io.File;
import java.io.IOException;

/**
 * @since 1.0.0 2020/2/24
 */
public class MJUPipeline implements Pipeline {
    public void process(ResultItems resultItems, Task task) {

        Article article = resultItems.get("article");
        System.out.println(JSON.toJSONString(article));

        try {

            if (article!=null&&article.getTitle()!=null){

                FileUtils.writeStringToFile(new File("D:\\mju.json"),JSON.toJSONString(article)+ "\r\n","utf-8",true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
