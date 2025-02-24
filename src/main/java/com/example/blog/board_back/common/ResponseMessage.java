package com.example.blog.board_back.common;

public interface ResponseMessage {
    // 200
    String SUCCESS = "Success";

    // 400
    String VALIDATION_FAILED = "Validation Failed";
    String DUPLICATE_EMAIL = "Duplicate Email";
    String DUPLICATE_NICKNAME = "Duplicate Nickname";
    String DUPLICATE_TEL_NUMBER = "Duplicate Telephone number";
    String NOT_EXISTED_USER = "Not existed User";
    String NOT_EXISTED_BOARD = "Not existed Board";

    // 401
    String SIGN_IN_FAIL = "Sign in Fail";
    String AUTORIZATION_FAIL = "Autorization Fail";

    // 403
    String NO_PERMISSION = "No Permission";

    // 500
    String DATABASE_ERROR = "DataBase Error";
}
