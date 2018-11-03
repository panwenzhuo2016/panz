CREATE TABLE `sys_user` (
  `user_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
  `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
  `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE `sys_role` (
  `role_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名',
  `parent_role_id` char(32) NOT NULL DEFAULT '' COMMENT '父id',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
   `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `sys_auth` (
  `auth_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `auth_name` varchar(100) DEFAULT NULL COMMENT '权限名',
  `parent_auth_id` char(32) NOT NULL DEFAULT '' COMMENT '父id',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
   `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

CREATE TABLE `sys_group` (
  `group_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `group_name` varchar(100) DEFAULT NULL COMMENT '组名',
  `parent_group_id` char(32) NOT NULL DEFAULT '' COMMENT '父id',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
   `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组表';

CREATE TABLE `sys_role_auth` (
  `role_auth_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `role_id` char(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `auth_id` char(32) NOT NULL DEFAULT '' COMMENT '权限id',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
   `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

CREATE TABLE `sys_group_auth` (
  `group_auth_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `group_id` char(32) NOT NULL DEFAULT '' COMMENT '组id',
  `auth_id` char(32) NOT NULL DEFAULT '' COMMENT '权限id',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
    `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
   `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`group_auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组权限表';

CREATE TABLE `sys_group_role` (
  `group_role_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `group_id` char(32) NOT NULL DEFAULT '' COMMENT '组id',
  `role_id` char(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
  `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
  `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`group_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组角色表';


CREATE TABLE `sys_user_auth` (
  `user_auth_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `user_id` char(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `auth_id` char(32) NOT NULL DEFAULT '' COMMENT '权限id',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
  `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
  `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户权限表';

CREATE TABLE `sys_user_role` (
  `user_role_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `user_id` char(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `role_id` char(32) NOT NULL DEFAULT '' COMMENT '角色id',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
  `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
  `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

CREATE TABLE `sys_user_group` (
  `user_group_id` char(32) NOT NULL DEFAULT '' COMMENT '主健',
  `user_id` char(32) NOT NULL DEFAULT '' COMMENT '用户id',
  `group_id` char(32) NOT NULL DEFAULT '' COMMENT '组id',
  `create_name` varchar(100) DEFAULT NULL COMMENT '创建人',
  `create_time` int DEFAULT NULL COMMENT '创建时间',
  `modify_name` varchar(100) DEFAULT NULL COMMENT '修改人',
  `modify_time` int DEFAULT NULL COMMENT '修改时间',
  `is_deleted`  tinyint(1) DEFAULT 0 COMMENT '是否删除',
  `beizu` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组表';












