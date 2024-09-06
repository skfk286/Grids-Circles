-- 관리자 정보 테이블
CREATE TABLE `manager`
(
    `no`         int auto_increment primary key comment '관리자 고유 번호 (자동 증가)',
    `id`         varchar(100) not null comment '관리자 ID',
    `pw`         varchar(100) not null comment '관리자 PW (암호화된 상태로)',
    `created_at` datetime default CURRENT_TIMESTAMP comment '계정 생성 시간',
    `updated_at` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '계정 수정 시간'
) comment = '관리자 정보 테이블';

-- 상품 정보 테이블
CREATE TABLE `product`
(
    `no`         int auto_increment primary key comment '상품 고유 번호 (자동 증가)',
    `name`       varchar(100) not null comment '제품명',
    `price`      int          not null comment '제품 가격',
    `created_at` datetime default CURRENT_TIMESTAMP comment '제품 등록 시간',
    `updated_at` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '제품 수정 시간',
    `manager_no` int comment '관리자 번호(manager no 외래키)',
    FOREIGN KEY (`manager_no`) REFERENCES `manager` (`no`)
) COMMENT ='상품 정보 테이블';

-- 주문 테이블
CREATE TABLE `order`
(
    `no`         int auto_increment primary key comment '주문 고유 번호 (자동 증가)',
    `email`      varchar(100) not null comment '이메일 주소',
    `created_at` datetime default CURRENT_TIMESTAMP comment '주문 요청 시간',
    `updated_at` datetime default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP comment '주문 수정 시간'
) COMMENT ='상품 정보 테이블';

-- 주문-상품 테이블 (n:m 관계)
CREATE TABLE `order_product`
(
    `no`         int auto_increment primary key comment '주문-상품 고유 번호 (자동 증가)',
    `order_no`   int not null comment '주문 번호',
    `product_no` int not null comment '상품 번호',
    `quantity`   int not null comment '수량',
    FOREIGN KEY (`order_no`) REFERENCES `order` (`no`) ON DELETE CASCADE,
    FOREIGN KEY (`product_no`) REFERENCES `product` (`no`) ON DELETE CASCADE
) comment = '주문-상품 테이블'
