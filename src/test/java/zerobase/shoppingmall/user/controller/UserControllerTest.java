package zerobase.shoppingmall.user.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jodd.net.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import zerobase.shoppingmall.response.BaseResponse;
import zerobase.shoppingmall.user.dto.UserInput;
import zerobase.shoppingmall.user.dto.entity.User;
import zerobase.shoppingmall.user.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {//공부하고 다시.

    @InjectMocks
    private UserController userController;

    @MockBean
    private UserService userService;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void successCreateUser() throws Exception {
        //given
        UserInput input = userInput();
        BaseResponse baseResponse = baseResponse();
        doReturn(baseResponse).when(userService).register(any(UserInput.class));

        //when
        ResultActions resultActions = mockMvc.perform(
            MockMvcRequestBuilders.post("/register/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(input))
        );

        //then
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
    }

    private UserInput userInput() {
        return UserInput.builder()
            .user_id("test@test.com")
            .password("1111")
            .address("aaa")
            .phone("010-1111-1111")
            .zipcode("02345")
            .userName("홍길동")
            .build();
    }

    private BaseResponse baseResponse() {
        return BaseResponse.builder()
            .code(HttpStatus.ok().status())
            .httpStatus(HttpStatus.ok())
            .message("회원 등록에 성공했습니다. 이메일 인증해주세요.")
            .build();
    }
//
//    @Test
//    void emailAuth() {
//    }
//
//    @Test
//    void login() {
//    }
}