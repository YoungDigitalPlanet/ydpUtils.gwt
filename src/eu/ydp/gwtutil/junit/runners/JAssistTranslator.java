package eu.ydp.gwtutil.junit.runners;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.analysis.Type;

public class JAssistTranslator  {

	private final Collection<String> classNamesToModify = new HashSet<String>();

	public JAssistTranslator(Collection<Class<?>> clazzToModify) {
		for (Class<?> clazz : clazzToModify) {
			classNamesToModify.add(clazz.getName());
		}
	}

	public CtClass prepareClass(ClassPool pool, String clazz) throws NotFoundException, CannotCompileException {
		if (classNamesToModify.contains(clazz)) {
			CtClass ctClass = pool.get(clazz);
			removeFinal(ctClass);
			removeNative(ctClass);
			return ctClass;
		}else{
			return pool.get(clazz);
		}

	}

	private void removeFinal(CtClass clazz) {
		Collection<CtMethod> methods = getMethods(clazz, Modifier.FINAL);
		for (CtMethod method : methods) {
			method.setModifiers(Modifier.clear(method.getModifiers(), Modifier.FINAL));
		}
	}

	private void removeNative(CtClass clazz) throws NotFoundException, CannotCompileException {
		Collection<CtMethod> methods = getMethods(clazz, Modifier.NATIVE);
		for (CtMethod method : methods) {
			method.setModifiers(Modifier.clear(method.getModifiers(), Modifier.NATIVE));
			if (method.getReturnType() != null) {
				if(method.getReturnType().isPrimitive()){
					if(Type.get(method.getReturnType()).equals(Type.BOOLEAN)){
						method.setBody("{return false ;}");
						continue;
					}
					if(Type.get(method.getReturnType()).equals(Type.CHAR)){
						method.setBody("{return 'a';}");
						continue;
					}
					if(Type.get(method.getReturnType()).equals(Type.INTEGER)
						|| Type.get(method.getReturnType()).equals(Type.SHORT)
						|| Type.get(method.getReturnType()).equals(Type.BYTE)
						|| Type.get(method.getReturnType()).equals(Type.LONG)){
						method.setBody("{return 0 ;}");
						continue;
					}
					if(Type.get(method.getReturnType()).equals(Type.FLOAT)
							|| Type.get(method.getReturnType()).equals(Type.DOUBLE)){
							method.setBody("{ return 0.0 ;}");
							continue;
						}
					method.setBody("{return null;}");
				}else{
					method.setBody("{return null;}");
				}
			} else {
				method.setBody("{ return ;}");
			}

		}
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
