-- 1) user 먼저 (board_id 생략/NULL)
INSERT INTO user_tb (username, age, login_id, password, email, create_at)
VALUES ('alice', 25, '123', '123', 'alice@test.com', CURRENT_TIMESTAMP);
INSERT INTO user_tb (username, age, login_id, password, email, create_at)
VALUES ('bob', 31, 'bob31', '{noop}pass2', 'bob@test.com', CURRENT_TIMESTAMP);
INSERT INTO user_tb (username, age, login_id, password, email, create_at)
VALUES ('chris', 28, 'chris28', '{noop}pass3', 'chris@test.com', CURRENT_TIMESTAMP);

-- 2) board (Board 엔티티 쪽에 user FK가 있다면 user_id 채워서)
INSERT INTO board_tb (title, content, user_id, create_at)
VALUES ('hello', 'first post', 1, CURRENT_TIMESTAMP);
INSERT INTO board_tb (title, content, user_id, create_at)
VALUES ('spring', 'boot is fun', 2, CURRENT_TIMESTAMP);
INSERT INTO board_tb (title, content, user_id, create_at)
VALUES ('jpa', 'relation fixed', 3, CURRENT_TIMESTAMP);


