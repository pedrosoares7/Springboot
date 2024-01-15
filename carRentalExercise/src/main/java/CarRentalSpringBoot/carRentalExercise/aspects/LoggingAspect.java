package CarRentalSpringBoot.carRentalExercise.aspects;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Before("execution( * CarRentalSpringBoot.carRentalExercise.service.ClientService.addNewClient(..))")
  public void logBeforeServiceToCreate(JoinPoint joinPoint) {
    logger.info("Before " + joinPoint.getSignature().getName() + " method call");
  }

  @AfterReturning(pointcut = "execution(* CarRentalSpringBoot.carRentalExercise.service.*.*(..))", returning = "result")
  public void logAfterService(JoinPoint joinPoint, Object result) {
      logger.info("After " + joinPoint.getSignature().getName() + " method call");
      logger.info("Response " + result);
  }

   @AfterThrowing(pointcut = "execution(* CarRentalSpringBoot.carRentalExercise.service.*.*(..))", throwing = "exception")
public void logAfterThrowing(JoinPoint joinPoint, Throwable exception){
      logger.error("Exception in " + joinPoint.getSignature().getName() + " method call");
      logger.error("Response: " + exception);
    }
@Around("execution(* CarRentalSpringBoot.carRentalExercise.service.ClientService.getClients(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
      long startTime = System.currentTimeMillis();
      logger.info("Before " + joinPoint.getSignature().getName() + " method call");
      Object result = joinPoint.proceed();
      long endTime = System.currentTimeMillis();
      logger.info("After " + joinPoint.getSignature(). getName() + " method call");
      logger.info("Execution time of " + joinPoint.getSignature().getName() + "method call " + (endTime - startTime) + " milliseconds");
      return result;
}

}