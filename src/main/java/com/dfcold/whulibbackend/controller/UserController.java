package com.dfcold.whulibbackend.controller;

import com.dfcold.whulibbackend.common.Result;
import com.dfcold.whulibbackend.domain.dto.req.AuthReq;
import com.dfcold.whulibbackend.domain.dto.req.UserUpdateReq;
import com.dfcold.whulibbackend.domain.entity.User;
import com.dfcold.whulibbackend.domain.service.IUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.time.Instant;

/**
 * @author dfcold
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    JwtEncoder encoder;

    @Resource
    IUserService userService;
    @Resource
    ObjectMapper objectMapper;

    @Operation(summary = "获取token", description = "获取token")
    @PostMapping("/token")
    public Result token(@RequestBody AuthReq authReq) {
        Instant now = Instant.now();
        long expiry = 36000L;
        String scope = "";
        User user = userService.getUserByUsernameAndPassWord(authReq.getUsername(), authReq.getPassword());
        //String scope = authentication.getAuthorities().stream()
        //        .map(GrantedAuthority::getAuthority)
        //        .collect(Collectors.joining(" "));
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")

                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(String.valueOf(user.getId()))
                // 声明权限字段
                .claim("scope", scope)
                .build();
        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return Result.ok().data("token", token);
    }
    @Operation(summary = "注册", description = "注册")
    @PostMapping("/register")
    public Result register(@RequestBody AuthReq authReq) throws JsonProcessingException {
        User user = new User();
        BeanUtils.copyProperties(authReq, user);
        boolean success = userService.save(user);
        if (success) {
            user = userService.getUserByUsernameAndPassWord(authReq.getUsername(), authReq.getPassword());
            return Result.ok().data("user",user);
        }else {
            return Result.error();
        }
    }
    @Operation(summary = "根据id获取用户信息", description = "根据id获取用户信息")
    @GetMapping
    public Result getById(Authentication authentication) {
        Long userId = Long.parseLong(authentication.getName());
        User user = userService.getById(userId);
        return Result.ok().data("user", user);

    }
    @Operation(summary = "部分更新用户信息", description = "部分更新用户信息")
    @PatchMapping
    public Result partialUpdate(@RequestBody UserUpdateReq userUpdateReq) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateReq, user);
        userService.updateById(user);
        return Result.ok();
    }
}


