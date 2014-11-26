package Test;

import GsonServer.GsonConverter;

class TestClass {
    private int bubu;
    private int bubi;
    private float bubini;
    private String name;

    TestClass(int bubu, int bubi, float bubini, String name) {
        this.bubu = bubu;
        this.bubi = bubi;
        this.bubini = bubini;
        this.name = name;
    }
}


/**
 * Created by t.garuglieri on 19/11/2014.
 */
public class GsonTest {


    public static void main(String[] args) {

        TestClass obj = new TestClass(1, 2, 3.45F, "BUMMMMM");

        String gsonStr = GsonConverter.convertObject(obj);

        System.out.println(gsonStr);

        TestClass reconvertedObj = (TestClass) GsonConverter.getObject(gsonStr, TestClass.class);

    }
}
