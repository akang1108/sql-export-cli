package info.akang.sqlexport.cli

class UnknownCommandException extends RuntimeException {
    UnknownCommandException() {
        super()
    }

    UnknownCommandException(String message) {
        super(message)
    }

    UnknownCommandException(String message, Throwable cause) {
        super(message, cause)
    }

    UnknownCommandException(Throwable cause) {
        super(cause)
    }

    protected UnknownCommandException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace)
    }
}
