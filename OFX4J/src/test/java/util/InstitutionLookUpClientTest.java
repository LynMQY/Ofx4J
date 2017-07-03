/**
 * 
 */
package util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author kevin
 *
 */
public class InstitutionLookUpClientTest {

	/**
	 * Test method for {@link util.InstitutionLookUpClient#institutionLookup(java.lang.String)}.
	 */
	@Test
	public void testInstitutionLookup() {
		assertTrue(InstitutionLookUpClient.institutionLookup("chase").size()!= 0);
	}
	
	@Test
	public void testinstitutionLookupID(){
		assertNotNull(InstitutionLookUpClient.institutionLookupID("444"));
	}

}
