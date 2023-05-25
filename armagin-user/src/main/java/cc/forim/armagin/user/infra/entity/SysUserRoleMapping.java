package cc.forim.armagin.user.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 系统用户权限映射实体类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/24 22:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@TableName("sys_user_role_mapping")
public class SysUserRoleMapping {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Date createTime;

    private Date editTime;

    private Long version;

    private Integer deleted;

}