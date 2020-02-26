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
 * 解析猪八戒官网处理器
 */
public class ZBJProcessor implements PageProcessor {

    //抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    private static String mainUrl = "https://task.zbj.com";

    private static int count = 0;

    private static List<String> urlList = new ArrayList<String>();

    public void process(Page page) {

        //从页面发现后续的url地址来抓取

        Selectable xpath = page.getHtml().xpath("//*[@id='pagination-ul']/li[@class='pagin-li']/a/@href");
        List<String> getUrls = xpath.all();
        List<String> need = new ArrayList<String>();
        for (String url : getUrls) {
            String target = mainUrl + url;
            need.add(target);
        }

        page.addTargetRequests(need);
//                page.getHtml().xpath("//div[@class='page-content']/div[@class='multi-page']/a/@href").all());
//                page.getHtml().xpath("//*[@id='pagination-ul']/li[@class='pagin-li']/a/@href").all());

        //判断链接是否符合"https://task.zbj.com/page任意个数字.html"格式
        if (page.getUrl().regex("https://task.zbj.com/page[0-9]+.html").match()) {
            //定义如何抽取页面信息，并保存下来
//            List<Selectable> selectableList = page.getHtml().xpath("//*[@id='utopia_widget_7']/div[@class='demand-list']").nodes();
            List<Selectable> selectableList = page.getHtml().xpath("//*[@id='utopia_widget_7']/div[1]/div[@class='demand-card']").nodes();
            List<Task> list = new ArrayList<Task>();
            for(Selectable selectable : selectableList){
                String title = selectable.xpath("//div[@class='demand-card']/div[2]/div[1]/div/span[2]/a/text()").toString();
                String type = selectable.xpath("//div[@class='demand-card']/div[3]/div[1]/span/text()").toString();
                String desc = selectable.xpath("//div[@class='demand-card']/div[2]/div[3]/text()").toString();
                String price = selectable.xpath("//div[@class='demand-card']/div[2]/div[2]/text()").toString();
                //*[@id="utopia_widget_7"]/div[1]/div[21]/div[2]/div[2]
                String url = selectable.xpath("//div[@class='demand-card']/div[2]/div[1]/div/span[2]/a/@href").toString();
                Task task = new Task();
                task.setDesc(desc);
                task.setPrice(price);
                task.setTitle(title);
                task.setType(type);
                task.setUrl("https:"+url);
                list.add(task);
            }

            page.putField("taskList",list);
            urlList.add(page.getUrl().toString());
            count++;
        }
    }

    public Site getSite() {
        return site;
    }
}
