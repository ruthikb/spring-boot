package com.xworkz.ecomerce.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
@Aspect
public class LoggingAspect {
//Joint Point

    @Pointcut("execution(* com.xworkz.boot_modules.service.*.*(..))")
    public void allInService(){
        //marker method
    }
    @Before("allInService()")
    public void logBefore(JoinPoint joinPoint) {

        String kind = joinPoint.getKind();
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String longString = joinPoint.toLongString();
        String shortString = joinPoint.toShortString();
        String joinPointString = joinPoint.toString();
        Object joinPointThis = joinPoint.getThis();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();

        System.out.println(" [JoinPoint Details]");
        System.out.println("• Kind: " + kind);
        System.out.println("• Signature: " + signature);
        System.out.println("• Args: " + Arrays.toString(args));
        System.out.println("• Long String: " + longString);
        System.out.println("• Short String: " + shortString);
        System.out.println("• JoinPoint.toString(): " + joinPointString);
        System.out.println("• Target Object (getThis): " + joinPointThis);
        System.out.println("• Source Location: " + sourceLocation);
        System.out.println("• Static Part: " + staticPart);
    }

    @After("allInService()")
    public void logAfter(JoinPoint joinPoint) {
        String kind = joinPoint.getKind();
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String longString = joinPoint.toLongString();
        String shortString = joinPoint.toShortString();
        String joinPointString = joinPoint.toString();
        Object joinPointThis = joinPoint.getThis();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();

        System.out.println(" [JoinPoint Details]");
        System.out.println("• Kind: " + kind);
        System.out.println("• Signature: " + signature);
        System.out.println("• Args: " + Arrays.toString(args));
        System.out.println("• Long String: " + longString);
        System.out.println("• Short String: " + shortString);
        System.out.println("• JoinPoint.toString(): " + joinPointString);
        System.out.println("• Target Object (getThis): " + joinPointThis);
        System.out.println("• Source Location: " + sourceLocation);
        System.out.println("• Static Part: " + staticPart);
    }

    @AfterThrowing("allInService()")
    public void logAfterThrowing(JoinPoint joinPoint){
        String kind = joinPoint.getKind();
        Object[] args = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        String longString = joinPoint.toLongString();
        String shortString = joinPoint.toShortString();
        String joinPointString = joinPoint.toString();
        Object joinPointThis = joinPoint.getThis();
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();

        System.out.println(" [JoinPoint Details]");
        System.out.println("• Kind: " + kind);
        System.out.println("• Signature: " + signature);
        System.out.println("• Args: " + Arrays.toString(args));
        System.out.println("• Long String: " + longString);
        System.out.println("• Short String: " + shortString);
        System.out.println("• JoinPoint.toString(): " + joinPointString);
        System.out.println("• Target Object (getThis): " + joinPointThis);
        System.out.println("• Source Location: " + sourceLocation);
        System.out.println("• Static Part: " + staticPart);
    }
}
