package net.toxbank.client.resource;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class StudyTest {
	
	private final static String TEST_SERVER = "http://demo.toxbank.net/";

	@Test
	public void testConstructor() {
		Study clazz = new Study();
		Assert.assertNotNull(clazz);
	}

	@Test
	public void testRetrieveMetadata() throws MalformedURLException {
		Study study = new Study(new URL(TEST_SERVER + "protocol/1"));
		Assert.assertNotNull(study);
		Assert.assertEquals(
			TEST_SERVER + "organization/1",
			study.getOwner()
		);
	}

	@Test
	public void testList() {
		List<URL> studies = Study.list(TEST_SERVER);
		Assert.assertNotNull(studies);
		Assert.assertNotSame(0, studies.size());
	}

	@Test
	public void testUpload() {
		Study study = new Study();
		URL url = study.upload(TEST_SERVER);
		Assert.assertNotNull(url);
		Assert.assertTrue(url.toExternalForm().startsWith(TEST_SERVER));
	}

	@Test
	public void testListVersions() throws MalformedURLException {
		Study study = new Study(new URL(TEST_SERVER + "protocol/1"));
		List<URL> versions = study.listVersions();
		Assert.assertNotNull(versions);
		Assert.assertNotSame(0, versions.size());
	}
	
	@Test
	public void testGetVersions() throws MalformedURLException {
		Study study = new Study(new URL(TEST_SERVER + "protocol/1"));
		List<Study> versions = study.getVersions();
		Assert.assertNotNull(versions);
		Assert.assertNotSame(0, versions.size());
		Assert.assertTrue(versions.contains(study));
	}

	@Test
	public void testGetSetKeywords() {
		Study study = new Study();
		Assert.assertEquals(0, study.getKeywords().size());
		study.addKeyword("foo");
		Assert.assertEquals(1, study.getKeywords().size());
		Assert.assertTrue(study.getKeywords().contains("foo"));
		study.removeKeyword("foo");
		Assert.assertEquals(0, study.getKeywords().size());
		Assert.assertFalse(study.getKeywords().contains("foo"));
	}

	@Test
	public void testGetSetAbstract() {
		Study study = new Study();
		Assert.assertNull(study.getAbstract());
		study.setAbstract("This is an abstract");
		Assert.assertNotNull(study.getAbstract());
		Assert.assertEquals(19, study.getAbstract().length());
	}

	@Test
	public void testGetSetVersionInfo() {
		Study study = new Study();
		Assert.assertNull(study.getVersionInfo());
		study.setVersionInfo("1");
		Assert.assertNotNull(study.getVersionInfo());
		Assert.assertEquals("1", study.getVersionInfo());
	}
}
