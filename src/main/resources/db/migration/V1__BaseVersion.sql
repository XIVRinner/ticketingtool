CREATE TABLE users
( id INT NOT NULL AUTO_INCREMENT,
  user VARCHAR(150) NOT NULL ,
  password VARCHAR(150) NOT NULL ,
  permisson INT NOT NULL ,

  PRIMARY KEY (id)
)
;

CREATE TABLE customers(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  org VARCHAR(25) NOT NULL
)
;

CREATE TABLE groups(
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(25) NOT NULL,
  customer_id INT,
  manager INT,
  mail VARCHAR(25),

  FOREIGN KEY (manager) REFERENCES users(id),
  FOREIGN KEY (customer_id) REFERENCES customers(id)
)
;
CREATE TABLE `status`(
  code INT PRIMARY KEY NOT NULL,
  name VARCHAR(25)

)
;

INSERT INTO `status`(code, name) VALUES(-2, "OVERDUE");
INSERT INTO `status`(code, name) VALUES(-1, "FAILED");
INSERT INTO `status`(code, name) VALUES(0, "WAITING");
INSERT INTO `status`(code, name) VALUES(1, "APPROVED");
INSERT INTO `status`(code, name) VALUES(2, "IN_PROGRESS");
INSERT INTO `status`(code, name) VALUES(3, "ON_HOLD");
INSERT INTO `status`(code, name) VALUES(100, "DONE");

INSERT INTO `status`(code, name) VALUES(11, "NOT_APPROVED");
INSERT INTO `status`(code, name) VALUES(12, "APPROVED_CHANGE");
INSERT INTO `status`(code, name) VALUES(13, "DECLINED");
INSERT INTO `status`(code, name) VALUES(14, "ON_REVISION");


CREATE TABLE severity(
  code INT NOT NULL PRIMARY KEY,
  name VARCHAR(25)
)
;

INSERT INTO severity(code, name) VALUES(1, "CRITICAL");
INSERT INTO severity(code, name) VALUES(2, "HIGH_RISK");
INSERT INTO severity(code, name) VALUES(3, "MEDIUM_RISK");
INSERT INTO severity(code, name) VALUES(4, "LOW_RISK");
INSERT INTO severity(code, name) VALUES(-1, "NOT_MEASURED");

CREATE TABLE incidents(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  responsible INT,
  short VARCHAR(150) NOT NULL,
  `long` VARCHAR(400),
  status_code INT,
  group_id INT,
  sev INT,
  resolution VARCHAR(400),
  created TIMESTAMP NOT NULL,
  deadline TIMESTAMP NOT NULL,


  FOREIGN KEY (responsible) REFERENCES users(id),
  FOREIGN KEY (status_code) REFERENCES `status`(id),
  FOREIGN KEY (group_id) REFERENCES groups(id)
)
;


CREATE TABLE changes(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  responsible INT,
  short VARCHAR(150) NOT NULL,
  `long` VARCHAR(400),
  status_code INT,
  group_id INT,
  sev INT,
  resolution VARCHAR(400),
  created TIMESTAMP NOT NULL,
  deadline TIMESTAMP NOT NULL,

  FOREIGN KEY (responsible) REFERENCES users(id),
  FOREIGN KEY (status_code) REFERENCES `status`(id),
  FOREIGN KEY (group_id) REFERENCES groups(id)

);




CREATE TABLE affected(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  object_name VARCHAR(100),
  change_id INT,

  FOREIGN KEY (change_id) REFERENCES changes(id)
);


CREATE TABLE approvals(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  change_id INT,
  status_code INT,

  FOREIGN KEY (change_id) REFERENCES changes(id),
  FOREIGN KEY (status_code) REFERENCES `status`(id)
);
