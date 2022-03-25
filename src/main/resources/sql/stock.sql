
--股票基本信息，代码和名称
CREATE TABLE `stock_info` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '代码',
    `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_code` (`code`),
    KEY `idx_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--股票行情
CREATE TABLE `stock` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '代码',
    `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
    `price` float NOT NULL COMMENT '当前价格',
    `last_close` float NOT NULL COMMENT '昨收',
    `open` float NOT NULL COMMENT '今开',
    `close` float NOT NULL COMMENT '今收',
    `high` float NOT NULL COMMENT '今高',
    `low` float NOT NULL COMMENT '今低',
    `rise_ratio` float NOT NULL COMMENT '涨幅',
    `total_market_value` double NOT NULL COMMENT '总市值',
    `circulating_market_value` double NOT NULL COMMENT '流通市值',
    `trade_value` float NOT NULL COMMENT '成交额',
    `trade_volume` float NOT NULL COMMENT '成交量',
    `turnover_ratio` float NOT NULL COMMENT '换手率',
    `book_ratio` float NOT NULL COMMENT '市净率',
    `earn_ratio` float NOT NULL COMMENT '市盈率',
    `fund_flow` int NOT NULL COMMENT '资金流向',
    `industry` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '所属行业',
    `stockholder` int NOT NULL COMMENT '股东数量',
    `date` int NOT NULL COMMENT '时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `extra` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '附加信息',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`),
    KEY `idx_earn_ratio` (`earn_ratio`),
    KEY `idx_rise_ratio` (`rise_ratio`),
    KEY `idx_date` (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--股票当前情况
CREATE TABLE `stock_current` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '代码',
    `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
    `current` float NOT NULL COMMENT '当前价',
    `last_close` float NOT NULL COMMENT '昨收',
    `open` float NOT NULL COMMENT '今开',
    `close` float NOT NULL COMMENT '今收',
    `high` float NOT NULL COMMENT '今高',
    `low` float NOT NULL COMMENT '今低',
    `rise_ratio` float NOT NULL COMMENT '涨幅',
    `price_judge` tinyint NOT NULL COMMENT '根据价格波动的判断',
    `k_judge` tinyint NOT NULL COMMENT '根据k线的判断',
    `fund_flow` int NOT NULL COMMENT '资金流向',
    `trade_value` float NOT NULL COMMENT '成交额',
    `trade_volume` float NOT NULL COMMENT '成交量',
    `turnover_ratio` float NOT NULL COMMENT '换手率',
    `book_ratio` float NOT NULL COMMENT '市净率',
    `earn_ratio` float NOT NULL COMMENT '市盈率',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`),
    KEY `idx_price_judge` (`price_judge`),
    KEY `idx_rise_ratio` (`rise_ratio`),
    KEY `idx_ct` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--股票交易记录
CREATE TABLE `stock_trade` (
    `id` int unsigned NOT NULL AUTO_INCREMENT,
    `code` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '代码',
    `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
    `type` float NOT NULL COMMENT '1买入 2卖出 3分红',
    `number` float NOT NULL COMMENT '买入数量',
    `price` float NOT NULL COMMENT '买入价格',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_name` (`name`),
    KEY `idx_ct` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;