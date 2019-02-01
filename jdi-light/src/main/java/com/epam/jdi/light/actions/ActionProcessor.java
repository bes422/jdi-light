package com.epam.jdi.light.actions;

/**
 * Created by Roman Iovlev on 14.02.2018
 * Email: roman.iovlev.jdi@gmail.com; Skype: roman.iovlev
 */

import com.epam.jdi.light.common.JDIAction;
import com.epam.jdi.tools.func.JFunc1;
import com.epam.jdi.tools.map.MapArray;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.List;

import static com.epam.jdi.light.actions.ActionHelper.*;
import static com.epam.jdi.light.common.Exceptions.exception;
import static com.epam.jdi.light.elements.base.OutputTemplates.FAILED_ACTION_TEMPLATE;
import static com.epam.jdi.light.settings.TimeoutSettings.TIMEOUT;
import static com.epam.jdi.light.settings.WebSettings.logger;
import static com.epam.jdi.tools.StringUtils.msgFormat;
import static com.epam.jdi.tools.Timer.nowTime;
import static com.epam.jdi.tools.map.MapArray.map;
import static com.epam.jdi.tools.pairs.Pair.$;
import static java.lang.System.currentTimeMillis;

@SuppressWarnings("unused")
@Aspect
public class ActionProcessor {

    @Pointcut("execution(* *(..)) && @annotation(com.epam.jdi.light.common.JDIAction)")
    protected void jdiPointcut() { }
    @Pointcut("execution(* *(..)) && @annotation(io.qameta.allure.Step)")
    protected void stepPointcut() { }

    @Around("jdiPointcut()")
    public Object jdiAround(ProceedingJoinPoint jp) {
        try {
            BEFORE_JDI_ACTION.execute(jp);
            Object result = stableAction(jp);
            return AFTER_JDI_ACTION.execute(jp, result);
        } catch (Throwable ex) {
            Object element = jp.getThis() != null ? jp.getThis() : new Object();
            throw exception("["+nowTime("mm:ss.S")+"] " + ACTION_FAILED.execute(element, ex.getMessage()));
        }
    }

    private static Object stableAction(ProceedingJoinPoint jp) {
        long start = currentTimeMillis();
        String exception = "";
        logger.logOff();
        int timeout = TIMEOUT.get();
        do { try {
            Object result =  jp.proceed();
            boolean c = condition(jp);
            if (!condition(jp)) continue;
            logger.logOn();
            return result;
        } catch (Throwable ex) {
            try {
                exception = ex.getMessage();
                Thread.sleep(200);
            } catch (Exception ignore) {  } }
        } while (currentTimeMillis() - start < timeout*1000);
        logger.logOn();
        throw exception(getFailedMessage(jp, exception));
    }
    private static String getFailedMessage(ProceedingJoinPoint jp, String exception) {
        MethodSignature method = getMethod(jp);
        try {
            String result = msgFormat(FAILED_ACTION_TEMPLATE, map(
                $("exception", exception),
                $("timeout", TIMEOUT.get()),
                $("action", method.getMethod().getName())
            ));
            return fillTemplate(result, jp, method);
        } catch (Exception ex) {
            throw new RuntimeException("Surround method issue: " +
                    "Can't get failed message: " + ex.getMessage());
        }
    }

    private static String getConditionName(ProceedingJoinPoint jp) {
        JDIAction ja = ((MethodSignature)jp.getSignature()).getMethod().getAnnotation(JDIAction.class);
        return ja != null ? ja.condition() : "";
    }
    public static MapArray<String, JFunc1<Object, Boolean>> CONDITIONS = map(
        $("", result -> true),
        $("true", result -> result instanceof Boolean && (Boolean)result),
        $("false", result -> result instanceof Boolean && !(Boolean)result),
        $("not empty", result -> result instanceof List && ((List)result).size() > 0),
        $("empty", result -> result instanceof List && ((List)result).size() == 0)
    );
    private static boolean condition(ProceedingJoinPoint jp) {
        String conditionName = getConditionName(jp);
        return CONDITIONS.has(conditionName)
            ? CONDITIONS.get(conditionName).execute(jp)
            : true;
    }

    @Around("stepPointcut()")
    public Object stepAround(ProceedingJoinPoint jp) throws Throwable {
        try {
            BEFORE_STEP_ACTION.execute(jp);
            Object result = jp.proceed();
            return AFTER_STEP_ACTION.execute(jp, result);
        } catch (Throwable ex) {
            Object element = jp.getThis() != null ? jp.getThis() : new Object();
            throw exception(ACTION_FAILED.execute(element, ex.getMessage()));
        }
    }
}
