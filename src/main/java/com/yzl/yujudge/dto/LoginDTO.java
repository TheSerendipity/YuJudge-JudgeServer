package com.yzl.yujudge.dto;

import com.yzl.yujudge.utils.validators.annotations.LoginFormValidated;

import javax.validation.constraints.NotNull;

/**
 * @author yuzhanglong
 * @description 用户登录操作的数据传输对象
 * @date 2020-08-02 19:56:48
 */

@LoginFormValidated
public class LoginDTO {
    private String nickname;

    private String email;

    @NotNull(message = "密码不得为空")
    private String password;

    @NotNull(message = "验证码不得为空")
    private String checkCodeContent;

    @NotNull(message = "验证码key不得为空")
    private String checkCodeKey;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getCheckCodeContent() {
        return checkCodeContent;
    }

    public void setCheckCodeContent(String checkCodeContent) {
        this.checkCodeContent = checkCodeContent;
    }

    public String getCheckCodeKey() {
        return checkCodeKey;
    }

    public void setCheckCodeKey(String checkCodeKey) {
        this.checkCodeKey = checkCodeKey;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
                "nickname='" + nickname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", checkCodeContent='" + checkCodeContent + '\'' +
                ", checkCodeKey='" + checkCodeKey + '\'' +
                '}';
    }
}
