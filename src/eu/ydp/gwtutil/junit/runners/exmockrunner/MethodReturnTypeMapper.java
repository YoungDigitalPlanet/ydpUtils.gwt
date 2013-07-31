package eu.ydp.gwtutil.junit.runners.exmockrunner;

import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.analysis.Type;

class MethodReturnTypeMapper {
	public PrimitiveReturnType getPrimitiveReturnType(CtMethod method) throws NotFoundException{
		if (isBooleanReturnType(method)) {
			return PrimitiveReturnType.BOOLEAN;
		}
		if (isCharReturnType(method)) {
			return PrimitiveReturnType.CHAR;
		}
		if (isNumberReturnType(method)) {
			return PrimitiveReturnType.INTEGER;
		}
		if (isRealNumberReturnType(method)) {
			return PrimitiveReturnType.FLOAT;
		}
		return PrimitiveReturnType.UNKNOWN;
	}

	private boolean isRealNumberReturnType(CtMethod method) throws NotFoundException {
		Type returnType = Type.get(method.getReturnType());
		return returnType.equals(Type.FLOAT)
			|| returnType.equals(Type.DOUBLE);
	}

	private boolean isNumberReturnType(CtMethod method) throws NotFoundException {
		Type returnType = Type.get(method.getReturnType());
		return returnType.equals(Type.INTEGER)
			|| returnType.equals(Type.SHORT)
			|| returnType.equals(Type.BYTE)
			|| returnType.equals(Type.LONG);
	}

	private boolean isCharReturnType(CtMethod method) throws NotFoundException {
		Type returnType = Type.get(method.getReturnType());
		return returnType.equals(Type.CHAR);
	}

	private boolean isBooleanReturnType(CtMethod method) throws NotFoundException {
		Type returnType = Type.get(method.getReturnType());
		return returnType.equals(Type.BOOLEAN);
	}
}
