create table Weapon(
  W_id integer,
  W_name varchar(20),
  W_attack integer,
  W_cost integer,
  constraint player_W_id_pk primary key (W_id)
);

create table Skill(
  S_id integer,
  S_name varchar(20),
  S_attack integer,
  S_mpcost integer,
  S_levellock integer,
  constraint player_S_id_pk primary key (S_id)
);

create table Player (
  P_id integer not null,
  P_name varchar(20),
  P_maxhp integer,
  P_hp integer,
  P_maxmp integer,
  P_mp integer,
  P_attack integer,
  P_define integer,
  P_level integer,
  P_experience integer,
  P_money integer,
  W_id integer,
  S_id integer,
  P_time time,
  constraint player_P_id_pk primary key (P_id),
  constraint player_Weapon_fk foreign key (W_id) references Weapon (W_id),
  constraint player_Skill_fk foreign key (S_id) references Skill (S_id)
);

create table Monster(
  M_id integer,
  M_name varchar(20),
  M_maxhp integer,
  M_hp integer,
  M_maxmp integer,
  M_mp integer,
  M_attack integer,
  M_define integer,
  M_level integer,
  M_experience integer,
  M_money integer,
  S_id integer,
  constraint player_M_id_pk primary key (M_id),
  constraint monster_Skill_fk foreign key (S_id) references Skill (S_id)
);


create table Game(
    G_id integer,
    P_id integer,
    G_state varchar(20),
    G_map varchar(20),
    constraint game_G_id_pk primary key (G_id),
    constraint game_player_fk foreign key (P_id) references Player (P_id)
);


insert into Monster values (1, 'monsterA', 50, 50, 0, 0, 12, 0, 2, 2, 55, null);
insert into Monster values (2, 'bossA', 500, 500, 300, 300, 40, 30, 4, 10, 1000, null);
insert into Monster values (3, 'monsterB', 150, 150, 20, 20, 25, 15, 3, 4, 120, null);
insert into Monster values (4, 'bossB', 700, 700, 1000, 1000, 30, 20, 7, 20, 3000, null);
insert into Monster values (5, 'bossC', 1000, 1000, 1000, 1000, 100, 100, 9, 10, 0, null);


insert into Weapon values (1, 'longSword', 10, 360);
insert into Weapon values (2, 'BFSword', 50, 800);
insert into Weapon values (3, 'theBloodthirster', 100, 1500);
insert into Weapon values (4, 'doransBlade', 200, 2400);


insert into Skill values (1, 'saberSkill1', 50, 50, 2);
insert into Skill values (2, 'saberSkill2', 100, 100, 5);
insert into Skill values (3, 'saberSkill3', 200, 200, 8);
insert into Skill values (4, 'lancerSkill1', 50, 50, 2);
insert into Skill values (5, 'lancerSkill2', 100, 100, 5);
insert into Skill values (6, 'lancerSkill3', 200, 200, 8);
insert into Skill values (7, 'casterSkill1', 50, 50, 2);
insert into Skill values (8, 'casterSkill2', 100, 100, 5);
insert into Skill values (9, 'casterSkill3', 200, 200, 8);
insert into Skill values (10, 'bossASkill', 100, 100, 0);
insert into Skill values (11, 'bossBSkill', 200, 200, 0);
insert into Skill values (12, 'bossCSkill', 300, 300, 0);


