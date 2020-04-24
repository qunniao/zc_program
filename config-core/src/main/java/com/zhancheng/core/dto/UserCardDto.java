package com.zhancheng.core.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.type.Alias;

/**
 * @author tangchao
 */
@Data
@Accessors(chain = true)
public class UserCardDto {

    private String name;
    private String companyName;
    private String companyPosition;
    private String phone;
    private String userCover;
    private String cardCover;
    private String weChat;
    private String email;
    private String address;
    private String introText;
    private String codeImage;
    private String nickname;
    private Integer viewNum;
    private Integer likeNum;

}
