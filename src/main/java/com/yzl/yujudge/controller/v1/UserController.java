package com.yzl.yujudge.controller.v1;

import com.yzl.yujudge.core.authorization.AuthorizationRequired;
import com.yzl.yujudge.core.authorization.UserHolder;
import com.yzl.yujudge.core.common.UnifiedResponse;
import com.yzl.yujudge.core.configuration.AuthorizationConfiguration;
import com.yzl.yujudge.dto.LoginDTO;
import com.yzl.yujudge.dto.RegisterDTO;
import com.yzl.yujudge.model.UserEntity;
import com.yzl.yujudge.service.UserService;
import com.yzl.yujudge.utils.EntityToVoListMapper;
import com.yzl.yujudge.utils.EntityToVoMapper;
import com.yzl.yujudge.vo.AuthorizationVO;
import com.yzl.yujudge.vo.PaginationVO;
import com.yzl.yujudge.vo.UserInfoVO;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 用户相关接口控制层
 *
 * @author yuzhanglong
 * @date 2020-08-02 19:50:22
 */

@RestController
@Validated
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final AuthorizationConfiguration authorizationConfiguration;

    public UserController(UserService userService, AuthorizationConfiguration authorizationConfiguration) {
        this.userService = userService;
        this.authorizationConfiguration = authorizationConfiguration;
    }

    /**
     * 注册一个用户
     *
     * @author yuzhanglong
     * @date 2020-08-02 19:52:36
     */
    @PostMapping("/register")
    public UnifiedResponse register(@Validated @RequestBody RegisterDTO registerDTO) {
        userService.userRegister(registerDTO);
        return new UnifiedResponse("注册成功");
    }

    /**
     * 用户鉴权、登录
     *
     * @author yuzhanglong
     * @date 2020-08-02 19:53:08
     */
    @PostMapping("/login")
    public UnifiedResponse login(@RequestBody @Validated LoginDTO loginDTO) {
        String token = userService.userLogin(loginDTO);
        AuthorizationVO authorizationVO = new AuthorizationVO();
        authorizationVO.setAccessToken(token);
        authorizationVO.setExpiresIn(authorizationConfiguration.getExpiredIn());
        return new UnifiedResponse(authorizationVO);
    }

    /**
     * 本接口仅用于token的测试
     *
     * @author yuzhanglong
     * @date 2020-08-03 19:38:25
     */
    @GetMapping("/check_token")
    @AuthorizationRequired
    public UnifiedResponse checkToken() {
        Long userId = UserHolder.getUserId();
        return new UnifiedResponse("验证成功,本接口仅用于token的测试");
    }


    /**
     * 下发验证码图片
     *
     * @author yuzhanglong
     * @date 2020-08-03 19:51:21
     */
    @RequestMapping("/get_check_code")
    public UnifiedResponse getCheckCode() {
        Map<String, String> generatedCodeInfo = userService.generateCheckCode();
        return new UnifiedResponse(generatedCodeInfo);
    }

    /**
     * 获取近期活跃用户
     *
     * @author yuzhanglong
     * @date 2020-08-07 20:13:09
     */
    @GetMapping("/get_active_user")
    @AuthorizationRequired
    public UnifiedResponse getActiveUser(@RequestParam @NotNull Integer amount) {
        List<UserEntity> userEntities = userService.getActiveUser(amount);
        EntityToVoListMapper<UserEntity, UserInfoVO> mapper = new EntityToVoListMapper<>(userEntities, UserInfoVO.class);
        List<UserInfoVO> userInfoVOList = mapper.getItems();
        return new UnifiedResponse(userInfoVOList);
    }


    /**
     * 通过用户id，获取用户信息
     * 我们不会直接让调用者传入用户id
     * 而是通过用户传入的token
     * 将token解析，获取其中包含的用户id
     *
     * @author yuzhanglong
     * @date 2020-08-08 13:11:24
     */
    @GetMapping("/get_user_info")
    @AuthorizationRequired
    public UnifiedResponse getUserInfo() {
        Long userId = UserHolder.getUserId();
        UserEntity user = userService.getUserInfo(userId);
        EntityToVoMapper<UserEntity, UserInfoVO> mapper = new EntityToVoMapper<>(user, UserInfoVO.class);
        return new UnifiedResponse(mapper.getViewObject());
    }

    /**
     * 分页获取所有用户的基本信息
     *
     * @param count 单页数量
     * @param start 页码
     * @author yuzhanglong
     * @date 2020-08-16 13:02:20
     */
    @GetMapping("/get_users")
    public UnifiedResponse getUsers(
            @RequestParam(defaultValue = "0") Integer start,
            @RequestParam(defaultValue = "10") Integer count) {
        Page<UserEntity> userEntities = userService.getUsers(start, count);
        PaginationVO<UserEntity, UserInfoVO> paginationVO = new PaginationVO<>(userEntities, UserInfoVO.class);
        return new UnifiedResponse(paginationVO);
    }


    /**
     * 删除用户
     *
     * @param userId 需要删除的用户Id
     * @author yuzhanglong
     * @date 2020-8-22 14:05:20
     */
    @DeleteMapping("/delete_user/{userId}")
    @AuthorizationRequired
    public UnifiedResponse deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return new UnifiedResponse("删除用户成功");
    }
}