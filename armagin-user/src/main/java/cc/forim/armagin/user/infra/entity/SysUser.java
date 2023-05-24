package cc.forim.armagin.user.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 系统用户实体类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/22 23:17
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@TableName("sys_user")
public class SysUser {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String username;

    private String nickName;

    private String phone;

    private String email;

    private String password;

    private String salt;

    private Date logged;

    private Date createTime;

    private Date editTime;

    private Long version;

    private Integer deleted;

}
