package tests.properties;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class SystemPropertiesTests {
    @Disabled
    @Test
    void simplePropertyTest00(){
        String browserName = System.getProperty("browser");
        System.out.println(browserName);
    }

    @Disabled
    @Test
    void simplePropertyTest01(){
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser");
        System.out.println(browserName);//opera
    }

    @Disabled
    @Test
    void simplePropertyTest02(){
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName);//firefox
    }

    @Test
    void simplePropertyTest03(){
        System.setProperty("browser", "opera");
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName);//opera
    }

    @Test
    @Tag("one_property_test")
    void simplePropertyTest04(){
        String browserName = System.getProperty("browser", "firefox");
        System.out.println(browserName);
        //gradle clean one_property -Dbrowser=safari
        //safari
    }

    @Test
    @Tag("many_property_test")
    void simplePropertyTest05(){
        String browserName = System.getProperty("browser", "firefox");
        String browserVersion = System.getProperty("browser_version", "105");
        String browserSize = System.getProperty("browser_size", "1920x1080");

        System.out.println(browserName);//opera
        System.out.println(browserVersion);//opera
        System.out.println(browserSize);//opera

        /*
        From Idea:
        firefox
        105
        1920x1080

        From terminal:
        gradle clean many_property
        firefox
        105
        1920x1080

        From terminal with params:
        gradle clean many_property -Dbrowser=safari
        safari
        105
        1920x1080

        From terminal with params:
        gradle clean many_property -Dbrowser=safari -Dbrowser_version=99 -Dbrowser_size=1398x780
        safari
        99
        1398x780
         */
    }
    @Test
    @Tag("hello")
    void simplePropertyTest06() {
        System.out.println("Hello, " + System.getProperty("user_name", "unknown student"));

        /*
        gradle clean hello -Duser_name=Alex
        Hello, Alex

        gradle clean hello -Duser_name=Alex Egorov
        FAILURE: Build failed with an exception.
        What went wrong:
        Task 'Egorov' not found in root project 'c15_hw_jenkins'.

        gradle clean hello -Duser_name="Alex Egorov"
        gradle clean hello "-Duser_name=Alex Egorov"
        Hello, Alex Egorov

         */
    }

}
