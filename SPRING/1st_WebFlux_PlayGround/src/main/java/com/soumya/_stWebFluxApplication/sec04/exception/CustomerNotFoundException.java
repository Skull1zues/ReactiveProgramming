package com.soumya._stWebFluxApplication.sec04.exception;

public class CustomerNotFoundException extends RuntimeException {

  private static final String MESSAGE = "Customer [id=%d] not Found";

  public CustomerNotFoundException(Integer id) {
    super(MESSAGE.formatted(id));
  }
}
