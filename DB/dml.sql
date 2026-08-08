-- 註冊會員
INSERT INTO users (user_name, phone, email, password)
VALUES ('hi', '0911122333', 'hi@gmail.com', '123456');

-- 測試貼文
INSERT INTO posts (user_id, content, image)
VALUES (1, '今天天氣很好', NULL);
