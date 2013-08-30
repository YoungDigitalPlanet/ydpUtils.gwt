package eu.ydp.gwtutil.client.gin.scopes.module;

import com.google.gwt.user.client.resource.ResourceModel;
import com.google.gwt.xml.client.Element;

public class ModuleCreationContext {

	private final ResourceModel<Element> resourceModel;

	public ModuleCreationContext(Element xmlElement) {
		this.resourceModel = new ResourceModel<Element>(xmlElement, "");
	}
	
	public ModuleCreationContext(ResourceModel<Element> resourceModel) {
		this.resourceModel = resourceModel;
	}

	public Element getXmlElement() {
		return resourceModel.getResource();
	}
	
}
