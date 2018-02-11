/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ArtemShevelyukhin
 * Created: 11.02.2018
 */

CREATE TABLE userdetails
(
  id character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  firstname character varying(255) NOT NULL,
  lastname character varying(255),
  password character varying(255) NOT NULL,
  displayname character varying(255) NOT NULL,
  age integer NOT NULL,
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);