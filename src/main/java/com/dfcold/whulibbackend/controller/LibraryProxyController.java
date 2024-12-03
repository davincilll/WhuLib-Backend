package com.dfcold.whulibbackend.controller;

import com.dfcold.whulibbackend.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dfcold
 * 代理访问图书馆资源
 */
@RestController
@RequestMapping("/proxy")
public class LibraryProxyController {
    @Operation(summary = "图书馆登录", description = "登录图书馆")
    @RequestMapping("/login")
    public Result login(@AuthenticationPrincipal Authentication authentication){
        return Result.ok();

    }
}
