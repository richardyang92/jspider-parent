package com.ry.jspider.test;

/**
 * Created by yangyang on 2016/12/25.
 */
public class CharacterUtilTest {
    public static void main(String[] args) throws Exception {

//        if (args.length < 1) {
//            System.out.println("usage:Main url <int>lang");
////            return;
//        }
//        int lang = (args.length == 2) ? Integer.parseInt(args[1]) : nsPSMDetector.ALL;
//        nsDetector detector = new nsDetector(lang);
//
//        detector.Init(new nsICharsetDetectionObserver() {
//
//            public void Notify(String charset) {
//                HtmlCharsetDetector.found = true;
//                System.out.println("charset=" + charset);
//            }
//        });
//        URL url = new URL("http://search.51job.com/list/000000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=");
//        BufferedInputStream impBufferedInputStream = new BufferedInputStream(url.openStream());
//        byte[] buffer = new byte[1024];
//        int len;
//        boolean done = false;
//        boolean isAscii = true;
//        while ((len = impBufferedInputStream.read(buffer, 0, buffer.length)) != -1) {
//
//            if (isAscii) {
//                isAscii = detector.isAscii(buffer, len);
//            }
//            if (!isAscii && !done) {
//                done = detector.DoIt(buffer, len, false);
//            }
//        }
//        detector.DataEnd();
//        if (isAscii) {
//            System.out.println("charset=ascii");
//        }
    }
}
