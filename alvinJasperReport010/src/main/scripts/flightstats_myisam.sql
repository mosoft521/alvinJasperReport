CREATE TABLE t_id (
  id int(11) NOT NULL DEFAULT '0',
  name varchar(20) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t_id (id, name) VALUES (1, 'Alvin');
INSERT INTO t_id (id, name) VALUES (2, '张嘉文');
INSERT INTO t_id (id, name) VALUES (3, '随便');
