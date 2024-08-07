package roomescape.service.auth;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;
import roomescape.domain.member.Email;
import roomescape.domain.member.Member;
import roomescape.domain.member.Role;
import roomescape.dto.login.LoginMember;
import roomescape.dto.login.LoginRequest;
import roomescape.dto.member.MemberPayload;
import roomescape.dto.token.TokenDto;
import roomescape.exception.custom.RoomEscapeException;
import roomescape.infrastructure.auth.JwtProvider;
import roomescape.infrastructure.auth.PasswordEncoder;
import roomescape.repository.MemberRepository;

@Service
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    public TokenDto login(LoginRequest request) {
        Member loginMember = authenticateUser(request);
        MemberPayload loginMemberPayload = MemberPayload.from(loginMember);
        String token = jwtProvider.createToken(loginMemberPayload);
        return new TokenDto(token);
    }

    public boolean isValidateToken(TokenDto tokenDto) {
        String token = tokenDto.accessToken();
        return token != null && jwtProvider.isValidateToken(token);
    }

    public Long extractMemberIdByToken(final TokenDto tokenDto) {
        String token = tokenDto.accessToken();
        return Long.parseLong(jwtProvider.getSubject(token));
    }

    public LoginMember extractLoginMemberByToken(TokenDto tokenDto) throws Exception {
        String token = tokenDto.accessToken();
        return createLoginMemberByToken(token);
    }

    private Member authenticateUser(LoginRequest request) {
        Email email = new Email(request.email());
        Member member = getMemberByEmail(email);
        validatePassword(request, member);
        return member;
    }

    private Member getMemberByEmail(Email email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new RoomEscapeException(
                        "등록된 아이디가 아닙니다.",
                        "id(email) : " + email
                ));
    }

    private LoginMember createLoginMemberByToken(String token) throws Exception {
        try {
            Claims claims = jwtProvider.getClaims(token);
            Long userId = Long.parseLong(jwtProvider.getSubject(token));
            String userName = claims.get("name", String.class);
            String userEmail = claims.get("email", String.class);
            Role userRole = Role.valueOf(claims.get("role", String.class));
            return new LoginMember(userId, userName, userEmail, userRole);
        } catch (Exception e) {
            throw new Exception("검증되지 않은 토큰입니다. 먼저 토큰 검증을 해주세요.");
        }
    }

    private void validatePassword(LoginRequest request, Member memberToLogin) {
        if (!passwordEncoder.matches(request.password(), memberToLogin.getPassword())) {
            throw new RoomEscapeException("잘못된 비밀번호 입니다.");
        }
    }
}
