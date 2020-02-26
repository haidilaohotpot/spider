package com.wonder4work.spider.processor;

import com.wonder4work.spider.entity.Item;
import com.wonder4work.spider.entity.Task;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @since 1.0.0 2020/2/25
 */
public class MSProcessor implements PageProcessor {
    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");
    private static String mainUrl = "https://codemart.com";
    private static int count = 21100;
    private static List<String> urlList = new ArrayList<String>();
    public void process(Page page) {
        boolean match = page.getUrl().regex("https://codemart.com/project/[0-9]").match();
        if (match) {
            String ing = page.getHtml().xpath("//*[@id='mart-reward-detail']/div[1]/section[1]/div[1]/span[2]/text()").toString();
            if (StringUtils.contains(ing,"招募中")){
                String title = page.getHtml().xpath("//*[@id='mart-reward-detail']/div[1]/section[1]/div[1]/span[1]/text()")
                        .toString();
                String price = page.getHtml().xpath("//*[@id='mart-reward-detail']/div[1]/section[1]/div[3]/span[1]/text()")
                        .toString();
                String desc = page.getHtml().xpath("//*[@id='mart-reward-detail']/div[1]/section[3]/div[1]/div/p/text()")
                        .toString();
                String type = page.getHtml().xpath("//*[@id='mart-reward-detail']/div[1]/section[1]/div[3]/span[2]/text()")
                        .toString();
                String workerCycle = page.getHtml().xpath("//*[@id='mart-reward-detail']/div[1]/section[1]/div[3]/span[3]/text()")
                        .toString();
                String newPrice = StringUtils.replaceChars(price,"￥","");
                Item item = new Item();
                item.setDesc(desc);item.setPrice(newPrice);item.setTitle(title);
                item.setType(type.trim());item.setWorkCycle(workerCycle.trim());
                page.putField("item",item);
            }
            count++;
        }
        page.addTargetRequest("https://codemart.com/project/"+count);
    }
    public Site getSite() {
        return site;
    }
}
