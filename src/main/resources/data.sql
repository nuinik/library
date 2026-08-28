insert into USERS(id, username, password)
values
    (1, 'user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'); -- password = admin

insert into AUTHORITY (id, authority_name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');

insert into USERS_AUTHORITY (user_id, authority_id)
values
    (1, 2),
    (2, 1);

insert into POLAZNIK(ime, prezime)
VALUES ('Ivo', 'Ivić');

insert into PROGRAM_OBRAZOVANJA(naziv, csvet)
VALUES ('Prvi', 3);

insert into UPIS(PROGRAM_OBRAZOVANJA_ID, POLAZNIK_ID)
VALUES(1, 1);