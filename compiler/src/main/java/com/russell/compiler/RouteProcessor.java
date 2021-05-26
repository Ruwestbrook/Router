package com.russell.compiler;

import com.google.auto.service.AutoService;
import com.russell.annotation.Route;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class  RouteProcessor extends AbstractProcessor {
    Filer filer;
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        System.out.println("init");
        filer=processingEnv.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        Set<? extends Element> set=roundEnv.getElementsAnnotatedWith(Route.class);
        GenerateJavaFile(set);
        for (Element e: set) {
           System.out.println( e.getSimpleName());
           TypeElement element= (TypeElement) e;
            Route route= e.getAnnotation(Route.class);
            route.path();
           System.out.println(element.getQualifiedName());
           String[] split=element.getQualifiedName().toString().split("\\.");
           String className=split[split.length-1];
           String packageName=processingEnv.getElementUtils().getPackageOf(element).toString();
        }


        return false;
    }



    private void GenerateJavaFile(Set<? extends Element> elements){
        StringBuilder importString=new StringBuilder();
        importString.append("package com.russell.router;");
        for (Element e: elements) {
            TypeElement element= (TypeElement) e;
            String className=processingEnv.getElementUtils().getBinaryName(element).toString();
            String packageName=processingEnv.getElementUtils().getPackageOf(element).toString();
            importString.append("import ").append(className).append(";\n");
        }

        importString.append("import android.app.Activity;\n");
        importString.append("import com.russell.library.RegisterRouter;\n");
        importString.append("import java.util.Map;\n");
        importString.append("\n");

        StringBuilder routerString=new StringBuilder();
        for (Element e: elements) {
            TypeElement element= (TypeElement) e;
            Route route= e.getAnnotation(Route.class);
            route.path();
            String className=processingEnv.getElementUtils().getBinaryName(element).toString();
            routerString.append("routers.put(\"").
                    append(route.path()).append("\",").append(className).append(".class);\n");
        }

        String file=importString.toString()+"public class AppRegister implements RegisterRouter {\n" +
                "    @Override\n" +
                "    public void load(Map<String, Class<? extends Activity>> routers) {\n" +
                routerString.toString()+
                "    }\n" +
                "}";

        JavaFileObject source = null;
        try {
            source = processingEnv.getFiler().createSourceFile(
                    "com.russell.router.AppRegister");
            Writer writer = source.openWriter();
            writer.write(file);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Route.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


}
