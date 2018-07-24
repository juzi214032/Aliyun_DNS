import com.aliyuncs.IAcsClient;
import com.juzi.Aliyun.dns.AliyunClient;
import com.juzi.Aliyun.dns.UpdateDNS;
import com.juzi.dom4j.XmlReader;

/**
 * Author: Juzi
 * Time: 2018/7/24 17:11
 * Blog: http://juzibiji.top
 */
public class Main {
    private static IAcsClient client = null;

    public static void main(String[] args) {
        //更新频率
        long updateTime = (Long.parseLong(XmlReader.getRoot().element("domain").elementText("update-time")))*1000;
        UpdateDNS updateDNS = new UpdateDNS();
        updateDNS.setClient(AliyunClient.getClient());
        while (true) {
            updateDNS.updateDNS();
            try {
                Thread.sleep(updateTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
