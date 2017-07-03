/**
 * 
 */
package client;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * @author kevin
 *
 */
@Root(name = "insitution", strict = false)
public class InstitutionList {
	@ElementList(name = "insitution", inline = true)
	private List<Institution> institutionIds;

	/**
	 * @return the institutionIds
	 */
	public List<Institution> getInstitutionIds() {
		return institutionIds;
	}

	/**
	 * @param institutionIds
	 *            the institutionIds to set
	 */
	public void setInstitutionIds(List<Institution> institutionIds) {
		this.institutionIds = institutionIds;
	}
}
