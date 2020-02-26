package com.wonder4work.spider;

import com.wonder4work.spider.pipeline.EPWKPipeline;
import com.wonder4work.spider.pipeline.MJUPipeline;
import com.wonder4work.spider.pipeline.MSPipeline;
import com.wonder4work.spider.processor.EPWKProcessor;
import com.wonder4work.spider.processor.MJUProcessor;
import com.wonder4work.spider.processor.MSProcessor;
import com.wonder4work.spider.processor.ZBJProcessor;
import us.codecraft.webmagic.Spider;

/**
 * @since 1.0.0 2020/2/23
 */
public class SpiderBootstrap {


    public static void main(String[] args) {

        Spider.create(new MSProcessor())
//                .addUrl("https://task.zbj.com/page1")
//                .addUrl("https://www.epwk.com/task/page2.html")
//                .addUrl("http://www.mju.edu.cn/xxyw/list.htm")
                .addUrl("https://codemart.com/projects?page=21054")
                .addPipeline(new MSPipeline())
                .thread(5)
                .run();

    }


}
