package com.codecon.backend.shared;

import com.codecon.backend.shared.exception.CustomException;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(StatusRuntimeException.class)
    public ResponseEntity<String> handleCustomHttpException(StatusRuntimeException statusRuntimeException) {

        String errorMessage = statusRuntimeException.getMessage();
        Status status = statusRuntimeException.getStatus();
        HttpStatus httpStatus = mapGrpcStatusToHttpException(status);
        return ResponseEntity.status(httpStatus)
                .body(errorMessage);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleException(CustomException customException) {

        HttpStatus httpStatus = customException.getHTTPStatus();
        String errorMessage = customException.getMessage();
        return ResponseEntity
                .status(httpStatus)
                .body(errorMessage);
    }


    private HttpStatus mapGrpcStatusToHttpException(Status grpcStatus) {
        Status.Code grpcCode = grpcStatus.getCode();
        return switch (grpcCode) {
            case OK -> HttpStatus.OK; // 200
            case CANCELLED -> HttpStatus.BAD_REQUEST; // 400 (или 499, если клиент отменил)
            case UNKNOWN, INTERNAL, DATA_LOSS -> HttpStatus.INTERNAL_SERVER_ERROR; // 500
            case INVALID_ARGUMENT, OUT_OF_RANGE -> HttpStatus.BAD_REQUEST; // 400
            case DEADLINE_EXCEEDED -> HttpStatus.GATEWAY_TIMEOUT; // 504
            case NOT_FOUND -> HttpStatus.NOT_FOUND; // 404
            case ALREADY_EXISTS, ABORTED -> HttpStatus.CONFLICT; // 409
            case PERMISSION_DENIED -> HttpStatus.FORBIDDEN; // 403
            case UNAUTHENTICATED -> HttpStatus.UNAUTHORIZED; // 401
            case RESOURCE_EXHAUSTED -> HttpStatus.TOO_MANY_REQUESTS; // 429
            case FAILED_PRECONDITION -> HttpStatus.PRECONDITION_FAILED; // 412
            case UNIMPLEMENTED -> HttpStatus.NOT_IMPLEMENTED; // 501
            case UNAVAILABLE -> HttpStatus.SERVICE_UNAVAILABLE; // 503
            default -> HttpStatus.INTERNAL_SERVER_ERROR; // 500
        };
    }

}