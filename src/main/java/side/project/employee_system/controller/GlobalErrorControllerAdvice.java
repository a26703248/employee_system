package side.project.employee_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import side.project.employee_system.utils.ResponseHandle;

@Slf4j
@RestControllerAdvice
public class GlobalErrorControllerAdvice {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value=MethodArgumentNotValidException.class)
  public ResponseHandle handler(MethodArgumentNotValidException e) {
    BindingResult result = e.getBindingResult();
    ObjectError objectError = result.getAllErrors().stream().findFirst().get();
    log.error("表單驗證錯誤------------------{}", e.getMessage());
    return ResponseHandle.error(objectError.getDefaultMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value=RuntimeException.class)
  public ResponseHandle handler(RuntimeException e) {
    log.error("Runtime異常------------------{}", e.getMessage());
    return ResponseHandle.error(e.getMessage());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value=IllegalArgumentException.class)
  public ResponseHandle handler(IllegalArgumentException e) {
    log.error("Argument異常------------------{}", e.getMessage());
    return ResponseHandle.error(e.getMessage());
  }

}
