package eu.ydp.gwtutil.client.animation.css;

import static org.fest.assertions.api.Assertions.*;
import static org.mockito.Mockito.*;

import eu.ydp.gwtutil.client.animation.AnimationConfig;
import eu.ydp.gwtutil.client.util.geom.Size;
import java.util.List;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CssAnimationClassBuilderTest {

	private CssAnimationClassBuilder testObj;

	@Mock
	StyleAppender styleAppender;
	@Captor
	private ArgumentCaptor<String> classBodyCaptor;
	private Size frameSize = new Size(20, 30);
	private String source = "http://dummy/jj.png";
	private AnimationConfig animationConfig = new AnimationConfig(20, frameSize, source);
	private Size imageSize = new Size(200, 30);
	private String animationStyleName = "HTTP___DUMMY_JJ_PNG";

	@Before
	public void before() {
		CssKeyFrameBuilder cssKeyFrameBuilder = new CssKeyFrameBuilder(styleAppender);
		testObj = new CssAnimationClassBuilder(styleAppender, cssKeyFrameBuilder);
	}

	@Test
	public void shouldReturnAnimationCssClassName() throws Exception {
		// when
		String animationCssClassName = testObj.createAnimationCssClassName(animationConfig, imageSize);

		// then
		assertThat(animationCssClassName).isEqualTo(animationStyleName);
	}

	@Test
	public void shouldReturnSameClassNames() throws Exception {
		// when
		String animationCssClassName = testObj.createAnimationCssClassName(animationConfig, imageSize);
		String secondAnimationCssClassName = testObj.createAnimationCssClassName(animationConfig, imageSize);

		// then
		assertThat(animationCssClassName).isEqualTo(secondAnimationCssClassName);
	}

	@Test
	public void shouldCreateKeyframesForDifferentBrowsers() throws Exception {
		// given
		final String webkitKeyframes = "@-webkit-keyframes HTTP___DUMMY_JJ_PNG_keyframes { from {background-position:0px} to {background-position:-200px} }";
		final String mozKeyframes = "@-moz-keyframes HTTP___DUMMY_JJ_PNG_keyframes { from {background-position:0px} to {background-position:-200px} }";
		final String w3cKeyframes = "@keyframes HTTP___DUMMY_JJ_PNG_keyframes { from {background-position:0px} to {background-position:-200px} }";

		// when
		testObj.createAnimationCssClassName(animationConfig, imageSize);
		verify(styleAppender, times(4)).appendStyleToDocument(classBodyCaptor.capture());
		List<String> generatedClass = classBodyCaptor.getAllValues();

		// then
		assertThat(generatedClass).contains(webkitKeyframes, mozKeyframes, w3cKeyframes);
	}

	@Test
	public void shouldAppendCssToPageBody() {
		// given
		final String requiredAnimationClassBody = " .HTTP___DUMMY_JJ_PNG {  -webkit-animation: HTTP___DUMMY_JJ_PNG_keyframes 500ms steps(10, end); -moz-animation: HTTP___DUMMY_JJ_PNG_keyframes 500ms steps(10, end); animation: HTTP___DUMMY_JJ_PNG_keyframes 500ms steps(10, end); background-image: url(http://dummy/jj.png) } ";

		// when
		String animationCssClassName = testObj.createAnimationCssClassName(animationConfig, imageSize);
		verify(styleAppender, times(4)).appendStyleToDocument(classBodyCaptor.capture());
		List<String> generatedClass = classBodyCaptor.getAllValues();

		// then
		String animationClassBody = generatedClass.get(3);
		assertThat(animationClassBody).contains(animationCssClassName);
		assertThat(requiredAnimationClassBody).isEqualTo(animationClassBody);
	}

	@Test
	public void shouldEscapeCharactersInClassName() {
		// given
		final String expectedClassName = "HTTP___LOCALHOST_59509_EMPIRIAPLAYER_CONTENT_C_3A_5CYPUBLISHER_5C1_52_5CLEARNINGUNIT1_5CBUILD_5CEMPIRIA_2_COMMON_TUTOR_CARLOS_ALLOK_PNG";

		String invalidSource = "http://localhost:59509/empiriaPlayer/content/C%3A%5CyPublisher%5C1_52%5Clearningunit1%5Cbuild%5Cempiria_2/common/tutor_carlos_allok.png";
		AnimationConfig invalidConfig = new AnimationConfig(30, new Size(40, 50), invalidSource);

		// when
		String animationCssClassName = testObj.createAnimationCssClassName(invalidConfig, new Size(60, 70));

		// then
		assertThat(animationCssClassName).isEqualTo(expectedClassName);
	}
}
