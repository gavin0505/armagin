package cc.forim.armagin.user.infra.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.Date;

/**
 * 系统用户角色实体类
 *
 * @author Gavin Zhang
 * @version V1.0
 * @since 2023/5/24 22:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@TableName("sys_role")
public class SysRole {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String code;

    private String remark;

    private Date createTime;

    private Date editTime;

    private Long version;

    private Integer deleted;

}