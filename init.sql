-- orders 테이블 생성
CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(100) NOT NULL,
    product_name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL,
    amount INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
    order_date TIMESTAMP NOT NULL
);

-- 10만 건 더미 데이터 생성
INSERT INTO orders (
    user_name,
    product_name,
    category,
    amount,
    status,
    order_date
)
SELECT
    'user_' || gs,

    CASE (gs % 5)
        WHEN 0 THEN '스키 패키지'
        WHEN 1 THEN '워터파크 티켓'
        WHEN 2 THEN '호텔 숙박권'
        WHEN 3 THEN '리조트 이용권'
        ELSE '스노우보드 패키지'
    END,

    CASE (gs % 3)
        WHEN 0 THEN '스키'
        WHEN 1 THEN '워터파크'
        ELSE '숙박'
    END,

    (10000 + floor(random() * 490001))::INTEGER,

    CASE (gs % 3)
        WHEN 0 THEN 'confirmed'
        WHEN 1 THEN 'cancelled'
        ELSE 'pending'
    END,

    NOW()
        - ((random() * 365)::INTEGER || ' days')::INTERVAL
        - ((random() * 24)::INTEGER || ' hours')::INTERVAL
        - ((random() * 60)::INTEGER || ' minutes')::INTERVAL

FROM generate_series(1, 100000) gs;