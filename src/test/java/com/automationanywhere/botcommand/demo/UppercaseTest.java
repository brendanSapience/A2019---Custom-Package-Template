package com.automationanywhere.botcommand.demo;

import com.automationanywhere.botcommand.data.Value;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UppercaseTest {

    Uppercase command = new Uppercase();

    @DataProvider(name = "strings")
    public Object[][] dataTobeTested(){
        return new Object[][]{
                {"Test", "TEST"}
        };
    }

    @Test(dataProvider = "strings")
    public void uppercaseTests(String f, String result){
        Value<String> d = command.action(f);
        String output = (String)d.get();
        //System.out.println("DEBUG:"+output);
        assertEquals(output,result);
    }
}
