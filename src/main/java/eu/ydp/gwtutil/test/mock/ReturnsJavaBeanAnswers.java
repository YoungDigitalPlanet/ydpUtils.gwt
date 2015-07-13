package eu.ydp.gwtutil.test.mock;

import org.mockito.internal.stubbing.defaultanswers.ReturnsMoreEmptyValues;
import org.mockito.invocation.InvocationOnMock;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Simulates Java Bean object answers for methods defined in the mocked class.
 * <p/>
 * Usage: SomeClass someClassMock = mock(SomeClass.class, new JavaBeanMockAnswers());
 *
 * @author rrybacki
 */
public class ReturnsJavaBeanAnswers extends ReturnsMoreEmptyValues {

    private static final long serialVersionUID = -3699260178969600384L;

    private final static Map<Object, Map<String, Object>> mockToMethodMap = new WeakHashMap<Object, Map<String, Object>>();

    @Override
    public Object answer(final InvocationOnMock invocation) throws Throwable {
        final Object target = invocation.getMock();
        final Method method = invocation.getMethod();

        Map<String, Object> methodToValueMap = null;
        synchronized (mockToMethodMap) {
            methodToValueMap = mockToMethodMap.get(target);
            if (methodToValueMap == null) {
                methodToValueMap = new HashMap<String, Object>();
                mockToMethodMap.put(target, methodToValueMap);
            }
        }

        if (method.getName().startsWith("set") && (method.getParameterTypes().length == 1)) {
            methodToValueMap.put(method.getName().substring(3), invocation.getArguments()[0]);
            return null;
        } else if (method.getName().startsWith("is") && (method.getParameterTypes().length == 0)) {
            Object retval = methodToValueMap.get(method.getName().substring(2));
            if (retval != null) {
                return retval;
            }
        } else if (method.getName().startsWith("get") && (method.getParameterTypes().length == 0)) {
            Object retval = methodToValueMap.get(method.getName().substring(3));
            if (retval != null) {
                return retval;
            }
        }

        return super.answer(invocation);
    }
}
