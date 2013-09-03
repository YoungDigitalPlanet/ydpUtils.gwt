package eu.ydp.gwtutil.client.gin.scopes.module;

import com.google.gwt.xml.client.Element;

import eu.ydp.gwtutil.client.resource.ResourceModel;

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
