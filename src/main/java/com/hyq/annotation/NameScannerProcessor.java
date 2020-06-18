package com.hyq.annotation;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;
/**
 * @author Yuqiu.He
 * @date 2020-04-17
 */
@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class NameScannerProcessor extends AbstractProcessor {

    @Override
    public void init(ProcessingEnvironment processingEnv){
        super.init(processingEnv);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv){
        if(!roundEnv.processingOver()){
            for(Element element : roundEnv.getElementsAnnotatedWith(NameScanner.class)){
                String name = element.getSimpleName().toString();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "element name: " + name);
            }
        }
        return false;
    }
}