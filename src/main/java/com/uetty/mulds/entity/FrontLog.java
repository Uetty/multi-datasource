package com.uetty.mulds.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.annotatoin.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Table(name = "sys_front_log")
@Data
public class FrontLog {
    @AutoID
    private Long id;
    private Long userId;
    private String username;
    private Long companySpaceId;
    private String ip;
    private String name;
    private String fullPath;
    private String title;
    private String envLabel;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
}
