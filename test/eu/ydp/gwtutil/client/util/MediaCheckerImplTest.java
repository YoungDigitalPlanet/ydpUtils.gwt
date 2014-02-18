package eu.ydp.gwtutil.client.util;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MediaCheckerImplTest {

	@InjectMocks
	private MediaCheckerImpl testObj;

	@Mock
	private AudioWrapper audioWrapper;

	@Mock
	private UserAgentUtil userAgentUtil;

	@Test
	public void shouldNotSupportMp3WhenMimeTypeNotSupported() {
		// given
		when(audioWrapper.isMimeSupported("audio/mp4")).thenReturn(Boolean.FALSE);

		// when
		Boolean result = testObj.isHtml5Mp3Supported();

		// then
		assertFalse(result);
	}

	@Test
	public void shouldSupportMp3WhenMimeTypeSupported() {
		// given
		when(audioWrapper.isAudioSupported()).thenReturn(Boolean.TRUE);
		when(audioWrapper.isMimeSupported("audio/mp4")).thenReturn(Boolean.TRUE);

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
		when(audioWrapper.isMimeSupported("audio/mp4")).thenReturn(Boolean.TRUE);
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
		when(audioWrapper.isMimeSupported("audio/ogg")).thenReturn(Boolean.TRUE);

		// when
		boolean result = testObj.isHtml5OggSuported();

		// then
		assertTrue(result);
	}

	@Test
	public void shouldNotBeAbleToPlayIfNotSoundAndMimeIsSupported() {
		// given
		when(audioWrapper.isAudioSupported()).thenReturn(Boolean.FALSE);
		when(audioWrapper.isMimeSupported("audio/ogg")).thenReturn(Boolean.TRUE);

		// when
		boolean result = testObj.isHtml5OggSuported();

		// then
		assertFalse(result);
	}

	@Test
	public void shouldNotBeAbleToPlayIfSoundAndNotMimeIsSupported() {
		// given
		when(audioWrapper.isAudioSupported()).thenReturn(Boolean.TRUE);
		when(audioWrapper.isMimeSupported("audio/ogg")).thenReturn(Boolean.FALSE);

		// when
		boolean result = testObj.isHtml5OggSuported();

		// then
		assertFalse(result);
	}

	@Test
	public void shouldNotBeAbleToPlayIfNotSoundAndNotMimeIsSupported() {
		// given
		when(audioWrapper.isAudioSupported()).thenReturn(Boolean.FALSE);
		when(audioWrapper.isMimeSupported("audio/ogg")).thenReturn(Boolean.FALSE);

		// when
		boolean result = testObj.isHtml5OggSuported();

		// then
		assertFalse(result);
	}
}
