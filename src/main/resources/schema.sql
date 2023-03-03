CREATE TABLE FIGHTER (id int not null auto_increment, created_at timestamp NOT NULL, name VARCHAR(50) NOT NULL,
                      damage_per_hit INT NOT NULL, health INT NOT NULL, resistance DOUBLE NOT NULL, anime_from VARCHAR(50) NOT NULL, primary key (id)
);