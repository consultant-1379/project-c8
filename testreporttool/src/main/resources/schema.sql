DROP TABLE IF EXISTS CNA;
DROP TABLE IF EXISTS CSA;
DROP TABLE IF EXISTS CRA;
DROP TABLE IF EXISTS CXP;
DROP TABLE IF EXISTS JENKINS;

CREATE TABLE CNA
(
    name varchar(100),
    number varchar(50)
);



CREATE TABLE CSA
(
    name varchar(100),
    number varchar(50)
);



CREATE TABLE CRA
(
    name varchar(100),
    number varchar(50)
);



CREATE TABLE CXP
(
    CSA varchar(100),
    CRA varchar(100),
    CNA varchar(100),
    CXP varchar(100),
    Number varchar(50),
    RPM varchar(200),
    TAF varchar(100)
);



CREATE TABLE JENKINS
(
    Number varchar(50),
    TestURLS varchar(100),
    Pass int,
    Fail int,
    Date DATETIME(2)
);