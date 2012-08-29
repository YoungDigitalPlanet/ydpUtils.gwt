package eu.ydp.gwtutil.client.xml;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Assert;
import org.junit.Test;

import eu.ydp.gwtutil.client.PathUtil;

public class PathUtilTest {

	@Test
	public void testResolvePath(){
		String fullPath = "http://www.ydp.eu/dir/x.html";
		String absPath = "/dir/x.html";
		String relPath = "dir/x.html";
		String base = "http://www.ydp.eu/";
		Assert.assertEquals(fullPath, PathUtil.resolvePath(fullPath, null));
		Assert.assertEquals(fullPath, PathUtil.resolvePath(fullPath, base));
		Assert.assertEquals(absPath, PathUtil.resolvePath(absPath, base));
		Assert.assertEquals(fullPath, PathUtil.resolvePath(relPath, base));
				
	}
	
	@Test
	public void testRetrieveBasePath(){		
		assertThat(PathUtil.retrieveBasePath("http://www.ydp.eu/dir/x.html"), equalTo("http://www.ydp.eu/dir/"));
		assertThat(PathUtil.retrieveBasePath("http://www.ydp.eu/dir/x.html?x=/"), equalTo("http://www.ydp.eu/dir/"));
		assertThat(PathUtil.retrieveBasePath("http://www.ydp.eu/dir\\x.html?x=/"), equalTo("http://www.ydp.eu/dir\\"));
		assertThat(PathUtil.retrieveBasePath("http://www.ydp.eu/dir/x.html?x=\\&y=/"), equalTo("http://www.ydp.eu/dir/"));
		assertThat(PathUtil.retrieveBasePath("c:\\dir\\file.ext"), equalTo("c:\\dir\\"));
		assertThat(PathUtil.retrieveBasePath("c:\\dir/file.ext"), equalTo("c:\\dir/"));
		assertThat(PathUtil.retrieveBasePath("c:/dir/file.ext"), equalTo("c:/dir/"));
	}
}

