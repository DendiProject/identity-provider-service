/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ArtemShevelyukhin
 * Created: 11.02.2018
 */

CREATE TABLE roles
(
  role_id integer NOT NULL,
  role character varying(255) NOT NULL,
  CONSTRAINT role_pk PRIMARY KEY (role_id)
)
WITH (
  OIDS=FALSE
);