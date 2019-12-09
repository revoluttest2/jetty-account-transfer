create table ACCOUNT (
    ACCOUNT_ID LONG PRIMARY KEY,
    BALANCE DECIMAL DEFAULT 0 NOT NULL
);
insert into ACCOUNT(account_id, balance) values ( 1, 10000 );
insert into ACCOUNT(account_id, balance) values ( 2, 20000 );
