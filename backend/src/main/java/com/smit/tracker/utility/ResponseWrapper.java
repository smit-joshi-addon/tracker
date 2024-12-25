package com.smit.tracker.utility;

public class ResponseWrapper<T> {

    private boolean success;
    private String message;
    private T data;

    // Constructors
    public ResponseWrapper() {
    }

    public ResponseWrapper(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Static factory methods for convenience
    public static <T> ResponseWrapper<T> success(String message, T data) {
        return new ResponseWrapper<>(true, message, data);
    }

    public static <T> ResponseWrapper<T> failure(String message) {
        return new ResponseWrapper<>(false, message, null);
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
