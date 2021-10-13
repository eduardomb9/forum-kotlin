ALTER TABLE usuario ADD COLUMN senha varchar(255);

update usuario set senha = '$2y$12$rnZ4zWxhPv.7iSOhBKkGqu2PVsJf./W78TScYJd.EWdaEeWhnhQW.' where id = 1;