package eu.ydp.gwtutil.user.rebind.instantiator;

import java.io.PrintWriter;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import eu.ydp.gwtutil.client.instantiator.InstantiatorBinding;
import eu.ydp.gwtutil.client.instantiator.InstantiatorBindingPair;

public class InstantiatorGenerator extends Generator {

	@Override
	public String generate(TreeLogger logger, GeneratorContext context, String typeName) throws UnableToCompleteException {
		JClassType classType;

		try {
			classType = context.getTypeOracle().getType(typeName);
			SourceWriter src = getSourceWriter(classType, context, logger);
			src.commit(logger);
			return typeName + "Generated";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public SourceWriter getSourceWriter(JClassType classType, GeneratorContext context, TreeLogger logger) {
		String packageName = classType.getPackage().getName();
		String simpleName = classType.getSimpleSourceName() + "Generated";
		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
		composer.addImplementedInterface(classType.getName());

		PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);
		if (printWriter == null) {
			return null;
		}
		SourceWriter sw = composer.createSourceWriter(context, printWriter);

		String returnType = ((JParameterizedType) classType.getImplementedInterfaces()[0]).getTypeArgs()[0].getQualifiedSourceName();

		sw.println("public " + returnType + " instantiate(String name) {");

		InstantiatorBinding a = classType.getAnnotation(InstantiatorBinding.class);
		if (a != null) {
			InstantiatorBindingPair[] pairs = a.value();
			for (int i = 0; i < pairs.length; i++) {
				sw.println("if (\"" + pairs[i].name() + "\".equals(name))");
				sw.println("	return new " + pairs[i].clazz().getName() + "();");
			}
		}
		if (a.whenNotFound().equals(InstantiatorBinding.Null.class))
			sw.println("return null;");
		else
			sw.println("return new " + a.whenNotFound().getName() + "();");
		sw.println("}");

		return sw;
	}
}
