package test.java.tomcat.util;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import tomcat.base.HttpRequest;
import tomcat.util.HttpRequestUtil;

import javax.servlet.http.Cookie;

/**
 * HttpRequestUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 18, 2017</pre>
 */
public class HttpRequestUtilTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: parseCookieHeader(String header)
     */
    @Test
    public void testParseCookieHeader() throws Exception {
        String header = "user_trace_token=20170219181250-c6e63f4617024d019b1a054ea1e49cf1; LGUID=20170219181251-f8b64310-f68b-11e6-9012-5254005c3644; SEARCH_ID=054447d3b9314486b043dc7b3ecaedd0; JSESSIONID=ABAAABAAAGGABCB434ADC85D10748EC9749C170C9EBD65F; _gat=1; PRE_UTM=m_cf_cpt_baidu_pc; PRE_HOST=bzclk.baidu.com; PRE_SITE=http%3A%2F%2Fbzclk.baidu.com%2Fadrc.php%3Ft%3D06KL00c00fATEwT0o_4m0FNkUsjQJkdu000002iV8H300000IDY4gW.THL0oUhY0A3qmh7GuZNCUvd-gLKM0Znquj6dn1Phn10snj04nvc4rfKd5RuKPjf1P1bknbnkrH-7wRFDPbndn1wAnj64PWnvP1Ij0ADqI1YhUyPGujYzrHfYP1R3PjczFMKzUvwGujYkP6K-5y9YIZK1rBtEILILQhk9uvqdQhPEUitOIgwVgLPEIgFWuHdVgvPhgvPsI7qBmy-bINqsmsKWThnqPWc3rHT%26tpl%3Dtpl_10085_14394_1%26l%3D1052356004%26attach%3Dlocation%253D%2526linkName%253D%2525E6%2525A0%252587%2525E9%2525A2%252598%2526linkText%253D%2525E3%252580%252590%2525E6%25258B%252589%2525E5%25258B%2525BE%2525E7%2525BD%252591%2525E3%252580%252591%2525E5%2525AE%252598%2525E7%2525BD%252591-%2525E4%2525B8%252593%2525E6%2525B3%2525A8%2525E4%2525BA%252592%2525E8%252581%252594%2525E7%2525BD%252591%2525E8%252581%25258C%2525E4%2525B8%25259A%2525E6%25259C%2525BA%2526xp%253Did%28%252522m4e160542%252522%29%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FDIV%25255B1%25255D%25252FH2%25255B1%25255D%25252FA%25255B1%25255D%2526linkType%253D%2526checksum%253D250%26ie%3Dutf-8%26f%3D8%26tn%3Dbaiduhome_pg%26wd%3D%25E6%258B%2589%25E5%258B%25BE%26oq%3D%25E6%258B%2589%25E5%258B%25BE%26rqlang%3Dcn%26issp%3D1%26inputT%3D2538; PRE_LAND=https%3A%2F%2Fwww.lagou.com%2F%3Futm_source%3Dm_cf_cpt_baidu_pc; _putrc=C221A39602C8136D; login=true; unick=%E6%9D%A8%E5%8D%93; showExpriedIndex=1; showExpriedCompanyHome=1; showExpriedMyPublish=1; hasDeliver=155; index_location_city=%E6%B7%B1%E5%9C%B3; Hm_lvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1494729785,1494730529,1495112712,1495116781; Hm_lpvt_4233e74dff0ae5bd0a3d81c6ccf756e6=1495116829; LGSID=20170518221255-166f66fd-3bd4-11e7-84ef-5254005c3644; LGRID=20170518221343-336c16ab-3bd4-11e7-84ef-5254005c3644; _ga=GA1.2.993974604.1487499171; _gid=GA1.2.101487600.1495116830";
        String header1 = "user_trace_token=20170219181250-c6e63f4617024d019b1a054ea1e49cf1";
        Cookie[] cookies = HttpRequestUtil.parseCookieHeader(header1);
        assert true : cookies.length > 0;
    }


} 
