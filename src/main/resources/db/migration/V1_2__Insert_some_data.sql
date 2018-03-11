/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ArtemShevelyukhin
 * Created: 11.02.2018
 */

/** Oauth - populate the oauth_client_details table */
INSERT INTO oauth_client_details(
            client_id, client_secret, scope, authorized_grant_types, 
            access_token_validity, 
             additional_information)
    VALUES ('web', 'secret', 'read', 'authorization_code,password,refresh_token,implicit', '900', '{}') ;


INSERT INTO userdetails(
            id, email, password)
    VALUES ('1', 'dazito.com', 'dazito');
