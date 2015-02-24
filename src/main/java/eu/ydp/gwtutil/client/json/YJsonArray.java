package eu.ydp.gwtutil.client.json;

public interface YJsonArray extends YJsonValue {

	YJsonValue get(int index);

	void set(int index, YJsonValue value);

	int size();
}
