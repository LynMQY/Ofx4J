package client;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

//github.com/KevinSJ/Ofx4J.git
import util.ResultInstitution;

/**
 * @author kevin
 *
 */
@Root
public class InstitutionList {

	@ElementList(name = "insitutionlist", inline = true)
	private List<ResultInstitution> institutionIds;

	/**
	 * @return the institutionIds
	 */
	public List<ResultInstitution> getInstitutionIds() {
		return institutionIds;
	}

	/**
	 * @param institutionIds
	 *            the institutionIds to set
	 */
	public void setInstitutionIds(List<ResultInstitution> institutionIds) {
		this.institutionIds = institutionIds;
	}


}