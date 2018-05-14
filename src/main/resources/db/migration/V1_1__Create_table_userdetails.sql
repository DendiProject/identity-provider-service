/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ArtemShevelyukhin
 * Created: 11.02.2018
 */


CREATE TABLE oauth_client_details
(
  client_id character varying(255) NOT NULL,
  resource_ids character varying(255),
  client_secret character varying(255),
  scope character varying(255),
  authorized_grant_types character varying(255),
  web_server_redirect_uri character varying(255),
  authorities character varying(255),
  access_token_validity integer,
  refresh_token_validity integer,
  additional_information character varying(4096),
  autoapprove character varying(255),
  CONSTRAINT oauth_client_details_pkey PRIMARY KEY (client_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE oauth_client_details
  OWNER TO postgres;


CREATE TABLE oauth_client_token
(
  token_id character varying(255),
  token bytea,
  authentication_id character varying(255) NOT NULL,
  user_name character varying(255),
  client_id character varying(255),
  CONSTRAINT oauth_client_token_pkey PRIMARY KEY (authentication_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE oauth_client_token
  OWNER TO postgres;


CREATE TABLE oauth_access_token
(
  token_id character varying(255) UNIQUE,
  token bytea,
  authentication_id character varying(255) NOT NULL,
  user_name character varying(255),
  client_id character varying(255),
  authentication bytea,
  refresh_token character varying(255),
  CONSTRAINT oauth_access_token_pkey PRIMARY KEY (authentication_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE oauth_access_token
  OWNER TO postgres;


CREATE TABLE oauth_refresh_token
(
  token_id character varying(255),
  token bytea,
  authentication bytea
)
WITH (
  OIDS=FALSE
);
ALTER TABLE oauth_refresh_token
  OWNER TO postgres;


CREATE TABLE oauth_code
(
  code character varying(255),
  authentication bytea
)
WITH (
  OIDS=FALSE
);
ALTER TABLE oauth_code
  OWNER TO postgres;


CREATE TABLE oauth_approvals
(
  userId character varying(255),
  clientId character varying(255),
  scope character varying(255),
  status character varying(10),
  expiresAt timestamp without time zone,
  lastModifiedAt timestamp without time zone
)
WITH (
  OIDS=FALSE
);
ALTER TABLE oauth_approvals
  OWNER TO postgres;



CREATE TABLE userdetails
(
  id character varying(255) NOT NULL,
  email character varying(255) NOT NULL,
  firstname character varying(255) ,
  lastname character varying(255),
  password character varying(255) NOT NULL,
  displayname character varying(255),
  age date,
  info character varying(4096),
  CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);

/*
CREATE TABLE ClientDetails
(
  appId character varying(255) NOT NULL,
  resourceIds character varying(255),
  appSecret character varying(255),
  scope character varying(255),
  grantTypes character varying(255),
  redirectUrl character varying(255),
  authorities character varying(255),
  access_token_validity integer,
  refresh_token_validity integer,
  additionalInformation character varying(4096),
  autoApproveScopes character varying(255),
  CONSTRAINT ClientDetails_pkey PRIMARY KEY (appId)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE ClientDetails
  OWNER TO postgres;
*/
