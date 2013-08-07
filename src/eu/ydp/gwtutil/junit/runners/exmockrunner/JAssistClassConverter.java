package eu.ydp.gwtutil.junit.runners.exmockrunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;

class JAssistClassConverter {

	private final ToModifyClassFinder toModifyClassFinder;
	private final MethodReturnTypeMapper methodReturnTypeMapper = new MethodReturnTypeMapper();

	public JAssistClassConverter(ToModifyClassFinder toModifyClassFinder) {
		this.toModifyClassFinder = toModifyClassFinder;
	}

	public CtClass prepareClass(ClassPool pool, String clazz) throws NotFoundException, CannotCompileException {
		CtClass ctClass = pool.get(clazz);
		if (toModifyClassFinder.isModifyNeeded(clazz)) {
			defrostIfNeeded(ctClass);
			removeFinal(ctClass);
			removeNative(ctClass);
		}
		return ctClass;
	}

	private void defrostIfNeeded(CtClass ctClass) {
		if (ctClass.isFrozen()) {
			ctClass.defrost();
		}
	}

	private void removeFinal(CtClass clazz) {
		Collection<CtMethod> methods = getMethods(clazz, Modifier.FINAL);
		for (CtMethod method : methods) {
			clearFinalModifire(method);
		}
	}

	private void clearFinalModifire(CtMethod method) {
		method.setModifiers(Modifier.clear(method.getModifiers(), Modifier.FINAL));
	}

	private void removeNative(CtClass clazz) throws NotFoundException, CannotCompileException {
		Collection<CtMethod> methods = getMethods(clazz, Modifier.NATIVE);
		for (CtMethod method : methods) {
			clearNativeModifire(method);
			String methodBody = getNewMethodBody(method);
			method.setBody(methodBody);
		}
	}

	private String getNewMethodBody(CtMethod method) throws NotFoundException, CannotCompileException {
		String methodBody = null;
		if (isVoidMethod(method)) {
			methodBody = getBodyForVoidMethod();
		} else {
			methodBody = getBodyForMethodWithReturnType(method);
		}
		return methodBody;
	}

	private void clearNativeModifire(CtMethod method) {
		method.setModifiers(Modifier.clear(method.getModifiers(), Modifier.NATIVE));
	}

	private String getBodyForVoidMethod() {
		return "{ return ;}";
	}

	private String getBodyForMethodWithReturnType(CtMethod method) throws NotFoundException, CannotCompileException {
		switch (methodReturnTypeMapper.getPrimitiveReturnType(method)) {
		case BOOLEAN:
			return "{return false ;}";
		case CHAR:
			return "{return 'a';}";
		case INTEGER:
			return "{return 0 ;}";
		case FLOAT:
			return "{ return 0.0 ;}";
		default:
			return "{return null;}";
		}
	}

	private boolean isVoidMethod(CtMethod method) throws NotFoundException {
		return method.getReturnType() == null;
	}

	private Collection<CtMethod> getMethods(CtClass ctClass, int modifier) {
		List<CtMethod> returnMethods = new ArrayList<CtMethod>();
		CtMethod[] methods = ctClass.getMethods();
		for (CtMethod method : methods) {
			if ((method.getModifiers() & modifier) != 0) {
				returnMethods.add(method);
			}
		}
		return returnMethods;
	}

}
