package eu.ydp.gwtutil.user.rebind.constants;

import com.google.common.collect.Lists;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(JUnitParamsRunner.class)
public class StringConstantsGeneratorTest {

    private static final String SEPARATOR = "#";
    private StringConstantsGenerator generator= new StringConstantsGenerator();

    public Object[] dataCamelCaseToComponents() {
        return $(
                $("AbcDef12GhiJk", Lists.newArrayList("abc", "def", "12", "ghi", "jk")),
                $("123", Lists.newArrayList("123")),
                $("A", Lists.newArrayList("a")),
                $("ABC", Lists.newArrayList("a", "b", "c")),
                $("", Lists.newArrayList()));
    }

    @Test
    @Parameters(method = "dataCamelCaseToComponents")
    public void camelCaseToComponents(String input, List<String> output) {
        // when
        List<String> actualOutput = generator.camelCaseToComponents(input);

        // then
        assertThat(actualOutput, equalTo(output));
    }

    public Object[] dataCombineComponents() {
        return $(
                $(Lists.newArrayList("abc", "def", "12", "ghi", "jk"), "abc#def#12#ghi#jk"),
                $(Lists.newArrayList("123"), "123"),
                $(Lists.newArrayList(), "")
        );
    }

    @Test
    @Parameters(method = "dataCombineComponents")
    public void combineComponents(List<String> input, String output) {
        // when
        String actualOutput = generator.combineComponents(input, SEPARATOR);

        // then
        assertThat(actualOutput, equalTo(output));

    }
}
