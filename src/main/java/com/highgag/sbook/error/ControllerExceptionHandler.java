package com.highgag.sbook.error;

import com.highgag.sbook.common.dto.GeneralResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("handleHttpRequestMethodNotSupportedException", e);

        final ErrorResponse response
                = ErrorResponse
                .create()
                .status(HttpStatus.METHOD_NOT_ALLOWED.value())
                .message(e.getMessage());

        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //@Valid 검증 실패 시 Catch
    @ExceptionHandler(InvalidParameterException.class)
    protected ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException e) {
        logger.error("handleInvalidParameterException", e);

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response
                = ErrorResponse
                .create()
                .status(errorCode.getStatus())
                .message(e.toString())
                .errors(e.getErrors());

        return new ResponseEntity<>(response, HttpStatus.resolve(errorCode.getStatus()));
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException(NoSuchElementException e){
        logger.error("handleNoSuchElementException", e);

        ErrorCode errorCode = ErrorCode.NOT_FOUND;
        GeneralResponse<Object> response = new GeneralResponse<>();
        response.setData("404", errorCode.getMessage());
        return new ResponseEntity(response, HttpStatus.resolve(errorCode.getStatus()));
    }

    //중복된 이메일의 사용자가 존재할 경우
    @ExceptionHandler(DuplicatedUserException.class)
    protected ResponseEntity<ErrorResponse> handleDuplicatedUserException(DuplicatedUserException e) {
        logger.error("handleDuplicatedUserException", e);

        ErrorCode errorCode = e.getErrorCode();

        GeneralResponse<Object> response = new GeneralResponse<>();
        response.setData("409", "중복된 회원이 존재합니다");
        return new ResponseEntity(response, HttpStatus.resolve(errorCode.getStatus()));
    }

    @ExceptionHandler(ForbiddenException.class)
    protected ResponseEntity<ErrorResponse> handleForbiddenException(ForbiddenException e){
        logger.error("forbidden exception", e);

        ErrorCode errorCode = e.getErrorCode();
        GeneralResponse<Object> response = new GeneralResponse<>();
        response.setData("403", "권한이 없습니다");
        return new ResponseEntity(response, HttpStatus.resolve(errorCode.getStatus()));
    }

    //CustomException을 상속받은 클래스가 예외를 발생 시킬 시, Catch하여 ErrorResponse를 반환
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        logger.error("handleAllException", e);

        ErrorCode errorCode = e.getErrorCode();

        ErrorResponse response
                = ErrorResponse
                .create()
                .status(errorCode.getStatus())
                .message(e.toString());

        return new ResponseEntity<>(response, HttpStatus.resolve(errorCode.getStatus()));
    }

    //모든 예외를 핸들링하여 ErrorResponse 형식으로 반환
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        logger.error("handleException", e);

        GeneralResponse<Object> response = new GeneralResponse<>();
        response.setData("500", "INTERNAL SERVER ERROR");

        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
