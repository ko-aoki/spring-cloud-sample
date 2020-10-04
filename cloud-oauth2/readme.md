## 認証サーバ
keycloakを使用しています。
以下を参考にしています。

https://www.keycloak.org/getting-started/getting-started-docker

https://qiita.com/katakura__pro/items/3a769e9df583d1d54e4d

https://qiita.com/Sinclair/items/2577cd5610827de1ba55

https://hub.docker.com/r/jboss/keycloak/

Dockerでpostgresqlとセット

https://github.com/keycloak/keycloak-containers/tree/master/docker-compose-examples

## Keycloakスタート

docker run -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:11.0.2

## Admin Console

http://localhost:8080/auth/admin

admin/Pa55w0rd

## Realmの作成,Client,Userの作成

Realm:myrealm

Client ID:spring-client
Access type:confidential

Valid Redirect URIs:http://localhost:8088/*

username:myuser

### Credentials

myuser/mypwd


