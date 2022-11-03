package br.gov.sp.fatec.projetolab5.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AnotacaoException extends RuntimeException {

  public AnotacaoException(String message) {
    super(message);
  }

  public AnotacaoException(String message, Throwable cause) {
    super(message, cause);
  }
}
