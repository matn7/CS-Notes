**GET**

```
curl http://localhost:8080/api/rooms

# See headers
curl -I http://localhost:8080/api/rooms

curl http://localhost:8080/api/basicAuth/validate

curl -H "Authorization: Basic bWF0dDpzZWNyZXQ=" http://localhost:8080/api/basicAuth/validate

# jane
curl -H "Authorization: Basic amFuZTpzZWNyZXQ=" http://localhost:8080/api/basicAuth/validate

# matt
curl -H "Authorization: Basic bWF0dDpzZWNyZXQ=" http://localhost:8080/api/basicAuth/validate

curl -H "Authorization: Bearer <KEY>" http://localhost:8080/api/users

curl -I -H "Authorization: Basic bWF0dDpzZWNyZXQ=" http://localhost:8080/api/basicAuth/validate
```


