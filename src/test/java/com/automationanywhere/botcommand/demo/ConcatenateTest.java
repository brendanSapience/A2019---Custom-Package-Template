package com.automationanywhere.botcommand.demo;
import com.automationanywhere.botcommand.data.Value;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static  org.testng.Assert.assertEquals;
public class ConcatenateTest {

    Concatenate command = new Concatenate();

    @DataProvider(name = "strings")
    public Object[][] dataTobeTested(){
        return new Object[][]{
                {"A", "B", "AB"}
        };
    }

    @Test(dataProvider = "strings")
    public void keyphraseTests(String f, String s, String e){
        Value<String> d = command.action(f,s);
        String output = (String)d.get();
        assertEquals(output,e);
    }
}
