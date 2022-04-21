# DataEngineer_ScalaPractice
## Functionality
It is a Scala application that:
- Connects to the DB2 instance on the cloud
- Generates a table with specified of sample records
   - product_id - autogenerated numeric
   - product_group - autogenerated numeric in 0..9 range
   - year - autogenerated numeric in 2015..2018 range
   - 12 columns with monthly purchases amount - numeric in 0..100000 range
   - There should be no duplicates for the same product/year
   - If there are multiple product rows for different years, product/group combination should be concise
 
## Requirements
Strongly recomended to use:

| Name | Version |
| ------ | ------ |
| SBT | 1.6.2 |
| JDK | 11 and higher |
| Scala | 2.13.8 |
| Git | 2.33.0 |

## How to run application
>All next steps you can do from <b>cmd</b> or <b>PowerShell</b>.
1. Clone the current version of the repository to your computer.
```sh
 git clone https://github.com/YuliyaValova/DataEngineer_ScalaPractice
```
2. Go to the project folder.
```sh
cd DataEngineer_ScalaPractice
```
3. Write this command to start sbt.
```sh
sbt 
```
4. Go to your sbt-shell and write: 
```sh
run jdbc:db2://<URL>/<DATABASE_NAME> <USER_NAME> <YOUR_PASSWORD> <TABLE_NAME> <NUMBER_OF_GENERATED_ROWS>
```
>URL - Url for connection to DB2 on Cloud. <br>
>DATABASE_NAME - Name of the database. <br>
>USER_NAME - Username from DB2 credentials. <br>
>YOUR_PASSWORD - Password from DB2 credentials. <br>
>TABLE_NAME - The name under which the generated table will be saved in the database. <br>
>NUMBER_OF_GENERATED_ROWS (Optional, by default 20000) - Number of rows generated for the table.
<br>

<b> Example </b>
<br>
Command with your custom number of genereted rows:
```sh
sbt run jdbc:db2://b1bc1839-6f45-4pd4-bef4-10cf081900bf.c1ogj3dd0tgtu0lade00.databases.appdomain.cloud:34305/bludb qlf38947 CG745Jpa7a930Jvb Test 15000
```
<b> or </b>
<br>
Command with default number of genereted rows:
```sh
sbt run jdbc:db2://b1bc1839-6f45-4pd4-bef4-10cf081900bf.c1ogj3dd0tgtu0lade00.databases.appdomain.cloud:34305/bludb qlf38947 CG745Jpa7a930Jvb Test 
```
 

