# File List View
This dummy project generate us a pretty of special files. 
There is a lot of files in a directory and there is an order. 
This project give us the pretty print of that: 
```text
->file1
    ->file2
        ->file3
            ...
                ->fileN
```

It is a Java Spring Boot application with Gradle (Groovy)

It has Swagger UI

Exposed its own JavaDoc

You can create those pretty view of the files

And can get the history of the result requests

# Dependencies & Build & Run
if you want to run it locally then you need:
- Postgresql DB
  - the port is :  5432
  - the database name is : DB
  - the username is : developer
  - the password is : develop
- you need to generate javaDoc in the /src/main/resources/static/doc
- You need gradle (I use the version of 8.5.0)
- And Java (I use the version 17)

But it has a script (.sh and .bat) which makes you everything and only need `podman`.
This script will:
- create a DB
- generate the javadoc
- build the app
- and start the app in 2 instance
  - localhost:8081
  - localhost:8082


# Endpoints

### /swagger -> swagger UI
### /doc -> JavaDoc
### GET /result?n={number of good files}&m={number of mistaken files}
### GET /history -> Return all the histories. 


The swagger ui helps to try the result and the history endpoints.

# The files
Most of the files following this schema:

```text
P:filename
C:filename
```

We can define an order of the files, and each files define which one is the previous one and which one is the following one

Call it later parent (`P:`) and children (`C:`)

The first file has no Parent and the Last one has no Children. 

so there will be 1-1 files with this schema: 

```text
P:
C:filename
```

```text
P:filename
C:
```

## Mistaken files
There is files which is not in the correct ones. These files look like the same:
```text
P:filename
C:filename
```
But we need to define which one is mistaken and which one is correct.

## How we get the files

In this project Generate the files randomly

The file generation for this project has rules:
- The file names need to be unique
- There must be an order of the good files
- The first file's first row need to be: `P:` 
- The last file's second row need to be: `C:`
- If there is mistaken files, then need those too
- The mistaken files must have value for the parent and the children too 
- if there is only 1 correct file it needs to be:
  ```text
  P:
  C:
  ```

This dummy file generation class is use only alphabetical character, 
so if the requested number of files is less than 26 then all files will be one letter long
and so on it will calculate the needed length by the number of files.

# The Algorithm

1. Find the first file, the only one file which has nothing after the `P:`
2. The first files second row define the second file
3. So on until find the files which has nothing after the `C:` because it is the last one
4. Need to get the files which is not in the order those must be the mistaken files 
5. Make a pretty print for it

