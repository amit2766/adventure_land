package com.amit.al.exceptions;

public class DeathException extends Throwable {
    public DeathException(String enemyName) {
        super(enemyName);
    }

}
