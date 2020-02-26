package com.wonder4work.spider.processor;

import com.wonder4work.spider.entity.Article;
import com.wonder4work.spider.entity.Task;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0 2020/2/24
 * 闽江学院 处理器
 */
public class MJUProcessor implements PageProcessor {

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36");

    private static String mainUrl = "http://www.mju.edu.cn";

    private static int count = 0;

    private static List<String> urlList = new ArrayList<String>();

    public void process(Page page) {

        //从页面发现后续的url地址来抓取

        Selectable xpath = page.getHtml().xpath("/html/body/div[3]/div/div[2]/div/div[2]/div/a[@class='column-news-item']/@href");
        List<String> need = xpath.all();
        page.addTargetRequests(need);
//        List<String> links = page.getHtml().links().regex("https://www.epwk.com/task/page[0-9]+.html").all();
//        page.addTargetRequests(links);
//                page.getHtml().xpath("//div[@class='page-content']/div[@class='multi-page']/a/@href").all());
//                page.getHtml().xpath("//*[@id='pagination-ul']/li[@class='pagin-li']/a/@href").all());

        //判断链接是否符合"https://task.zbj.com/page任意个数字.html"格式
        if (page.getUrl().regex("https://mp.weixin.qq.com/\\w+").match()) {

            String title = page.getHtml().xpath("//*[@id='activity-name']/text()").toString();
            String content = page.getHtml().xpath("//*[@id='js_content']").toString();

            Article article = new Article();
            article.setTitle(title);
            article.setContent(content);
            page.putField("article",article);

            urlList.add(page.getUrl().toString());
            count++;
        }
    }

    public Site getSite() {
        return site;
    }
}
