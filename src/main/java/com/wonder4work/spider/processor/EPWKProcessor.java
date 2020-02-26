package com.wonder4work.spider.processor;

import com.wonder4work.spider.entity.Task;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * @since 1.0.0 2020/2/24
 *
 * 一品威客 网站解析器
 */
public class EPWKProcessor implements PageProcessor {

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100).setDomain("www.epwk.com")
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.86 Safari/537.36")
            .addCookie("PHPSESSID","cd26c569f8687581629a991d5b9563f2604b3f38")
            .addCookie("Hm_lvt_387b8f4fdb89d4ea233922bdc6466394","1582465185,1582532721")
            .addCookie("Hm_lpvt_387b8f4fdb89d4ea233922bdc6466394","1582535378");

    private static String mainUrl = "https://www.epwk.com";

    private static int count = 0;

    private static List<String> urlList = new ArrayList<String>();

    public void process(Page page) {

        //从页面发现后续的url地址来抓取

        Selectable xpath = page.getHtml().xpath("/html/body/div[10]/div[1]/div[2]/div[4]/div[@class='task_class_list_li_box']/div[1]/h3/a/@href||/html/body/div[10]/div[1]/div[2]/div[5]/a//@href");
        List<String> need = xpath.all();
        page.addTargetRequests(need);
        List<String> links = page.getHtml().links().regex("https://www.epwk.com/task/page[0-9]+.html").all();
        page.addTargetRequests(links);
//                page.getHtml().xpath("//div[@class='page-content']/div[@class='multi-page']/a/@href").all());
//                page.getHtml().xpath("//*[@id='pagination-ul']/li[@class='pagin-li']/a/@href").all());

        //判断链接是否符合"https://task.zbj.com/page任意个数字.html"格式
        if (page.getUrl().regex("https://www.epwk.com/task/[0-9]+").match()) {

            String title = page.getHtml().xpath("/html/body/div[10]/div/div[1]/div[1]/div[2]/div[1]/a/text()").toString();
            String price = page.getHtml().xpath("/html/body/div[10]/div/div[1]/div[3]/div[1]/div/div[2]/div[1]/span[1]/span/text()").toString();
            String desc = page.getHtml().xpath("/html/body/div[10]/div/div[1]/div[3]/div[4]/div[1]/div[4]/text()").toString();
            String url = page.getHtml().xpath("/html/body/div[10]/div/div[1]/div[1]/div[2]/div[1]/a/@href").toString();
            String type = "其他";
            Task task = new Task();
            task.setUrl(url);
            task.setType(type);
            task.setTitle(title);
            task.setPrice(price);
            task.setDesc(desc);

            page.putField("task",task);

            urlList.add(page.getUrl().toString());
            count++;
        }
    }

    public Site getSite() {
        return site;
    }
}
