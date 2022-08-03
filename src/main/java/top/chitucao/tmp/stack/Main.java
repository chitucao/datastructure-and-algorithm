package top.chitucao.tmp.stack;

import org.junit.Test;

/**
 * @author dennyfly
 * @since 2021/11/26 14:49
 */
public class Main {

    @Test
    public void testBrowser() {
        Browser browser = new Browser();
        browser.open("http://www.baidu.com");
        browser.open("http://news.baidu.com/");
        browser.open("http://news.baidu.com/ent");
        browser.goBack();
        browser.goBack();
        browser.goForward();
        browser.open("http://www.qq.com");
//        browser.goForward();
        browser.goBack();
        browser.goForward();
        browser.goBack();
        browser.goBack();
//        browser.goBack();
    }
}
