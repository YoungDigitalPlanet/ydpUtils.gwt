package eu.ydp.gwtutil.user.rebind.constants;

import java.io.PrintWriter;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import eu.ydp.gwtutil.client.constants.Output;
import eu.ydp.gwtutil.client.constants.OutputType;
import eu.ydp.gwtutil.client.constants.Prefix;
import eu.ydp.gwtutil.client.constants.Separator;
import eu.ydp.gwtutil.client.constants.Suffix;

public class StringConstantsGenerator extends Generator {

	@Override
	public String generate(TreeLogger logger, GeneratorContext context,String typeName) throws UnableToCompleteException {
		JClassType classType;
		
		try {
			classType = context.getTypeOracle().getType(typeName);
			SourceWriter src = getSourceWriter(classType, context, logger);
			if (src != null){
				src.commit(logger);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return typeName + "Generated";
	}

	public SourceWriter getSourceWriter(JClassType classType,GeneratorContext context, TreeLogger logger) {
		String packageName = classType.getPackage().getName();
		String simpleName = classType.getSimpleSourceName() + "Generated";
		ClassSourceFileComposerFactory composer = new ClassSourceFileComposerFactory(packageName, simpleName);
		composer.addImplementedInterface(classType.getName());
		
		PrintWriter printWriter = context.tryCreate(logger, packageName, simpleName);
		if (printWriter == null){
			return null;
		}
		SourceWriter sw = composer.createSourceWriter(context, printWriter);
		String prefix = findPrefix(classType);
		String suffix = findSuffix(classType);
		String separator = findSeparator(classType);
		OutputType outputType = findOutputType(classType);

		sw.println("private String value;");
		sw.println("private void setValue(String value) {");
		sw.println("	this.value = value;");
		sw.println("}");
		sw.println("private " + simpleName + " replicate(String value) {");
		sw.println("	" + simpleName + " obj = new " + simpleName + "();");
		sw.println("	if (this.value == null)");
		sw.println("		obj.setValue(value);");
		sw.println("	else");
		sw.println("		obj.setValue(this.value + \"" + separator + "\" + value);");
		sw.println("	return obj;");
		sw.println("}");
		
		for (int m = 0 ; m < classType.getMethods().length ; m ++){
			printMethod(simpleName, classType.getMethods()[m], separator, sw, outputType);
		}

		sw.println("public String toString() {");
		sw.println("	return \"" + prefix + "\" + value + \"" + suffix + "\";");
		sw.println("}");
		
		return sw;
	}

	private void printMethod(String simpleName, JMethod method, String separator, SourceWriter sw, OutputType outputType){
		sw.println("public " + simpleName + " " + method.getName() + "() {");
		String value = null;
		switch (outputType) {
		case LOWER_CASE_SEPARATED:
			value = camelCaseToDash(method.getName());
			break;
		case UPPER_CASE:
			value = method.getName().toUpperCase();
			break;
		default:
			break;
		}
		sw.println("	return replicate(\"" + value + "\");");
		sw.println("}");
	}
	
	private String camelCaseToDash(String cc){
		StringBuilder output = new StringBuilder();
		for (int i = 0 ; i < cc.length() ; i ++ ){
			Character ch = cc.charAt(i);
			if (Character.isUpperCase(ch)){
				output.append("-");
			}
			output.append(Character.toLowerCase(ch));
		}
		 return output.toString();
	}

	private OutputType findOutputType(JClassType classType) {
		Output ann = classType.getAnnotation(Output.class);
		if (ann != null)
			return ann.value();
		return OutputType.LOWER_CASE_SEPARATED;
	}
	
	private String findSeparator(JClassType classType) {
		Separator ann = classType.getAnnotation(Separator.class);
		if (ann != null)
			return ann.value();
		return "-";
	}
	
	private String findPrefix(JClassType classType){
		Prefix ann = classType.getAnnotation(Prefix.class);
		if (ann != null)
			return ann.value();
		return "";
	}
	
	private String findSuffix(JClassType classType){
		Suffix ann = classType.getAnnotation(Suffix.class);
		if (ann != null)
			return ann.value();
		return "";
	}

}
