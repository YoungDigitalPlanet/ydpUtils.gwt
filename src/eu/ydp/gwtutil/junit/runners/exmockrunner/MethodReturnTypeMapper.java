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
		return Type.get(method.getReturnType()).equals(Type.FLOAT)
			|| Type.get(method.getReturnType()).equals(Type.DOUBLE);
	}

	private boolean isNumberReturnType(CtMethod method) throws NotFoundException {
		return Type.get(method.getReturnType()).equals(Type.INTEGER)
			|| Type.get(method.getReturnType()).equals(Type.SHORT)
			|| Type.get(method.getReturnType()).equals(Type.BYTE)
			|| Type.get(method.getReturnType()).equals(Type.LONG);
	}

	private boolean isCharReturnType(CtMethod method) throws NotFoundException {
		return Type.get(method.getReturnType()).equals(Type.CHAR);
	}

	private boolean isBooleanReturnType(CtMethod method) throws NotFoundException {
		return Type.get(method.getReturnType()).equals(Type.BOOLEAN);
	}
}
