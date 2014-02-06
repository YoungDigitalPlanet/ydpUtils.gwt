package eu.ydp.gwtutil.client.resource;

import eu.ydp.gwtutil.client.PathUtil;

public class ResourceModel<T> {

	private String pathBase;
	private T resource;

	public ResourceModel() {
	}

	public ResourceModel(T resource, String pathBase) {
		setData(resource, pathBase);
	}

	public final void setData(T resource, String pathBase) {
		this.pathBase = pathBase;
		this.resource = resource;
	}

	public String getPathBase() {
		return pathBase;
	}

	public T getResource() {
		return resource;
	}

	protected String resolvePath(String url) {
		return PathUtil.resolvePath(url, getPathBase());
	}
}
