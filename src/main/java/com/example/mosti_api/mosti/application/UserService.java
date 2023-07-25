package com.example.mosti_api.mosti.application;

import com.example.mosti_api.mosti.adapter.ramda.NftGateway;
import com.example.mosti_api.mosti.adapter.ramda.dto.response.RamdaMapResponseDto;
import com.example.mosti_api.mosti.application.domain.User;
import com.example.mosti_api.mosti.application.domain.factory.UserFactory;
import com.example.mosti_api.mosti.application.dto.request.user.UserCreateReqDto;
import com.example.mosti_api.mosti.application.dto.request.user.UserUpdateReqDto;
import com.example.mosti_api.mosti.application.dto.response.UserRespDto;
import com.example.mosti_api.mosti.application.port.in.IUserService;
import com.example.mosti_api.mosti.application.port.out.IUserRepository;
import io.netty.util.internal.StringUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;
    private final NftGateway nftGateway;


    public UserService(IUserRepository userRepository, PasswordEncoder passwordEncoder, ApplicationEventPublisher eventPublisher, NftGateway nftGateway) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
        this.nftGateway = nftGateway;
    }

    @Override
    public List<UserRespDto> 목록조회() {
        return this.userRepository.목록조회().stream().map(UserRespDto::생성).collect(Collectors.toList());
    }

    @Override
    public UserRespDto 조회(String loginId) {
        return UserRespDto.생성(this.userRepository.조회(loginId));
    }

    @Override
    public void 추가(UserCreateReqDto userCreateReqDto) {

    }

    @Override
    public void 수정(UserUpdateReqDto userUpdateReqDto) {

    }

    @Override
    public RamdaMapResponseDto 지갑정보생성및수정(long userId) {
        Map<String,String> param = new ConcurrentHashMap<>();
        param.put("userId",String.valueOf(userId));
        RamdaMapResponseDto respDto =
                this.nftGateway.requestWithPostWebClient(NftGateway.API.CREATE_WALLET, RamdaMapResponseDto.class,param);
        this.userRepository.지갑정보수정(respDto.getData().get("walletId"),String.valueOf(respDto.getData().get("address")),userId);
        return respDto;
    }

}
