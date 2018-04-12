//package com.irenailieva.nutricounter.controllers.exception;
//
//import com.irenailieva.nutricounter.controllers.base.BaseController;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class GlobalExceptionController extends BaseController {
//
//    private static final int UNAUTHORIZED_STATUS_CODE = HttpStatus.UNAUTHORIZED.value();
//    private static final String UNAUTHORIZED_MESSAGE = "Oops... Looks like you don't have permission to view this resource.";
//
//    private static final int DEFAULT_ERROR_STATUS_CODE = HttpStatus.INTERNAL_SERVER_ERROR.value();
//    private static final String DEFAULT_ERROR_MESSAGE = "Something went wrong.";
//
//    @ExceptionHandler(Exception.class)
//    public ModelAndView handleException(Exception e) {
//
//        int errorCodeValue = e.getClass().isAnnotationPresent(ResponseStatus.class)
//                ? e.getClass().getAnnotation(ResponseStatus.class).code().value()
//                : DEFAULT_ERROR_STATUS_CODE;
//
//        String errorMessage = e.getClass().isAnnotationPresent(ResponseStatus.class)
//                ? e.getClass().getAnnotation(ResponseStatus.class).reason()
//                : DEFAULT_ERROR_MESSAGE;
//
//        if (e.getClass().equals(AccessDeniedException.class)) {
//            errorCodeValue = UNAUTHORIZED_STATUS_CODE;
//            errorMessage = UNAUTHORIZED_MESSAGE;
//        }
//
//        return super.view("error-template")
//                .addObject("errorCode", errorCodeValue)
//                .addObject("errorMessage", errorMessage);
//    }
//}
