import org.junit.Test;

import java.text.SimpleDateFormat;

/**
 * Created by gg on 2014/9/12.
 */
public class test {
    @Test
    public void testtemp(){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
    }
}
