# sqlite-android-test
Basic sqlite test app.

## Sobre: 

Aplicação básica. A finalidade deste aplicativo é de teste. Ou seja, após configurar o sqlite com o Android Studio simplesmente 
editando o **profile** localizado em ```/etc/profile``` e especificando o uso das ferramentas já instaladas e prontas para uso no Android Studio, 
como o sqlite.

### Tutorial:

#### VERIFICAR O *PATH* da IDE em *configurar JDK* no Android Studio

#### COPIAR o *PATH*

#### EDITAR o arquivo profile localizado em /etc/profile

```
export PATH="$PATH:/home/user/Android/Sdk/platform-tools"
```

#### VERIFICAR se deu certo utilizando comando *ver versão sqlite3* : ```sqlite3```

#### RESULTADO:
```
:~$ sqlite3
SQLite version 3.32.2 2021-07-12 15:00:17
Enter ".help" for usage hints.
Connected to a transient in-memory database.
Use ".open FILENAME" to reopen on a persistent database.
sqlite> 
```
#### SAIR: ```.quit```

## PRONTO!


