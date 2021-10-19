--liquibase formatted sql

--changeset zarak:log_boot_create
--comment: Создаёт таблицу boot_info
CREATE TABLE IF NOT EXISTS boot_info(
	id SERIAL PRIMARY KEY,
	boot_time TIMESTAMP NOT NULL
);
--rollback DROP TABLE boot_info;

--changeset zarak:log_boot_update runAlways:true
--comment: При каждом запуске добавляет значение в таблицу boot_info
insert into boot_info(boot_time)
	values(now()::timestamp);
--rollback DELETE * FROM boot_info;
