CREATE TABLE "public"."users"
(
    "id"         bigserial primary key,
    "email"      varchar      NOT NULL,
    "username"   varchar,
    "phone"      varchar,
    "password"   varchar      NOT NULL,
    "status"     int4         NOT NULL DEFAULT 0,
    "created_at" timestamp(0) NOT NULL,
    "updated_at" timestamp(0) NOT NULL,
    "deleted_at" timestamp(0)
);

COMMENT ON COLUMN "public"."users"."username" IS '用户昵称';
COMMENT ON COLUMN "public"."users"."phone" IS '联系人电话';
COMMENT ON COLUMN "public"."users"."status" IS '账户状态';
COMMENT ON COLUMN "public"."users"."updated_at" IS '更新时间';
COMMENT ON COLUMN "public"."users"."deleted_at" IS '删除时间';

CREATE TABLE "public"."blogs"
(
    "id"         bigserial primary key,
    "title"      varchar      NOT NULL,
    "detail"     varchar      NOT NULL,
    "user_id"    int8         NOT NULL,
    "created_at" timestamp(0) NOT NULL,
    "updated_at" timestamp(0) NOT NULL,
    "deleted_at" timestamp(0)
);
COMMENT ON COLUMN "public"."blogs"."title" IS '文章标题';
COMMENT ON COLUMN "public"."blogs"."detail" IS '文章内容';
COMMENT ON COLUMN "public"."blogs"."user_id" IS '所属用户';