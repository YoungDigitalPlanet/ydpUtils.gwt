package eu.ydp.gwtutil.client.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MediaCheckerImplTest {

    private static final String AUDIO_OGG = "audio/ogg";

    private static final String AUDIO_MP4 = "audio/mp4";

    @InjectMocks
    private MediaCheckerImpl testObj;

    @Mock
    private AudioWrapper audioWrapper;

    @Mock
    private UserAgentUtil userAgentUtil;

    @Test
    public void shouldNotSupportMp3WhenMimeTypeNotSupported() {
        // given
        when(audioWrapper.isMimeSupported(AUDIO_MP4)).thenReturn(Boolean.FALSE);

        // when
        Boolean result = testObj.isHtml5Mp3Supported();

        // then
        assertFalse(result);
    }

    @Test
    public void shouldSupportMp3WhenMimeTypeSupported() {
        // given
        when(audioWrapper.isAudioSupported()).thenReturn(Boolean.TRUE);
        when(audioWrapper.isMimeSupported(AUDIO_MP4)).thenReturn(Boolean.TRUE);

        // when
        Boolean result = testObj.isHtml5Mp3Supported();

        // then
        assertTrue(result);
    }

    @Test
    public void shouldBeAbleToPlayMp3OnLocalAndroid() {
        // given
        when(audioWrapper.isAudioSupported()).thenReturn(Boolean.TRUE);
        when(userAgentUtil.isAndroidBrowser()).thenReturn(Boolean.TRUE);
        when(userAgentUtil.isLocal()).thenReturn(Boolean.TRUE);

        // when
        Boolean result = testObj.isHtml5Mp3Supported();

        // then
        assertTrue(result);
    }

    @Test
    public void shouldNotBeAbleToPlayMp3IfSoundNotSupported() {
        // given
        when(audioWrapper.isAudioSupported()).thenReturn(Boolean.FALSE);
        when(audioWrapper.isMimeSupported(AUDIO_MP4)).thenReturn(Boolean.TRUE);
        when(userAgentUtil.isAndroidBrowser()).thenReturn(Boolean.TRUE);
        when(userAgentUtil.isLocal()).thenReturn(Boolean.TRUE);

        // when
        boolean result = testObj.isHtml5Mp3Supported();

        // then
        assertFalse(result);
    }

    @Test
    public void shouldBeAbleToPlayIfSoundAndMimeIsSupported() {
        // given
        when(audioWrapper.isAudioSupported()).thenReturn(Boolean.TRUE);
        when(audioWrapper.isMimeSupported(AUDIO_OGG)).thenReturn(Boolean.TRUE);

        // when
        boolean result = testObj.isHtml5OggSupported();

        // then
        assertTrue(result);
    }

    @Test
    public void shouldNotBeAbleToPlayIfNotSoundAndMimeIsSupported() {
        // given
        when(audioWrapper.isAudioSupported()).thenReturn(Boolean.FALSE);
        when(audioWrapper.isMimeSupported(AUDIO_OGG)).thenReturn(Boolean.TRUE);

        // when
        boolean result = testObj.isHtml5OggSupported();

        // then
        assertFalse(result);
    }

    @Test
    public void shouldNotBeAbleToPlayIfSoundAndNotMimeIsSupported() {
        // given
        when(audioWrapper.isAudioSupported()).thenReturn(Boolean.TRUE);
        when(audioWrapper.isMimeSupported(AUDIO_OGG)).thenReturn(Boolean.FALSE);

        // when
        boolean result = testObj.isHtml5OggSupported();

        // then
        assertFalse(result);
    }

    @Test
    public void shouldNotBeAbleToPlayIfNotSoundAndNotMimeIsSupported() {
        // given
        when(audioWrapper.isAudioSupported()).thenReturn(Boolean.FALSE);
        when(audioWrapper.isMimeSupported(AUDIO_OGG)).thenReturn(Boolean.FALSE);

        // when
        boolean result = testObj.isHtml5OggSupported();

        // then
        assertFalse(result);
    }

    @Test
    public void shouldNotBeAbleToPlayIfMimeNotSupportedAndLocal() {
        // given
        when(audioWrapper.isAudioSupported()).thenReturn(Boolean.TRUE);
        when(audioWrapper.isMimeSupported(AUDIO_MP4)).thenReturn(Boolean.FALSE);
        when(userAgentUtil.isAndroidBrowser()).thenReturn(Boolean.FALSE);
        when(userAgentUtil.isLocal()).thenReturn(Boolean.TRUE);

        // when
        boolean result = testObj.isHtml5Mp3Supported();

        // then
        assertFalse(result);
    }

    @Test
    public void shouldNotBeAbleToPlayIfMimeNotSupportedAndNotLocal() {
        // given
        when(audioWrapper.isAudioSupported()).thenReturn(Boolean.TRUE);
        when(audioWrapper.isMimeSupported(AUDIO_MP4)).thenReturn(Boolean.FALSE);
        when(userAgentUtil.isAndroidBrowser()).thenReturn(Boolean.TRUE);
        when(userAgentUtil.isLocal()).thenReturn(Boolean.FALSE);

        // when
        boolean result = testObj.isHtml5Mp3Supported();

        // then
        assertFalse(result);
    }
}
