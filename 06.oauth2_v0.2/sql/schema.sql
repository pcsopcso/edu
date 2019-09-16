CREATE USER oauth2 IDENTIFIED BY sa;
--생성된 사용자에게 CONNECT 권한 및 RESOURCE 권한을 준다.
--   CONNECT는 접속 권한
--   RESOURCE는 객체(생성, 수정, 삭제), 데이터(입력, 수정, 조회, 삭제) 권한
--   RESOURCE로 권한을 주지 않고 테이블 생성 데이터 입력만 가능하도록 일부 권한만 줄 수도 있다.
--   GRANT 권한 종류1, 권한 종류2 TO 권한을 줄 사용자
--GRANT CONNECT, RESOURCE TO SAMPLE_USER;
--GRANT CONNECT,
--resource TO oauth_user;
-- 클라이언트 등록과 관련된 데이터 테이블
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(2000),
  autoapprove VARCHAR(256)
);
-- AUTHORIZATION SERVER ClientDetailsServiceConfigurer IN Memory 형식에 정의했던 데이터 삽입
INSERT INTO
  oauth_client_details (
    client_id,
    resource_ids,
    client_secret,
    scope,
    authorized_grant_types,
    web_server_redirect_uri,
    authorities,
    access_token_validity,
    refresh_token_validity,
    additional_information,
    autoapprove
  )
VALUES
  (
    '4c73df0f-a991-46e2-ba3e-94ff55c3c487',
    'daniel_client',
    --4901799c-843a-49dd-81bb-52f368b6dba4
    'c862442d26fdd5682306ba5b07dcf5f0b51074d578f3d001ee7229469d545cbb',
    'read,write',
    'authorization_code,implicit,password,client_credentials,refresh_token',
    'http://localhost:9000/callback',
    null,
    3000,
    null,
    '{"name":"client3","client_type":"PUBLIC"}',
    'false'
  );
INSERT INTO
  oauth_client_details (
    client_id,
    resource_ids,
    client_secret,
    scope,
    authorized_grant_types,
    web_server_redirect_uri,
    authorities,
    access_token_validity,
    refresh_token_validity,
    additional_information,
    autoapprove
  )
VALUES
  (
    'clientapp2',
    null,
    '311ffdc998038e85ac0b5bd1fb20097a67281b7c0bc0ef905771daec9eb52b66',
    'read_profile,read_posts',
    'authorization_code,refresh_token',
    'http://localhost:9000/callback',
    null,
    3000,
    -1,
    null,
    'false'
  );
-- 발급된 액세스 토큰을 저장하기 위한 테이블
  create table oauth_access_token (
    token_id VARCHAR(256),
    token clob,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name VARCHAR(256),
    client_id VARCHAR(256),
    authentication clob,
    refresh_token VARCHAR(256)
  );
-- 리프레시 토큰 발급을 위한 테이블
  create table oauth_refresh_token (
    token_id VARCHAR(256),
    token varchar(1000),
    authentication varchar(1000)
  );
SELECT
  *
from
  OAUTH_REFRESH_TOKEN;
-- 사용자의 승인을 저장하기 위한 테이블
  create table oauth_approvals (
    userId VARCHAR(256),
    clientId VARCHAR(256),
    scope VARCHAR(256),
    status VARCHAR(10),
    expiresAt TIMESTAMP,
    lastModifiedAt TIMESTAMP
  );
create table oauth_code (code VARCHAR(256), authentication blob);
-- 클라이언트 유저테이블
  create table client_user(
    id integer primary key,
    username varchar(100),
    password varchar(50),
    access_token varchar(2000),
    access_token_validity TIMESTAMP,
    refresh_token varchar(2000)
  );