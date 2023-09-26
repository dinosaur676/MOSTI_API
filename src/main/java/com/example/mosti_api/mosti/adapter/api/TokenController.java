package com.example.mosti_api.mosti.adapter.api;

import com.example.mosti_api.framework.dto.ResponseDto;
import com.example.mosti_api.framework.dto.SuccessRespDto;
import com.example.mosti_api.mosti.application.domain.UserToken;
import com.example.mosti_api.mosti.application.dto.request.token.UserTokenReqDto;
import com.example.mosti_api.mosti.application.dto.response.token.MintWaitRespDto;
import com.example.mosti_api.mosti.application.dto.response.token.UserTokenRespDto;
import com.example.mosti_api.mosti.application.port.in.IGatewayService;
import com.example.mosti_api.mosti.application.port.in.IMintWaitService;
import com.example.mosti_api.mosti.application.port.in.ITokenInfoService;
import com.example.mosti_api.mosti.application.port.in.IUserTokenService;
import com.example.mosti_api.mosti.application.util.JWTUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/token")
public class TokenController {

    final IGatewayService gatewayService;
    final IUserTokenService userTokenService;
    final ITokenInfoService tokenInfoService;
    final IMintWaitService mintWaitService;

    public TokenController(IGatewayService gatewayService, IUserTokenService userTokenService, ITokenInfoService tokenInfoService, IMintWaitService mintWaitService) {
        this.gatewayService = gatewayService;
        this.userTokenService = userTokenService;
        this.tokenInfoService = tokenInfoService;
        this.mintWaitService = mintWaitService;
    }

    @GetMapping("/info")
    public ResponseDto getInfoSelect(@AuthenticationPrincipal Jwt jwt, @RequestParam(required = false) Long tokenId) {
        if(tokenId == null) {
            return new SuccessRespDto(tokenInfoService.select(JWTUtil.getEmail(jwt)), "성공");
        }
        else {
            return new SuccessRespDto(tokenInfoService.selectByTokenId(tokenId.longValue()), "성공");
        }
    }

    @GetMapping("/user")
    public ResponseDto getUserTokenList(@AuthenticationPrincipal Jwt jwt, @RequestParam String walletName, @RequestParam String walletTag) {
        List<UserTokenRespDto> tokenRespDtoList = userTokenService.select(walletName, walletTag, JWTUtil.getEmail(jwt));

        if(tokenRespDtoList.isEmpty() || tokenRespDtoList == null)
        {
            return new SuccessRespDto(new ArrayList<>(), "비었음");
        }

        return new SuccessRespDto(tokenRespDtoList, "성공");
    }

    @GetMapping("/wait")
    public ResponseDto getWaitTokenList(@AuthenticationPrincipal Jwt jwt) {
        List<MintWaitRespDto> dto = mintWaitService.select(JWTUtil.getEmail(jwt));

        if(dto == null || dto.isEmpty()) {
            return new SuccessRespDto(new ArrayList<>(), "성공");
        }

        return new SuccessRespDto(dto, "성공");
    }

    @PostMapping("/user")
    public ResponseDto mintToken(@AuthenticationPrincipal Jwt jwt, @RequestBody UserTokenReqDto userTokenReqDto) {
        MintWaitRespDto mintWaitRespDto = mintWaitService.selectByTokenId(JWTUtil.getEmail(jwt), userTokenReqDto.tokenId());

        if(mintWaitRespDto == null) {
            return new SuccessRespDto("없는 토큰");
        }

        LocalDateTime deletedOn = LocalDateTime.of(2030, Month.DECEMBER, 31, 0, 0);

        gatewayService.mintTokenInPublic(userTokenReqDto.address(), userTokenReqDto.tokenId(), deletedOn);
        userTokenService.insert(JWTUtil.getEmail(jwt), userTokenReqDto.walletName(), userTokenReqDto.walletTag(),
                userTokenReqDto.tokenId(), mintWaitRespDto.createdOn(), LocalDateTime.now());
        mintWaitService.update(JWTUtil.getEmail(jwt), userTokenReqDto.tokenId());

        return new SuccessRespDto("성공");
    }


}
