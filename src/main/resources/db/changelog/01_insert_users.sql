--liquibase formatted sql

--changeset zarak:insert_users_zarak
--preconditions onFail:CONTINUE onError:CONTINUE
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM users_security where id = 0;
--comment: Создаёт юзера Zarak
insert into users_security(id, login, email, password, age, userRole)
    values(0, 'Zarak', 'mrzarak0303@gmail.com', '$2a$10$pfw8GapcDZHogtArYshMiOxWUkCUhR0jpV0bneAhOMZVqrixbU61O', 18, 'ADMIN');
--rollback DELETE FROM public.users_security WHERE id = 0;

--changeset zarak:insert_users_boba
--preconditions onFail:CONTINUE onError:CONTINUE
--precondition-sql-check expectedResult:0 SELECT COUNT(*) FROM users_security where id = 1;
--comment: Создаёт юзера Boba
insert into users_security(id, login, email, password, age, userRole)
	values(1, 'Boba', 'mrboba231@gmail.com', '$2a$10$pfw8GapcDZHogtArYshMiOxWUkCUhR0jpV0bneAhOMZVqrixbU61O', 55, 'USER');
--rollback DELETE FROM public.users_security WHERE id = 1;
