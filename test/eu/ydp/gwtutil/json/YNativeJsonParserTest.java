package eu.ydp.gwtutil.json;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;

import org.junit.Test;

import eu.ydp.gwtutil.client.json.YJsonArray;
import eu.ydp.gwtutil.client.json.YJsonNumber;
import eu.ydp.gwtutil.client.json.YJsonObject;
import eu.ydp.gwtutil.client.json.YJsonString;
import eu.ydp.gwtutil.client.json.YJsonValue;

public class YNativeJsonParserTest {
	
	@Test
	public void testParseString(){	
		
		//given 
		String content="string";
		
		//when
		YJsonValue jsonValue = YNativeJsonParser.parse(content);
				
		//then
		assertThat(jsonValue, instanceOf(YJsonString.class));
		assertThat(jsonValue.isString().stringValue(), equalTo(content));		
		
	}
	
	@Test
	public void testParseNumber(){	
		
		//given 
		String content = "5";
		
		//when
		YJsonValue jsonValue = YNativeJsonParser.parse(content);
				
		//then
		assertThat(jsonValue, instanceOf(YJsonNumber.class));
		assertThat(jsonValue.isNumber().numberValue().intValue(), equalTo(Integer.parseInt(content)));		
		
	}
	
	@Test
	public void testParseArray(){	
		
		//given 
		String content = "[0,1,2,3,4,5]";
		
		//when
		YJsonValue jsonValue = YNativeJsonParser.parse(content);
				
		//then
		assertThat(jsonValue, instanceOf(YJsonArray.class));
		assertThat(jsonValue.isArray().toString(), equalTo(content));		
		
	}
	
	@Test
	public void testParseObject(){	
		
		//given 
		String content = "{\"field0\":\"a\", \"field1\":\"b\"}";
		
		//when
		YJsonValue jsonValue = YNativeJsonParser.parse(content);
		YJsonObject jsonObject = jsonValue.isObject();		
		//then
		assertThat(jsonValue, instanceOf(YJsonObject.class));
		 
		assertThat(jsonObject.get("field0").isString().stringValue(),equalTo("a"));
		assertThat(jsonObject.get("field1").isString().stringValue(),equalTo("b"));
		
	}
	
}
